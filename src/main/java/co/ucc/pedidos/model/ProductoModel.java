package co.ucc.pedidos.model;

public class ProductoModel {
    private String idProducto;
    private int cantidad;
    private String resena;
    private InventarioModel inventario;
    private CategoriaModel categoria;
    private PedidoModel pedido;

    public ProductoModel() {}

    public ProductoModel(String idProducto, int cantidad, String resena) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.resena = resena;
    }

    public String getIdProducto() { return idProducto; }
    public void setIdProducto(String idProducto) { this.idProducto = idProducto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public String getResena() { return resena; }
    public void setResena(String resena) { this.resena = resena; }

    public InventarioModel getInventario() { return inventario; }
    public void setInventario(InventarioModel inventario) { this.inventario = inventario; }

    public CategoriaModel getCategoria() { return categoria; }
    public void setCategoria(CategoriaModel categoria) { this.categoria = categoria; }

    public PedidoModel getPedido() { return pedido; }
    public void setPedido(PedidoModel pedido) { this.pedido = pedido; }
}
