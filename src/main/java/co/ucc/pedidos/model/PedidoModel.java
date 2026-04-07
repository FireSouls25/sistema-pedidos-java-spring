package co.ucc.pedidos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;

@Entity
@Table(name = "pedido")
public class PedidoModel {
    @Id
    @Column(name = "id_pedido")
    private String idPedido;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "categoria", length = 100)
    private String categoria;
    @Column(name = "lugar_entrega", length = 255)
    private String lugarEntrega;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado", nullable = true)
    private EstadoModel estado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fecha_pedido", nullable = true)
    private FechaPedidoModel fechaPedido;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = true)
    private ClienteModel cliente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_producto", nullable = true)
    private ProductoModel producto;

    public PedidoModel() {
        // No inicializar estado y fechaPedido aquí - se manejarán como opcionales
    }

    public PedidoModel(String idPedido, double precio, String categoria, String lugarEntrega) {
        this.idPedido = idPedido;
        this.precio = precio;
        this.categoria = categoria;
        this.lugarEntrega = lugarEntrega;
    }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public String getIdPedido() { return idPedido; }
    public void setIdPedido(String idPedido) { this.idPedido = idPedido; }

    public EstadoModel getEstado() { return estado; }
    public void setEstado(EstadoModel estado) { this.estado = estado; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getLugarEntrega() { return lugarEntrega; }
    public void setLugarEntrega(String lugarEntrega) { this.lugarEntrega = lugarEntrega; }

    public FechaPedidoModel getFechaPedido() { return fechaPedido; }
    public void setFechaPedido(FechaPedidoModel fechaPedido) { this.fechaPedido = fechaPedido; }

    public ClienteModel getCliente() { return cliente; }
    public void setCliente(ClienteModel cliente) { this.cliente = cliente; }

    public ProductoModel getProducto() { return producto; }
    public void setProducto(ProductoModel producto) { this.producto = producto; }
}
