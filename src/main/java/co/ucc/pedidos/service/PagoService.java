package co.ucc.pedidos.service;

import co.ucc.pedidos.exception.MontoInvalidoException;
import co.ucc.pedidos.exception.PagoNoEncontradoException;
import co.ucc.pedidos.model.Pago;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PagoService {
    
    private final List<Pago> listaPagos = new ArrayList<>();

    public Pago registrarPago(Pago pago) {
        if (pago == null) {
            throw new MontoInvalidoException("El pago no puede ser nulo");
        }
        if (!pago.esMontoValido()) {
            throw new MontoInvalidoException("El monto debe ser mayor que cero");
        }
        pago.procesarPago();
        listaPagos.add(pago);
        return pago;
    }

    public List<Pago> listarPagos() {
        return new ArrayList<>(listaPagos);
    }

    public Pago buscarPorId(String id) {
        for (Pago pago : listaPagos) {
            if (pago.getId().equals(id)) {
                return pago;
            }
        }
        throw new PagoNoEncontradoException("Pago no encontrado con ID: " + id);
    }
}
