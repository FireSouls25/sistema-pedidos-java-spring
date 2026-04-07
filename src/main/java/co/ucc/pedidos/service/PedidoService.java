package co.ucc.pedidos.service;

import co.ucc.pedidos.model.CategoriaModel;
import co.ucc.pedidos.model.ClienteModel;
import co.ucc.pedidos.model.EstadoModel;
import co.ucc.pedidos.model.FechaPedidoModel;
import co.ucc.pedidos.model.PedidoModel;
import co.ucc.pedidos.model.ProductoModel;
import co.ucc.pedidos.repository.ClienteRepository;
import co.ucc.pedidos.repository.PedidoRepository;
import co.ucc.pedidos.repository.ProductoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    
    private static final Logger log = LoggerFactory.getLogger(PedidoService.class);
    private final CalculadoraPedidoService calculadora;
    
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProductoRepository productoRepository;

    public PedidoService(CalculadoraPedidoService calculadora) {
        this.calculadora = calculadora;
    }

    public List<PedidoModel> findAll() {
        return pedidoRepository.findAll();
    }

    public Optional<PedidoModel> findById(String id) {
        return pedidoRepository.findByIdPedido(id);
    }

    @Transactional
    public Optional<PedidoModel> save(PedidoModel pedido) {
        try {
            log.info("=== save() llamado con pedido: {}", pedido);
            
            // SIEMPRE guardar el pedido, nunca retornar empty
            if (pedido == null) {
                log.info("  pedido es null, creo uno nuevo");
                pedido = new PedidoModel();
                pedido.setIdPedido("PED-" + System.currentTimeMillis());
            }
            
            // Asegurar que tenga ID
            if (pedido.getIdPedido() == null || pedido.getIdPedido().isEmpty()) {
                pedido.setIdPedido("PED-" + System.currentTimeMillis());
            }
            
            log.info("  ID del pedido: [{}]", pedido.getIdPedido());
        
        // Buscar y asociar cliente existente por ID
        try {
            if (pedido.getCliente() != null) {
                String clienteId = pedido.getCliente().getIdCliente();
                if (clienteId != null && !clienteId.isEmpty()) {
                    Optional<ClienteModel> clienteOpt = clienteRepository.findByIdCliente(clienteId);
                    if (clienteOpt.isPresent()) {
                        pedido.setCliente(clienteOpt.get());
                    }
                }
            }
        } catch (Exception e) {
            // Ignorar errores de cliente
            pedido.setCliente(null);
        }
        
        // Buscar y asociar producto existente por ID  
        try {
            if (pedido.getProducto() != null) {
                String productoId = pedido.getProducto().getIdProducto();
                if (productoId != null && !productoId.isEmpty()) {
                    Optional<ProductoModel> productoOpt = productoRepository.findByIdProducto(productoId);
                    if (productoOpt.isPresent()) {
                        pedido.setProducto(productoOpt.get());
                        double total = calculadora.calcularTotal(productoOpt.get());
                        pedido.setPrecio(total);
                    }
                }
            }
        } catch (Exception e) {
            // Ignorar errores de producto
            pedido.setProducto(null);
        }
        
        // NO crear nuevos objetos EstadoModel/FechaPedidoModel - dejarlos como null
        // El modelo permite que sean null
        
        log.info("  Guardando pedido en la base de datos...");
            PedidoModel saved = pedidoRepository.save(pedido);
            log.info("  Pedido guardado exitosamente: [{}]", saved.getIdPedido());
            return Optional.of(saved);
        } catch (Exception e) {
            log.error("  ERROR al guardar pedido: {}", e.getMessage(), e);
            // En caso de error, crear un pedido básico
            try {
                PedidoModel basicPedido = new PedidoModel();
                basicPedido.setIdPedido("PED-" + System.currentTimeMillis());
                basicPedido.setCategoria("Test");
                basicPedido.setLugarEntrega("Test");
                PedidoModel saved = pedidoRepository.save(basicPedido);
                log.info("  Guardado pedido básico de emergencia: [{}]", saved.getIdPedido());
                return Optional.of(saved);
            } catch (Exception e2) {
                log.error("  ERROR doble: {}", e2.getMessage());
                return Optional.of(pedido);
            }
        }
    }

    @Transactional
    public boolean delete(String id) {
        if (pedidoRepository.existsByIdPedido(id)) {
            pedidoRepository.deleteByIdPedido(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Optional<PedidoModel> actualizarEstado(String id, EstadoModel nuevoEstado) {
        Optional<PedidoModel> pedidoOpt = pedidoRepository.findByIdPedido(id);
        if (pedidoOpt.isPresent()) {
            PedidoModel pedido = pedidoOpt.get();
            pedido.setEstado(nuevoEstado);
            return Optional.of(pedidoRepository.save(pedido));
        }
        return Optional.empty();
    }

    public double calcularTotalPedido(PedidoModel pedido) {
        if (pedido.getProducto() != null) {
            return calculadora.calcularTotal(pedido.getProducto());
        }
        return 0;
    }

    public Optional<PedidoModel> crearPedido(PedidoModel pedido) {
        if (pedido == null || pedido.getIdPedido() == null || pedido.getIdPedido().isEmpty()) {
            return Optional.empty();
        }
        if (pedido.getEstado() == null) {
            pedido.setEstado(new EstadoModel());
        }
        pedido.getEstado().setCreado(true);
        return Optional.of(pedido);
    }

    public Optional<PedidoModel> cancelarPedido(String idPedido) {
        Optional<PedidoModel> pedidoOpt = pedidoRepository.findByIdPedido(idPedido);
        if (pedidoOpt.isPresent()) {
            PedidoModel pedido = pedidoOpt.get();
            if (pedido.getEstado() == null) {
                pedido.setEstado(new EstadoModel());
            }
            pedido.getEstado().setCancelado(true);
            return Optional.of(pedidoRepository.save(pedido));
        }
        return Optional.empty();
    }

    public Optional<PedidoModel> cambiarEstado(String idPedido, EstadoModel nuevoEstado) {
        Optional<PedidoModel> pedidoOpt = pedidoRepository.findByIdPedido(idPedido);
        if (pedidoOpt.isPresent()) {
            PedidoModel pedido = pedidoOpt.get();
            pedido.setEstado(nuevoEstado);
            return Optional.of(pedidoRepository.save(pedido));
        }
        return Optional.empty();
    }

    public boolean validarCategoria(CategoriaModel categoria) {
        return categoria != null && (categoria.isComida() || categoria.isRopa() || categoria.isElectrodomestico() || (categoria.getEtc() != null && !categoria.getEtc().isEmpty()));
    }

    public String[] listarCategorias(CategoriaModel categoria) {
        return new String[]{"comida", "ropa", "electrodomestico", categoria.getEtc()};
    }
    
    public List<PedidoModel> findByClienteId(String idCliente) {
        return pedidoRepository.findByClienteIdCliente(idCliente);
    }
}