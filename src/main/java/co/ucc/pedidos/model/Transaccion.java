package co.ucc.pedidos.model;

import java.util.Date;

public abstract class Transaccion {
    private String idTransaccion;
    private Date fechaTransaccion;
    private double monto;
    private String estado;

    public Transaccion() {
        this.fechaTransaccion = new Date();
    }

    public Transaccion(String idTransaccion, double monto) {
        this.idTransaccion = idTransaccion;
        this.monto = monto;
        this.fechaTransaccion = new Date();
        this.estado = "PENDIENTE";
    }

    public abstract boolean validarTransaccion();

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(String idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
