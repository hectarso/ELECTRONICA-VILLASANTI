
<%@page import="modelos.Ivas"%>
<%@page import="controladores.StocksControlador"%>
<%@page import="modelos.Stocks"%>
<%@page import="controladores.ProductosControlador"%>
<%@page import="modelos.Productos"%>
<%@page import="modelos.Marcas"%>
<%@page import="modelos.Categorias"%>
<%@page import="modelos.Ubicaciones"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_producto = Integer.parseInt(request.getParameter("id_producto"));
    int id_marca = Integer.parseInt(request.getParameter("id_marca"));
    int id_categoria = Integer.parseInt(request.getParameter("id_categoria"));
    int id_ubicacion = Integer.parseInt(request.getParameter("id_ubicacion"));
    String nombre_producto = request.getParameter("nombre_producto");
    int stockmin_producto = Integer.parseInt(request.getParameter("stockmin_producto"));
    int stockmax_producto = Integer.parseInt(request.getParameter("stockmax_producto"));
    String codigo_producto = request.getParameter("codigo_producto");
    String precioc = request.getParameter("precio_compra_producto").replaceAll("\\.", "");
    int precio_compra_producto = Integer.parseInt(precioc);
    String preciov = request.getParameter("precio_venta_producto").replaceAll("\\.", "");
    int precio_venta_producto = Integer.parseInt(preciov);
    int iva_producto = Integer.parseInt(request.getParameter("iva_producto"));
    int cantidad_stock = 0;

    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Marcas marca = new Marcas();
    marca.setId_marca(id_marca);

    Categorias categoria = new Categorias();
    categoria.setId_categoria(id_categoria);

    Ubicaciones ubicacion = new Ubicaciones();
    ubicacion.setId_ubicacion(id_ubicacion);
    

    Productos producto = new Productos();
    producto.setId_producto(id_producto);
    producto.setNombre_producto(nombre_producto);
    producto.setStockmin_producto(stockmin_producto);
    producto.setStockmax_producto(stockmax_producto);
    producto.setCodigo_producto(codigo_producto);
    producto.setPrecio_compra_producto(precio_compra_producto);
    producto.setPrecio_venta_producto(precio_venta_producto);
    producto.setIva_producto(iva_producto);
    producto.setMarca(marca);
    producto.setCategoria(categoria);
    producto.setUbicacion(ubicacion);

    if (ProductosControlador.agregar(producto)) {
        tipo = "success";
        mensaje = "Datos agregados.";
        Stocks stock = new Stocks();
        stock.setCantidad_stock(cantidad_stock);
        stock.setProducto(producto);
        StocksControlador.agregar(stock);
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_producto", producto.getId_producto());
    out.print(obj);
    out.flush();
%>
