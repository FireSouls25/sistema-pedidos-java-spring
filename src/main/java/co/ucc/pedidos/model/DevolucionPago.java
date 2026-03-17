package co.ucc.pedidos.model;

public class DevolucionPago extends Transaccion {
    private String motivo;
    private PagoModel pagoOriginal;
    private boolean approved;

    public DevolucionPago() {
        super();
    }

    public DevolucionPago(String idTransaccion, double monto, String motivo, PagoModel pagoOriginal) {
        super(idTransaccion, monto);
        this.motivo = motivo;
        this.pagoOriginal = pagoOriginal;
        this.approved = false;
    }

    @Override
    public boolean validarTransaccion() {
        if (getMonto() <= 0) {
            return false;
        }
        if (pagoOriginal == null) {
            return false;
        }
        if (pagoOriginal.getMonto() < getMonto()) {
            return false;
        }
        return true;
    }

    public boolean procesarDevolucion() {
        if (validarTransaccion()) {
            this.approved = true;
            setEstado("COMPLETADA");
            return true;
        }
        setEstado("RECHAZADA");
        return false;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public PagoModel getPagoOriginal() {
        return pagoOriginal;
    }

    public void setPagoOriginal(PagoModel pagoOriginal) {
        this.pagoOriginal = pagoOriginal;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
