package co.ucc.pedidos.model;

public class Pago {
    private String id;
    private double monto;
    private String fecha;
    private String estado;
    private String pedidoId;

    public Pago() {}

    public Pago(String id, double monto, String fecha, String pedidoId) {
        this.id = id;
        this.monto = monto;
        this.fecha = fecha;
        this.pedidoId = pedidoId;
        this.estado = "PENDIENTE";
    }

    public String getId() {
        return id;
    }

    public double getMonto() {
        return monto;
    }

    public String getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public String getPedidoId() {
        return pedidoId;
    }

    public boolean esMontoValido() {
        return monto > 0;
    }

    public void procesarPago() {
        if (esMontoValido()) {
            this.estado = "PROCESADO";
        }
    }

    private void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }
}
