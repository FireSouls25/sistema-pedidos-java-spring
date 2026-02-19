package co.ucc.pedidos.model;

import java.util.List;

public class ClienteModel {
    private String idCliente;
    private String genero;
    private String nombre;
    private String correoElectronico;
    private String direccion;
    private PagoModel pago;
    private List<PedidoModel> pedidos;

    public ClienteModel() {}

    public ClienteModel(String idCliente, String genero, String nombre, String correoElectronico, String direccion) {
        this.idCliente = idCliente;
        this.genero = genero;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public PagoModel getPago() {
        return pago;
    }

    public void setPago(PagoModel pago) {
        this.pago = pago;
    }

    public List<PedidoModel> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<PedidoModel> pedidos) {
        this.pedidos = pedidos;
    }
}
