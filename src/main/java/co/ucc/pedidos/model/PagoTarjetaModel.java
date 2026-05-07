package co.ucc.pedidos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class PagoTarjetaModel extends PagoModel{

    @Column(name = "numero_tarjeta", length = 16)
    private String numeroTarjeta;



    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }
}
