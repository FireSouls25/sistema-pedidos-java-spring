package co.ucc.pedidos.controller;

import co.ucc.pedidos.model.Pago;
import co.ucc.pedidos.service.PagoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @PostMapping
    public Pago registrarPago(@RequestBody Pago pago) {
        return pagoService.registrarPago(pago);
    }

    @GetMapping
    public List<Pago> listarPagos() {
        return pagoService.listarPagos();
    }

    @GetMapping("/{id}")
    public Pago obtenerPago(@PathVariable String id) {
        return pagoService.buscarPorId(id);
    }
}
