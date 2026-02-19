package co.ucc.pedidos.model;

public class EstadoModel {
    private boolean creado;
    private boolean enviado;
    private boolean entregado;
    private boolean cancelado;
    private PedidoModel pedido;

    public EstadoModel() {
        this.creado = true;
        this.enviado = false;
        this.entregado = false;
        this.cancelado = false;
    }

    public boolean isCreado() {
        return creado;
    }

    public void setCreado(boolean creado) {
        this.creado = creado;
    }

    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }

    public boolean isEntregado() {
        return entregado;
    }

    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }

    public PedidoModel getPedido() {
        return pedido;
    }

    public void setPedido(PedidoModel pedido) {
        this.pedido = pedido;
    }
}
