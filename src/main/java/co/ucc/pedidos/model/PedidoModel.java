package co.ucc.pedidos.model;

import java.util.List;

public class PedidoModel {
    private double precio;
    private String idPedido;
    private EstadoModel estado;
    private String categoria;
    private String lugarEntrega;
    private FechaPedidoModel fechaPedido;
    private ProductoModel producto;
    private List<ClienteModel> clientes;

    public PedidoModel() {
        this.estado = new EstadoModel();
    }

    public PedidoModel(String idPedido, double precio, String categoria, String lugarEntrega) {
        this.idPedido = idPedido;
        this.precio = precio;
        this.categoria = categoria;
        this.lugarEntrega = lugarEntrega;
        this.estado = new EstadoModel();
        this.fechaPedido = new FechaPedidoModel();
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public EstadoModel getEstado() {
        return estado;
    }

    public void setEstado(EstadoModel estado) {
        this.estado = estado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getLugarEntrega() {
        return lugarEntrega;
    }

    public void setLugarEntrega(String lugarEntrega) {
        this.lugarEntrega = lugarEntrega;
    }

    public FechaPedidoModel getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(FechaPedidoModel fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public ProductoModel getProducto() {
        return producto;
    }

    public void setProducto(ProductoModel producto) {
        this.producto = producto;
    }

    public List<ClienteModel> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteModel> clientes) {
        this.clientes = clientes;
    }
}
