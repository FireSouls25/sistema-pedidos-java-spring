package co.ucc.pedidos.model;

public class PagoModel {
    private double precio;
    private String metodoPago;
    private String idPago;
    private ClienteModel cliente;
    private boolean procesado;

    public PagoModel() {}

    public PagoModel(double precio, String metodoPago, String idPago) {
        this.precio = precio;
        this.metodoPago = metodoPago;
        this.idPago = idPago;
        this.procesado = false;
    }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public String getIdPago() { return idPago; }
    public void setIdPago(String idPago) { this.idPago = idPago; }

    public ClienteModel getCliente() { return cliente; }
    public void setCliente(ClienteModel cliente) { this.cliente = cliente; }

    public boolean isProcesado() { return procesado; }
    public void setProcesado(boolean procesado) { this.procesado = procesado; }

    public void procesarPago() {
        this.procesado = true;
    }

    public boolean validarMetodoPago() {
        return metodoPago != null && !metodoPago.isEmpty();
    }

    public void cancelarPago() {
        this.procesado = false;
    }

    public String generarComprobante() {
        return "Comprobante - Pago: " + idPago + ", Monto: " + precio + ", Metodo: " + metodoPago;
    }
}
