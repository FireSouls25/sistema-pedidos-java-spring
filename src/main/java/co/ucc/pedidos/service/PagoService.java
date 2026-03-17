package co.ucc.pedidos.service;

import co.ucc.pedidos.exception.MontoInvalidoException;
import co.ucc.pedidos.exception.PagoNoEncontradoException;
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
        pago.procesarPago();
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
}
