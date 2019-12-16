//cajas
function hidden() {
    var t = $("#id_tipo_factura").val();

    if (t === "1") {


        $("#tipo_factura").prop('hidden', false);
        $("#tipo_factura").prop('hidden', true);
        $("#id_venta").prop('readonly', false);
        $("#id_venta").val(0);
        $("#total").val(0);
        $("#vuelto").prop('style', 'color: black');
        $("#importe_caja").val(0);
        $("#id_pago").val(0);
        $("#vuelto").val(0);

    } else {
        if (t === "2") {
            $("#tipo_factura").prop('hidden', true);
            $("#tipo_factura").prop('hidden', false);
            $("#id_venta").prop('readonly', true);
            $("#id_venta").val(0);
            // $("#nro_cuota").val(0);
            $("#total").val(0);
            $("#vuelto").prop('style', 'color: black');
            $("#importe_caja").val(0);
            $("#id_pago").val(0);
            $("#vuelto").val(0);

        }

    }
}


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

function buscarIdCaja() {
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
            var monto = dar_formato_numero(json.monto_inicial, ",", ".");
            $("#mensajes").html(json.mensaje);
            $("#id_caja").val(json.id_caja);
            $("#fecha_apertura").val(json.fecha_apertura);
            $("#monto_inicial").val(monto);
            $("#estado_caja").val(json.estado_caja);
            $("#contenidoDetalle").html(json.contenido_detalle);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                $("#detalle").prop('hidden', true);
                //siguienteCampo("#nombre_caja", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', true);
                $("#detalle").prop('hidden', false);
                //siguienteCampo("#nombre_caja", "#botonModificar", true);
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

function buscarNombreCaja() {
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
                $("#id_caja").val(id);
                // $("#nombre_cliente").focus();
                buscarIdCaja();
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

function agregarCaja() {
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
            $("#id_caja").val(json.id_caja);
            buscarIdCaja();
            $("#id_caja").focus;
            $("#id_caja").select();
            
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

function modificarCaja() {
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
            $("#id_caja").focus;
            $("#id_caja").select();
            buscarIdCaja();
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

function eliminarCaja() {
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
            eliminarCobroDetalle();
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#id_caja").focus;
            $("#id_caja").select();
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
//usuario
function buscarIdUsuario() {
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
            $("#id_usuario").val(json.id_usuario);
            $("#nombre_usuario").val(json.nombre_usuario);

            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#nombre_usuario", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#nombre_usuario", "#botonModificar", true);
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

function buscarNombreUsuario() {
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
                $("#id_usuario").val(id);
                $("#nombre_usuario").focus();
                buscarIdUsuario();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
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
//venta
function buscarIdVenta() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdVenta.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_venta").val(json.id_venta);
            $("#numero_factura").val(json.numero_factura);
            $("#total").val(json.total);

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos de venta.");
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
        url: 'jsp/buscarNombreVenta.jsp',
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
    if ($("#nombre_caja").val().length < 3) {
        valor = false;
        $("#mensajes").html("Nombre Caja no puede estar vacio.");
        $("#nombre_caja").focus();
    }

    /*if ($("#numero_factura_venta").val().length < 2) {
     valor = false;
     $("#mensajes").html("N° de factura es obligatorio.");
     $("#numero_factura_venta").focus();
     }*/

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
    $("#id_caja").val("0");
    $("#id_usuario").val("0");
    $("#nombre_venta").val("");
    $("#monto_inicial").val("");
    $("#fecha_apertura").val("");
    $("#estado_caja").val("");
    

}
function agregarLinea() {
    $("#id_detallecaja").val("0");
    $("#id_venta").val("0");
    $("#numero_factura").val("0");
    $("#total").val("0");
    $("#id_venta").val("0");
    $("#id_pago").val("0");
    $("#forma_pago").val("");
    $("#id_caja").val("0");
    $("#importe_caja").val("0");
     
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_caja").focus();
    $("#id_caja").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    buscarIdCajaDetalle();
    //siguienteCampo("#horas_detalle_venta", "#botonAgregarLinea", true);
}
function editarLinea(id) {
    $("#id_detallecaja").val(id);
    $("#id_caja").val("0");
    $("#id_venta").val("0");
    $("#id_venta").val("0");
    $("#numero_factura").val("0");
    $("#total").val("0");
    $("#id_pago").val("0");
    $("#forma_pago").val("");
    $("#importe_caja").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_caja").focus();
    $("#id_caja").select();
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdCajaDetalle();
    //siguienteCampo("#cantidad_producto_venta", "#botonModificarLinea", true);
}
// ventasproductos
function buscarIdCajaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCajaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_venta").val(json.id_venta);
            $("#id_pago").val(json.id_pago);
            $("#id_caja").val(json.id_caja);
            $("#importe_caja").val(json.importe_caja);
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
function buscarIdCajaCajaDetalle() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCajaCajaDetalle.jsp',
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
function agregarCajaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_caja = $("#id_caja").val();
    datosFormulario += "&id_caja=" + id_caja;
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarCajaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdCaja();
            
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
function modificarCajaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_caja = $("#id_caja").val();
    datosFormulario += "&id_caja=" + id_caja;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarCajaDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdCaja();
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
function eliminarCajaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_caja = $("#id_caja").val();
    datosFormulario += "&id_caja=" + id_caja;
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarCajaDetalle.jsp',
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
            buscarIdCaja();

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

function buscarIdPagos() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPago.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_pago").val(json.id_pago);
            $("#forma_pago").val(json.forma_pago);

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
function buscarFormaPago() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarFormaPago.jsp',
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
                $("#id_pago").val(id);
                $("#forma_pago").focus();
                buscarIdPagos();
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

function validarFormularioCaja() {

    var valor = true;
    var num = $("#id_venta").val();

    var prov = $("#id_pago").val();
    
    var total = $("#total").val();
    
    var prod = $("#importe_caja").val();
    
    var im = $("#importe").val();
    // var cant = $("#cantidad_productocaja").val();

    if (prov === "0") {
        valor = false;

        $("#id_pago").val("");
        $("#id_pago").focus();
        $("#mensajes").html("Tipo pago no puede estar vacio.");

    } else {

        if (num === "0") {
            valor = false;

            $("#id_venta").val("");
            $("#id_venta").focus();
            $("#mensajes").html("Venta no puede estar vacio.");

        } else {
            if (prod === "0") {
                valor = false;

                $("#importe_caja").val("");
                $("#importe_caja").focus();
                $("#mensajes").html("Importe no puede estar vacia.");
            }else{
                 if (im < total ) {
                valor = false;

                $("#importe_caja").val("");
                $("#importe_caja").focus();
                $("#mensajes").html("Importe no puede ser menor al total a pagar");
            }
            }



        }


    }
    return valor;
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
    var valor1, valor2,/*valor3, valor4,*/ subtotal/*, totaliva, preciototal*/;
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

function validardetalle() {
    if ($("#id_producto").val().trim() === "" || $("#id_producto").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Debe añadir un producto");
        $("#id_producto").focus();
    }
    var valor2, valort;
    valort = $("#total").val();
    valor2 = $("#importe_caja").val();
    //vuelto = (valor2 - valort);
    if (valor2 < valort){
        valor = false;
        $("#mensajes").html("Importe debe ser igual o mayor al total");
        $("#importe_caja").focus();  
    }
}

function vuelto(e) {
    //document.getElementById(e.id).value;
    var valor1, valor2, vuelto;
    valor1 = $("#total").val();
    valor2 = $("#importe_caja").val();
    
    vuelto = (valor2 - valor1);
    if (vuelto > 0){
        $("#vuelto").prop('style', 'color: green');
        $("#vuelto").val(vuelto);
    }else{
        if (vuelto < 0){
        $("#vuelto").prop('style', 'color: red');
        $("#vuelto").val(vuelto);
        }else{
            $("#vuelto").prop('style', 'color: black');
            $("#vuelto").val(vuelto);
        }
    }
    
    
}
function soloNumeros(e) {
    var key = window.Event ? e.which : e.keyCode();
    return (key >= 48 && key <= 57);
}