package modelos;

public class DetallesPedidos {

    int id_detallepedido;
    int id_producto;
    int id_pedido;
    int cantidad_productopedido;
    Productos producto;
    Pedidos pedido;

    public DetallesPedidos() {

    }

    public DetallesPedidos(int id_detallepedido, int id_producto, int id_pedido, int cantidad_productopedido, Productos producto, Pedidos pedido) {
        this.id_detallepedido = id_detallepedido;
        this.id_producto = id_producto;
        this.id_pedido = id_pedido;
        this.cantidad_productopedido = cantidad_productopedido;
        this.producto = producto;
        this.pedido = pedido;
    }

    public int getId_detallepedido() {
        return id_detallepedido;
    }

    public void setId_detallepedido(int id_detallepedido) {
        this.id_detallepedido = id_detallepedido;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public int getCantidad_productopedido() {
        return cantidad_productopedido;
    }

    public void setCantidad_productopedido(int cantidad_productopedido) {
        this.cantidad_productopedido = cantidad_productopedido;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public Pedidos getPedido() {
        return pedido;
    }

    public void setPedido(Pedidos pedido) {
        this.pedido = pedido;
    }

    
}
