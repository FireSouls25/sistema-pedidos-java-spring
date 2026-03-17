package co.ucc.pedidos.service;

import co.ucc.pedidos.model.FechaPedidoModel;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class FechaPedidoService {

    public Date calcularFechaEstimada(FechaPedidoModel fechaPedido) {
        return fechaPedido != null ? fechaPedido.getFechaEstimada() : null;
    }

    public void actualizarFechaRecibo(FechaPedidoModel fechaPedido, Date fechaRecibir) {
        if (fechaPedido != null) {
            fechaPedido.setFechaRecibir(fechaRecibir);
        }
    }

    public boolean verificarRetrasoPedido(FechaPedidoModel fechaPedido) {
        if (fechaPedido == null) {
            return false;
        }
        Date fechaRecibir = fechaPedido.getFechaRecibir();
        Date fechaEstimada = fechaPedido.getFechaEstimada();
        return fechaRecibir != null && fechaEstimada != null && fechaRecibir.after(fechaEstimada);
    }
}
