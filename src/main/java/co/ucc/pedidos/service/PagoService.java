package co.ucc.pedidos.service;

import co.ucc.pedidos.exception.MontoInvalidoException;
import co.ucc.pedidos.exception.PagoNoEncontradoException;
import co.ucc.pedidos.model.DevolucionPago;
import co.ucc.pedidos.model.PagoModel;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PagoService {
    
    private final List<PagoModel> listaPagos = new ArrayList<>();

    public PagoModel registrarPago(PagoModel pago) {
        if (pago == null) {
            throw new MontoInvalidoException("El pago no puede ser nulo");
        }
        if (pago.getMonto() <= 0) {
            throw new MontoInvalidoException("El monto debe ser mayor que cero");
        }
        procesarPago(pago);
        listaPagos.add(pago);
        return pago;
    }

    public List<PagoModel> listarPagos() {
        return new ArrayList<>(listaPagos);
    }

    public PagoModel buscarPorId(String id) {
        for (PagoModel pago : listaPagos) {
            if (pago.getIdPago().equals(id)) {
                return pago;
            }
        }
        throw new PagoNoEncontradoException("Pago no encontrado con ID: " + id);
    }

    public boolean validarMetodoPago(PagoModel pago) {
        return pago.getMetodoPago() != null && !pago.getMetodoPago().isEmpty();
    }

    public void procesarPago(PagoModel pago) {
        if (pago.validarTransaccion()) {
            pago.setProcesado(true);
            pago.setEstado("COMPLETADO");
        }
    }

    public void cancelarPago(PagoModel pago) {
        pago.setProcesado(false);
        pago.setEstado("CANCELADO");
    }

    public String generarComprobante(PagoModel pago) {
        return "Comprobante - Pago: " + pago.getIdPago() + ", Monto: " + pago.getPrecio() + ", Metodo: " + pago.getMetodoPago();
    }

    public boolean procesarDevolucion(DevolucionPago devolucion) {
        if (devolucion.validarTransaccion()) {
            devolucion.setApproved(true);
            devolucion.setEstado("COMPLETADA");
            return true;
        }
        devolucion.setEstado("RECHAZADA");
        return false;
    }
}
