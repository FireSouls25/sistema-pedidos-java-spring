package co.ucc.pedidos.service;

import co.ucc.pedidos.model.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {
    
    private final CalculadoraPedido calculadora;

    public PedidoService() {
        this.calculadora = new CalculadoraPedido();
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
}
