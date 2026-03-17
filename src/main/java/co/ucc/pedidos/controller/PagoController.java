package co.ucc.pedidos.controller;

import co.ucc.pedidos.model.PagoModel;
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
    public PagoModel registrarPago(@RequestBody PagoModel pago) {
        return pagoService.registrarPago(pago);
    }

    @GetMapping
    public List<PagoModel> listarPagos() {
        return pagoService.listarPagos();
    }

    @GetMapping("/{id}")
    public PagoModel obtenerPago(@PathVariable String id) {
        return pagoService.buscarPorId(id);
    }
}
