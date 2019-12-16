
package modelos;


public class Productos {

    private int id_producto;
    private String nombre_producto;
    private String descripcion_producto;
    private Marcas marca;
    private Categorias categoria;
 
    private Ubicaciones ubicacion;
    private int stockmin_producto;
    private int stockmax_producto;
    private String codigo_producto;
    private int precio_compra_producto;
    private int precio_venta_producto;
    private int iva_producto;

    public Productos() {
    }

    public Productos(int id_producto, String nombre_producto, String descripcion_producto, Marcas marca, Categorias categoria, Ubicaciones ubicacion, int stockmin_producto, int stockmax_producto, String codigo_producto, int precio_compra_producto, int precio_venta_producto, int iva_producto) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.descripcion_producto = descripcion_producto;
        this.marca = marca;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
        this.stockmin_producto = stockmin_producto;
        this.stockmax_producto = stockmax_producto;
        this.codigo_producto = codigo_producto;
        this.precio_compra_producto = precio_compra_producto;
        this.precio_venta_producto = precio_venta_producto;
        this.iva_producto = iva_producto;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public Marcas getMarca() {
        return marca;
    }

    public void setMarca(Marcas marca) {
        this.marca = marca;
    }

    public Categorias getCategoria() {
        return categoria;
    }

    public void setCategoria(Categorias categoria) {
        this.categoria = categoria;
    }

    public Ubicaciones getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicaciones ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getStockmin_producto() {
        return stockmin_producto;
    }

    public void setStockmin_producto(int stockmin_producto) {
        this.stockmin_producto = stockmin_producto;
    }

    public int getStockmax_producto() {
        return stockmax_producto;
    }

    public void setStockmax_producto(int stockmax_producto) {
        this.stockmax_producto = stockmax_producto;
    }

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public int getPrecio_compra_producto() {
        return precio_compra_producto;
    }

    public void setPrecio_compra_producto(int precio_compra_producto) {
        this.precio_compra_producto = precio_compra_producto;
    }

    public int getPrecio_venta_producto() {
        return precio_venta_producto;
    }

    public void setPrecio_venta_producto(int precio_venta_producto) {
        this.precio_venta_producto = precio_venta_producto;
    }

    public int getIva_producto() {
        return iva_producto;
    }

    public void setIva_producto(int iva_producto) {
        this.iva_producto = iva_producto;
    }

   
}
