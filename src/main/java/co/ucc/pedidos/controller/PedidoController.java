package co.ucc.pedidos.controller;

import co.ucc.pedidos.model.PedidoModel;
import co.ucc.pedidos.service.PedidoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<PedidoModel> listarPedidos() {
        return pedidoService.findAll();
    }

    @GetMapping("/{id}")
    public PedidoModel obtenerPedido(@PathVariable String id) {
        return pedidoService.findById(id);
    }

    @PostMapping
    public PedidoModel crearPedido(@RequestBody PedidoModel pedido) {
        return pedidoService.save(pedido);
    }

    @DeleteMapping("/{id}")
    public void eliminarPedido(@PathVariable String id) {
        pedidoService.delete(id);
    }
}
