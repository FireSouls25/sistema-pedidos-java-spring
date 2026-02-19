package co.ucc.pedidos.model;

import java.util.List;

public class InventarioModel {
    private int cantidad;
    private boolean disponibilidad;
    private String categoria;
    private List<ProductoModel> productos;

    public InventarioModel() {}

    public InventarioModel(int cantidad, boolean disponibilidad, String categoria) {
        this.cantidad = cantidad;
        this.disponibilidad = disponibilidad;
        this.categoria = categoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<ProductoModel> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoModel> productos) {
        this.productos = productos;
    }
}
