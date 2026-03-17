package co.ucc.pedidos.model;

public class PagoModel extends Transaccion {
    private double precio;
    private String metodoPago;
    private String idPago;
    private ClienteModel cliente;
    private boolean procesado;

    public PagoModel() {
        super();
    }

    public PagoModel(double precio, String metodoPago, String idPago) {
        super(idPago, precio);
        this.precio = precio;
        this.metodoPago = metodoPago;
        this.idPago = idPago;
        this.procesado = false;
    }

    @Override
    public boolean validarTransaccion() {
        return validarMetodoPago() && getMonto() > 0;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getMonto() {
        return precio;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    public boolean isProcesado() {
        return procesado;
    }

    public void setProcesado(boolean procesado) {
        this.procesado = procesado;
    }

    public boolean validarMetodoPago() {
        return metodoPago != null && !metodoPago.isEmpty();
    }

    public void procesarPago() {
        if (validarTransaccion()) {
            this.procesado = true;
            setEstado("COMPLETADO");
        }
    }

    public void cancelarPago() {
        this.procesado = false;
        setEstado("CANCELADO");
    }

    public String generarComprobante() {
        return "Comprobante - Pago: " + idPago + ", Monto: " + precio + ", Metodo: " + metodoPago;
    }
}
