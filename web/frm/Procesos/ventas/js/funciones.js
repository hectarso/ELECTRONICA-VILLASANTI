function fechaHoy() {

    var hoy = new new Date().toJSON().slice(0, 10);



    console.log(hoy);
    $("#fecha_venta").val(hoy);
}
function addZero(i) {
    if (i < 10) {
        i = '0' + i;
    }
    return i;
}

function buscarIdVenta() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_venta").val(json.id_venta);
            $("#fecha_venta").val(json.fecha_venta);
            var fecha = $("#fecha_venta").serialize();
            $("#id_timbrado").val(json.id_timbrado);
            $("#numero_timbrado").val(json.numero_timbrado);
            $("#id_establecimiento").val(json.id_establecimiento);
            $("#id_puesto").val(json.id_puesto);
            /*$("#nombre_establecimiento").val(json.nombre_establecimiento);
             $("#ruc_establecimiento").val(json.ruc_establecimiento);*/
            $("#id_cliente").val(json.id_cliente);
            $("#nombre_cliente").val(json.nombre_cliente);
            $("#ruc_cliente").val(json.ruc_cliente);
            $("#numero_factura").val(json.numero_factura);
            $("#estado_venta").val(json.estado_venta);
            $("#total").val(json.total);
            $("#contenidoDetalle").html(json.contenido_detalle);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                //siguienteCampo("#id_tipoventa", "#botonAgregar", true);
                $("#detalle").prop('hidden', true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                //siguienteCampo("#id_tipoventa", "#botonModificar", true);
                $("#detalle").prop('hidden', false);
            }
            if (json.estado_venta === "ANULADO" || json.estado_venta === "COBRADO") {
                $(".bloquearboton").prop('disabled', true);
                $("#agregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', true);
                $("#botonCobrar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
            } else {
                $(".bloquearboton").prop('disabled', false);
                $("#agregar").prop('disabled', false);
            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function buscarNombreVenta() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombre.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_venta").val(id);
                // $("#nombre_cliente").focus();
                buscarIdVenta();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function agregarVenta() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    console.log(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {

            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#botonAgregar").prop('disabled', true);
            $("#detalle").prop('hidden', false);
            $("#id_venta").val(json.id_venta);
            buscarIdVenta();
            // $("#id_venta").focus;
            //$("#id_venta").select();
            //}
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function modificarVenta() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/modificar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_venta").focus;
            $("#id_venta").select();
            buscarIdVenta();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function eliminarVenta() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            eliminarVentaDetalle();
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#id_venta").focus;
            $("#id_venta").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

//timbrado
function buscarIdTimbrado() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdTimbrado.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_timbrado").val(json.id_timbrado);
            $("#numero_timbrado").val(json.numero_timbrado);

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos de timbrado.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNumeroTimbrado() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNumeroTimbrado.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_timbrado").val(id);
                $("#numero_timbrado").focus();
                buscarIdTimbrado();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

//cliente
function buscarIdCliente() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCliente.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_cliente").val(json.id_cliente);
            $("#nombre_cliente").val(json.nombre_cliente);
            $("#ruc_cliente").val(json.ruc_cliente);

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreCliente() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreCliente.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_cliente").val(id);
                $("#nombre_cliente").focus();
                buscarIdCliente();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
//buscar Establecimiento
function buscarIdEstablecimiento() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdEstablecimiento.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_establecimiento").val(json.id_establecimiento);
            $("#nombre_establecimiento").val(json.nombre_establecimiento);
            $("#ruc_establecimiento").val(json.ruc_establecimiento);
            /*$("#direccion_establecimiento").val(json.direccion_establecimiento);
             $("#id_ciudad").val(json.id_ciudad);
             $("#nombre_ciudad").val(json.nombre_ciudad);
             $("#telefono_establecimiento").val(json.telefono_establecimiento);
             $("#representante_establecimiento").val(json.representante_establecimiento);*/

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreEstablecimiento() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreEstablecimiento.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_establecimiento").val(id);
                $("#nombre_establecimiento").focus();
                buscarIdEstablecimiento();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
//puesto
function buscarIdPuesto() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPuesto.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_puesto").val(json.id_puesto);
            //$("#nombre_puesto").val(json.nombre_puesto);
        },

        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombrePuesto() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombrePuesto.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_puesto").val(id);
                $("#nombre_puesto").focus();
                buscarIdPuesto();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo traer los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
}


//buscarfactura
function buscarNumeroFactura() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarFactura.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Comprobando datos ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            if (json.nuevo === "true") {


            } else {
                alert("N° de factura repetido");
                $("#numero_factura").val("");
                $("#numero_factura").focus();
                //siguienteCampo("#descripcion_producto", "#botonModificar", true);
            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}

function validarFormulario() {
    var valor = true;
    if ($("#nombre_venta").val().length < 3) {
        valor = false;
        $("#mensajes").html("Nombre Venta no puede estar vacio.");
        $("#nombre_venta").focus();
    }

    if ($("#nombre_cliente").val().length < 2) {
        valor = false;
        $("#mensajes").html("Cliente no puede estar vacio.");
        $("#id_cliente").focus();
    }

    if ($("#numero_timbrado").val().length < 2) {
        valor = false;
        $("#mensajes").html("Timbrado no puede estar vacio.");
        $("#id_timbrado").focus();
    }

    /*if ($("#nombre_tipoventa").val().length < 2) {
     valor = false;
     $("#mensajes").html("Tipo Venta no puede estar vacio.");
     $("#id_tipoventa").focus();
     }*/
    return valor;
}
function limpiarFormulario() {
    $("#id_venta").val("0");
    $("#nombre_venta").val("");
    $("#nombre_tipoventa").val("");
    $("#numero_factura").val("0");
    $("#estado_venta").val("");
    $("#id_establecimiento").val("0");
    $("#id_puesto").val("0");
    /*$("#nombre_establecimiento").val("");
     $("#ruc_establecimiento").val("");*/
    $("#id_timbrado").val("");
    $("#numero_timbrado").val("0");
    $("#nombre_cliente").val("");
    $("#ruc_cliente").val("");
    $("#id_cliente").val("0");
    $("#id_tipocventa").val("0");
    $("#fecha_venta").val("");
}
function agregarLinea() {
    $("#id_detalle_venta").val("0");
    $("#id_producto").val("0");
    $("#nombre_producto").val("");
    //$("#nombre_marca").val("");
    $("#codigo_producto").val("");
    $("#precio_venta_producto").val("0");
    $("#iva_producto").val("0");
    $("#cantidad_producto_venta").val("0");
    $("#preciototal_venta").val("0");
    //$("#nombre_iva").val("");
    //$("#porcentaje_iva").val("0");
    $("#ssubtotal_5").val("0");
    $("#ssubtotal_10").val("0");
    $("#ssubtotal_exenta").val("0");
    $("#ttotalgravada_5").val("0");
    $("#ttotalgravada_10").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_producto").focus();
    $("#id_producto").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    buscarIdVentaDetalle();
    siguienteCampo("#id_producto", "#cantidad_producto_venta", true);
}
function editarLinea(id) {
    $("#id_detalle_venta").val(id);
    //$("#id_producto").val("0");
    //$("#nombre_producto").val("");
    //$("#nombre_marca").val("");
    //$("#codigo_producto").val("");
    //$("#precio_venta_producto").val("0");
    //$("#iva_producto").val("0");
    $("#cantidad_producto_venta").val("");
    $("#preciototal_venta").val("0");
    //$("#nombre_iva").val("");
    //$("#porcentaje_iva").val("0");
    $("#ssubtotal_5").val("0");
    $("#ssubtotal_10").val("0");
    $("#ssubtotal_exenta").val("0");
    $("#ttotalgravada_5").val("0");
    $("#ttotalgravada_10").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_producto").focus();
    $("#id_producto").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdVentaDetalle();
    siguienteCampo("#cantidad_producto_venta", "#botonModificarLinea", true);
}
// ventasproductos
function buscarIdVentaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdVentaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_producto").val(json.id_producto);
            $("#nombre_producto").val(json.nombre_producto);
            $("#codigo_producto").val(json.codigo_producto);
            $("#precio_venta_producto").val(json.precio_venta_producto);
            $("#iva_producto").val(json.iva_producto);
            //$("#nombre_marca").val(json.nombre_marca);
            $("#cantidad_producto_venta").val(json.cantidad_producto_venta);
            $("#preciototal_venta").val(json.preciototal_venta);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                //siguienteCampo("#nombre_producto", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', true);
                //siguienteCampo("#nombre_producto", "#botonModificar", true);
            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarIdVentaVentaDetalle() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdVentaVentaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoDetalle").html(json.contenido_detalle);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function agregarVentaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_venta = $("#id_venta").val();
    datosFormulario += "&id_venta=" + id_venta;
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarVentaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            if (json.cantidad_stock !== -1) {
                $("#mensajes").html(json.mensaje);
                $("#panelLinea").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
                buscarIdVenta();
            } else {

                $("#mensajes").html(json.mensaje);
            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function modificarVentaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_venta = $("#id_venta").val();
    datosFormulario += "&id_venta=" + id_venta;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarVentaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdVenta();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function eliminarVentaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_venta = $("#id_venta").val();
    datosFormulario += "&id_venta=" + id_venta;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarVentaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json)
        {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdVenta();

        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
// productos
function buscarIdProducto() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdProducto.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_producto").val(json.id_producto);
            $("#nombre_producto").val(json.nombre_producto);
            //$("#nombre_marca").val(json.nombre_marca);
            $("#codigo_producto").val(json.codigo_producto);
            $("#precio_venta_producto").val(json.precio_venta_producto);
            $("#iva_producto").val(json.iva_producto);
            /*$("#id_iva").val(json.id_iva);
             $("#nombre_iva").val(json.nombre_iva);
             $("#porcentaje_iva").val(json.porcentaje_iva);*/
            //alert(json.codigo_producto);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreProducto() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreProducto.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_producto").val(id);
                $("#nombre_producto").focus();
                buscarIdProducto();
                $("#buscar").fadeOut("slow");
                $("#panelLinea").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });

}

function iva(e) {
    //document.getElementById(e.id).value;
    var valor, valor1, total;
    valor = $("#iva_producto").val();
    valor1 = $("#preciototal_venta").val();
    total = (valor1 * valor) / 100;

    if ($("#iva_producto").val().trim() === "10") {

        $("#ssubtotal_10").val(total);
        $("#ssubtotal_5").val(0);
        $("#ssubtotal_exenta").val(0);
    } else {
        if ($("#iva_producto").val().trim() === "5") {

            $("#ssubtotal_5").val(total);
            $("#ssubtotal_10").val(0);
            $("#ssubtotal_exenta").val(0);
        } else {
            $("#ssubtotal_5").val(0);
            $("#ssubtotal_10").val(0);
            $("#ssubtotal_exenta").val(0);
        }
    }
}

//ivagravado
function ivagravada(e) {
    //document.getElementById(e.id).value;
    var valorc, valorp, totalg;
    valorc = $("#cantidad_producto_venta").val();
    valorp = $("#precio_venta_producto").val();
    totalg = (valorc * valorp);

    if ($("#iva_producto").val().trim() === "10") {

        $("#ttotalgravada_10").val(totalg);
        $("#ttotalgravada_5").val(0);
    } else {
        if ($("#iva_producto").val().trim() === "5") {

            $("#ttotalgravada_5").val(totalg);
            $("#ttotalgravada_10").val(0);
        } else {
            $("#ttotalgravada_5").val(0);
            $("#ttotalgravada_10").val(0);
        }
    }
}
/*function iva(e) {
 //document.getElementById(e.id).value;
 var valor, valor1, total;
 valor = $("#porcentaje_iva").val();
 valor1 = $("#preciototal_venta").val();
 total = (valor1 * valor) / 100;
 
 if ($("#porcentaje_iva").val().trim() === "10") {
 
 $("#ssubtotal_10").val(total);
 $("#ssubtotal_5").val(0);
 $("#ssubtotal_exenta").val(0);
 } else {
 if ($("#porcentaje_iva").val().trim() === "5") {
 
 $("#ssubtotal_5").val(total);
 $("#ssubtotal_10").val(0);
 $("#ssubtotal_exenta").val(0);
 } else {
 $("#ssubtotal_5").val(0);
 $("#ssubtotal_10").val(0);
 $("#ssubtotal_exenta").val(0);
 }
 }
 }*/
//ivagravado
/*function ivagravada(e) {
 //document.getElementById(e.id).value;
 var valor, valor1, totalg;
 valor = $("#cantidad_producto_venta").val();
 valor1 = $("#preciototal_venta").val();
 totalg = (valor1 * valor);
 
 if ($("#porcentaje_iva").val().trim() === "10") {
 
 $("#ttotalgravada_10").val(totalg);
 $("#ttotalgravada_5").val(0);
 } else {
 if ($("#porcentaje_iva").val().trim() === "5") {
 
 $("#ttotalgravada_5").val(totalg);
 $("#ttotalgravada_10").val(0);
 } else {
 $("#ssubtotal_5").val(0);
 $("#ssubtotal_10").val(0);
 }
 }
 }*/

function validarcantidad() {
    var valor = true;
    if ($("#cantidad_producto_venta").val().trim() === "" || $("#cantidad_producto_venta").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Cantidad debe ser mayor a 0.");
        $("#cantidad_producto_venta").focus();
    }


    return valor;
}

function pretotal(e) {
    //Aqui crearemos una función para que de acuerdo a la cantidad de productos, se haga un sub total
    var valor1, valor2, /*valor3, valor4,*/ subtotal/*, totaliva, preciototal*/;
    valor1 = $("#precio_venta_producto").val();
    valor2 = $("#cantidad_producto_venta").val();
    /*valor3 = $("#porcentaje_iva").val();
     valor4 = $("#preciototal_venta").val();*/
    subtotal = valor1 * valor2;
    //totaliva = valor3 * valor4;
    //preciototal = subtotal + totaliva;
    //$("#preciototal_venta").val(preciototal);
    $("#preciototal_venta").val(subtotal);
}

function numeros(evt) {
    if (window.event) {//asignamos el valor de la tecla a keynum
        keynum = evt.keyCode; //IE
    } else {
        keynum = evt.which; //FF
    }
    //comprobamos si se encuentra en el rango numérico y que teclas no recibirá.
    if ((keynum > 47 && keynum < 58) || keynum === 8 || keynum === 13 || keynum === 6) {
        return true;
    } else {
        alert("Solo ingrese numeros");
        //$("#codigo_articulo").val('');
        return false;
    }
}

/*function abrir(url){
 open(url,"clientes","width=825px, heigth=892px, top=10,left=5");
 }*/

/*function abrir(url){
 open(url,'ventas','height=600,width=700,left=300,top=50,resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,location=no,directories=no, status=yes');
 }
 /*function open(url){
 <input type="button" value="Open a Popup Window" 
 onclick="window.open('http://localhost:8084/PROYECTOFINAL2019CTA/frm/Procesos/ventas/clientes.html',
 'popUpWindow','height=600,width=800,left=300,top=50,
 resizable=yes,scrollbars=yes,toolbar=yes,menubar=no,
 location=no,directories=no, status=yes');
 }*/
function soloNumeros(e) {
    var key = window.Event ? e.which : e.keyCode();
    return (key >= 48 && key <= 57);
}

function buscarIdCaja2() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId2.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            //var monto = dar_formato_numero(json.monto_inicial, ",", ".");
            $("#mensajes").html(json.mensaje);
            $("#id_caja").val(json.id_caja);
            aa=document.getElementById("id_venta").value;
            bb=document.getElementById("id_caja").value;
            location.href = "../detallescajas/index.html?id_venta="+aa+"&id_caja="+bb;
            

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}