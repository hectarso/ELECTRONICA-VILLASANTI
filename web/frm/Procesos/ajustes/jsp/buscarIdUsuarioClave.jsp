<%@page import="modelos.Usuarios"%>
<%@page import="org.json.simple.JSONObject"%>

<%
    HttpSession sesion = request.getSession();
    
    int id_usuario = 0;
    String usuario_usuario = "";
    String clave_usuario = "";
    String nombre_usuario = "";
    int id_rol = 0;
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    //String nuevo = "true";

    Usuarios usuarioLogueado = (Usuarios) sesion.getAttribute("usuarioLogueado");
    if (usuarioLogueado != null) {

        id_usuario = usuarioLogueado.getId_usuario();
        usuario_usuario = usuarioLogueado.getUsuario_usuario();
         clave_usuario = usuarioLogueado.getClave_usuario();
        nombre_usuario=usuarioLogueado.getNombre_usuario();
        id_rol = usuarioLogueado.getRol().getId_rol();
        tipo = "success";
        mensaje = "Datos encontrados";
//        nuevo = "false";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    //  obj.put("nuevo", nuevo);
    obj.put("id_usuario", id_usuario);
    obj.put("usuario_usuario", usuario_usuario);
    obj.put("clave_usuario", clave_usuario);
    obj.put("nombre_usuario", nombre_usuario);
    out.print(obj);
    out.flush();
%>