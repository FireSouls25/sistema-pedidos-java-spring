package co.ucc.pedidos.service;

import co.ucc.pedidos.model.CategoriaModel;
import co.ucc.pedidos.model.EstadoModel;
import co.ucc.pedidos.model.PedidoModel;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {
    
    private final CalculadoraPedidoService calculadora;

    public PedidoService(CalculadoraPedidoService calculadora) {
        this.calculadora = calculadora;
    }

    public List<PedidoModel> findAll() {
        return new ArrayList<>();
    }

    public PedidoModel findById(String id) {
        return null;
    }

    public PedidoModel save(PedidoModel pedido) {
        if (pedido.getProducto() != null) {
            double total = calculadora.calcularTotal(pedido.getProducto());
            pedido.setPrecio(total);
        }
        return pedido;
    }

    public void delete(String id) {
    }

    public PedidoModel actualizarEstado(String id, EstadoModel nuevoEstado) {
        return null;
    }

    public double calcularTotalPedido(PedidoModel pedido) {
        if (pedido.getProducto() != null) {
            return calculadora.calcularTotal(pedido.getProducto());
        }
        return 0;
    }

    public void crearPedido(PedidoModel pedido) {
        pedido.getEstado().setCreado(true);
    }

    public void cancelarPedido(PedidoModel pedido) {
        pedido.getEstado().setCancelado(true);
    }

    public void cambiarEstado(PedidoModel pedido, EstadoModel nuevoEstado) {
        pedido.setEstado(nuevoEstado);
    }

    public boolean validarCategoria(CategoriaModel categoria) {
        return categoria != null && (categoria.isComida() || categoria.isRopa() || categoria.isElectrodomestico() || (categoria.getEtc() != null && !categoria.getEtc().isEmpty()));
    }

    public String[] listarCategorias(CategoriaModel categoria) {
        return new String[]{"comida", "ropa", "electrodomestico", categoria.getEtc()};
    }
}
