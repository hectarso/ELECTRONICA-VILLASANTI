


<%@page import="controladores.UsuariosControlador"%>
<%@page import="modelos.Roles"%>
<%@page import="modelos.Usuarios"%>
<%@page import="utiles.Utiles"%>
<%@page import="org.json.simple.JSONObject"%>
<%
    HttpSession sesion = request.getSession();

    int id_usuario = 0;
   
    String usuario_usuario = "";
    String clave_usuario = "";
    String nombre_usuario = "";
    String claveactual_usuario = Utiles.md5(request.getParameter("claveactual_usuario"));
    String clavenueva_usuario = request.getParameter("clavenueva_usuario");
    int id_rol = 0;
    

    String tipo = "error";
    String mensaje = "Datos no modificados";

    Usuarios usuarioLogueado = (Usuarios) sesion.getAttribute("usuarioLogueado");
    if (usuarioLogueado != null) {

        id_usuario = usuarioLogueado.getId_usuario();
        usuario_usuario = usuarioLogueado.getUsuario_usuario();
        clave_usuario = usuarioLogueado.getClave_usuario();
        nombre_usuario=usuarioLogueado.getNombre_usuario();
        id_rol = usuarioLogueado.getRol().getId_rol();
       
        System.out.println("usuarioLogueado - clave (jsp-actual): " + clave_usuario);
        System.out.println("del formulario - clave actual: " + claveactual_usuario);

        if (claveactual_usuario.equals(clave_usuario)) {
            Usuarios usuario = new Usuarios();
            usuario.setId_usuario(id_usuario);
            usuario.setUsuario_usuario(usuario_usuario);
            usuario.setClave_usuario(clavenueva_usuario);
            usuario.setNombre_usuario(nombre_usuario);
            

            Roles rol = new Roles();
            rol.setId_rol(id_rol);

            usuario.setRol(rol);

            

            if (UsuariosControlador.modificar(usuario)) {
                tipo = "success";
                mensaje = "Datos modificados correctamente";
                System.out.println("Cambiado!!");
            }

        } else {
            tipo = "error";
            mensaje = "Clave actual incorrecta";
        }

    }

    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("tipo", tipo);

    obj.put("id_usuario", id_usuario);
    obj.put("usuario_usuario", usuario_usuario);
    obj.put("clave_usuario", clave_usuario);
    obj.put("nombre_usuario", nombre_usuario);

    out.print(obj);
    out.flush();
%>