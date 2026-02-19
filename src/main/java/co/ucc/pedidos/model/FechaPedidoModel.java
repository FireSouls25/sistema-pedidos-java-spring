package co.ucc.pedidos.model;

import java.util.Date;

public class FechaPedidoModel {
    private Date fechaPedir;
    private Date fechaRecibir;
    private Date fechaEstimada;
    private PedidoModel pedido;

    public FechaPedidoModel() {}

    public FechaPedidoModel(Date fechaPedir, Date fechaRecibir, Date fechaEstimada) {
        this.fechaPedir = fechaPedir;
        this.fechaRecibir = fechaRecibir;
        this.fechaEstimada = fechaEstimada;
    }

    public Date getFechaPedir() {
        return fechaPedir;
    }

    public void setFechaPedir(Date fechaPedir) {
        this.fechaPedir = fechaPedir;
    }

    public Date getFechaRecibir() {
        return fechaRecibir;
    }

    public void setFechaRecibir(Date fechaRecibir) {
        this.fechaRecibir = fechaRecibir;
    }

    public Date getFechaEstimada() {
        return fechaEstimada;
    }

    public void setFechaEstimada(Date fechaEstimada) {
        this.fechaEstimada = fechaEstimada;
    }

    public PedidoModel getPedido() {
        return pedido;
    }

    public void setPedido(PedidoModel pedido) {
        this.pedido = pedido;
    }
}
