package co.ucc.pedidos.model;

public class CategoriaModel {
    private boolean comida;
    private boolean ropa;
    private boolean electrodomestico;
    private String etc;
    private ProductoModel producto;

    public CategoriaModel() {}

    public CategoriaModel(boolean comida, boolean ropa, boolean electrodomestico, String etc) {
        this.comida = comida;
        this.ropa = ropa;
        this.electrodomestico = electrodomestico;
        this.etc = etc;
    }

    public boolean isComida() { return comida; }
    public void setComida(boolean comida) { this.comida = comida; }

    public boolean isRopa() { return ropa; }
    public void setRopa(boolean ropa) { this.ropa = ropa; }

    public boolean isElectrodomestico() { return electrodomestico; }
    public void setElectrodomestico(boolean electrodomestico) { this.electrodomestico = electrodomestico; }

    public String getEtc() { return etc; }
    public void setEtc(String etc) { this.etc = etc; }

    public ProductoModel getProducto() { return producto; }
    public void setProducto(ProductoModel producto) { this.producto = producto; }

    public boolean validarCategoria() {
        return comida || ropa || electrodomestico || (etc != null && !etc.isEmpty());
    }

    public String[] listarCategorias() {
        return new String[]{"comida", "ropa", "electrodomestico", etc};
    }
}
