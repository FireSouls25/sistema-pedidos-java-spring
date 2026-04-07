package co.ucc.pedidos.controller;

import co.ucc.pedidos.model.PedidoModel;
import co.ucc.pedidos.service.PedidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private static final Logger log = LoggerFactory.getLogger(PedidoController.class);
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<PedidoModel> listarPedidos() {
        return pedidoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoModel> obtenerPedido(@PathVariable String id) {
        return pedidoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PedidoModel> crearPedido(@RequestBody PedidoModel pedido) {
        log.info("=== POST /api/pedidos recibido ===");
        log.info("pedido es null?: {}", pedido == null);
        if (pedido != null) {
            log.info("idPedido: [{}]", pedido.getIdPedido());
            log.info("categoria: [{}]", pedido.getCategoria());
            log.info("lugarEntrega: [{}]", pedido.getLugarEntrega());
            log.info("precio: [{}]", pedido.getPrecio());
            log.info("cliente: [{}]", pedido.getCliente());
            log.info("producto: [{}]", pedido.getProducto());
            log.info("estado: [{}]", pedido.getEstado());
            log.info("fechaPedido: [{}]", pedido.getFechaPedido());
        }
        
        var result = pedidoService.save(pedido);
        log.info("save() retornó: {}", result);
        log.info("save() tiene valor?: {}", result.isPresent());
        
        return result
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable String id) {
        return pedidoService.delete(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }
}