package co.ucc.pedidos.model;

public class CalculadoraPedido {
    
    public double calcularTotal(ProductoModel producto) {
        if (producto != null) {
            return producto.getCantidad();
        }
        return 0;
    }
}
