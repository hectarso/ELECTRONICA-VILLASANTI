//detalles cajas

function hidden() {
    var t = $("#id_tipo_factura").val();

    if (t === "1") {


        $("#tipo_factura").prop('hidden', false);
        $("#tipo_factura").prop('hidden', true);
        $("#id_venta").prop('readonly', false);
        $("#id_venta").val(0);
        $("#total").val(0);
        $("#vuelto2").prop('style', 'color: black');
        $("#importe_caja").val(0);
        $("#id_pago").val(0);
        $("#vuelto2").val(0);

    } else {
        if (t === "2") {
            $("#tipo_factura").prop('hidden', true);
            $("#tipo_factura").prop('hidden', false);
            $("#id_venta").prop('readonly', true);
            $("#id_venta").val(0);
            // $("#nro_cuota").val(0);
            $("#total").val(0);
            $("#vuelto2").prop('style', 'color: black');
            $("#importe_caja").val(0);
            $("#id_pago").val(0);
            $("#vuelto2").val(0);

        }

    }
}

function agregarCaja2() {
    var datosFormulario = $("#formPrograma").serialize();
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
            // limpiarFormulario();
            $("#botonAgregar").prop('disabled', true);
            $("#detalle").prop('hidden', false);
            $("#id_caja").val(json.id_caja);
            buscarIdCaja2();
            $("#id_caja").focus();
            $("#id_caja").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_caja").focus();
        }
    });
}

function modificarCaja2() {
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
            //limpiarFormulario();
            $("#id_caja").focus();
            $("#id_caja").select();
            buscarIdCaja2();
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

function eliminarCaja2() {
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
            $("#id_caja").focus();
            $("#id_caja").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo eliminar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
}

function buscarIdCaja2() {
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

function buscarNombreCaja2() {
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
                buscarIdCaja2();
               // buscarId();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
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

function buscarCaja() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarCaja.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);


            if (json.nuevo === "false") {
                $("#nombre_caja").val("");
                $("#nombre_caja").focus();
            } else {

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

function buscarIdCajaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    alert(datosFormulario);
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
            $("#id_detallecaja").val(json.id_detallecaja);
            $("#id_venta").val(json.id_venta);
            $("#id_pago").val(json.id_pago);
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

function agregarCajaDetalle2() {
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
            // $("#botonAgregarLinea").prop('disabled', true);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdCaja2();

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
/*function agregarCajaDetalle() {
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
 $("#botonAgregarLinea").prop('disabled', true);
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
 */
function modificarCajaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_caja = $("#id_caja").val();
    datosFormulario += "&id_caja=" + id_caja;

    alert(datosFormulario);
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
            buscarIdCaja2();
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
            buscarIdCaja2();

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

function buscarIdVenta() {
    var datosFormulario = $("#formLinea").serialize();
    //  alert(datosFormulario);
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
            $("#total").val(json.total);

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


function buscarNombreVenta2() {
    var datosFormulario = $("#formBuscar").serialize();
    // alert(datosFormulario);
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
                $("#nombre_cliente").focus();
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


function validarFormulario() {
    var valor = true;
    if ($("#fecha_apertura").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Seleccionar fecha.");
        $("#fecha_apertura").focus();
    }
    if ($("#monto_inicial").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Monto no puede estar vacio.");
        $("#monto_inicial").focus();
    }



    return valor;
}

function validarFormularioDetalle() {
    var valor = true;
    if ($("#id_venta").val().trim() === "" || $("#id_venta").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Seleccionar Venta.");
        $("#id_venta").focus();
    }
    if ($("#id_pago").val().trim() === "" || $("#id_pago").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Seleccionar forma pago.");
        $("#id_pago").focus();
    }

    if ($("#importe_caja").val().trim() === "" || $("#importe_caja").val().trim() === "0") {
        valor = false;
        $("#mensajes").html("Importe no puede estar vacio.");
        $("#importe_caja").focus();
    }

    if ($("#importe_caja").val().trim() < $("#total").val().trim()) {
        valor = false;
        $("#mensajes").html("Importe no debe ser menor que el total");
        $("#importe_caja").focus();
    }

    return valor;
}

function limpiarFormulario() {
    $("#id_caja").val("");
    $("#fecha_apertura").val("");
    $("#monto_inicial").val("");
    $("#estado_caja").val("");

}

function buscarIdPagos2() {
    var datosFormulario = $("#formLinea").serialize();
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

function buscarNombrePago() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombrePago.jsp',
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
                buscarIdPago();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
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

function agregarLinea() {
    $("#id_detallecaja").val("0");
    $("#id_venta").val("0");
    $("#numero_factura").val("");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_venta").focus();
    $("#id_venta").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    //siguienteCampo("#horas_detallecaja", "#botonAgregarLinea", true);
}
function editarLinea(id) {
    $("#id_detallecaja").val(id);
    $("#id_venta").val("0");

    // $("#id_cuenta").val("0");
    $("#importe_caja").val("0");
    $("#vuelto2").val("0");
    $("#total").val("0");
    $("#id_pago").val("0");
    //$("#nombre_forma_pago").val("");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");


    if ($("#id_tipo_factura").val().trim() === "CONTADO") {
        $("#id_venta").focus();
        $("#id_venta").select();
    }

    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdCajaDetalle();
    // buscarIdCuenta();
    //siguienteCampo("#cantidad_productocaja", "#botonModificarLinea", true);
}
// cajasproductos
function buscarIdCajaDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    // alert(datosFormulario);
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
            $("#numero_factura").val(json.numero_factura);
            $("#id_pago").val(json.id_pago);
            $("#id_caja").val(json.id_caja);
            $("#importe_caja").val(json.importe_caja);
            // $("#vuelto2").val(vuelto2);
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

function vuelto2(e) {
    var valor1, valor2, vuelto2;
    valor1 = $("#total").val();
    valor2 = $("#importe_caja").val();

    vuelto2 = (valor2 - valor1);
    if (vuelto2 > 0) {
        $("#vuelto2").prop('style', 'color: green');
        $("#vuelto2").val(vuelto2);
    } else {
        if (vuelto2 < 0) {
            $("#vuelto2").prop('style', 'color: red');
            $("#vuelto2").val(vuelto2);
        } else {
            $("#vuelto2").prop('style', 'color: black');
            $("#vuelto2").val(vuelto2);
        }
    }


}



function limpiarLinea() {
    $("#id_detallecaja").val("0");
    $("#id_venta").val("0");
    $("#importe_caja").val("0");
    $("#vuelto2").val("0");
    $("#total").val("0");
    $("#id_pago").val("0");

}
function soloLetras(e) {
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toLowerCase();
    letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
    especiales = "8-37-39-46";

    tecla_especial = false;
    for (var i in especiales) {
        if (key === especiales[i]) {
            tecla_especial = true;
            break;
        }
    }

    if (letras.indexOf(tecla) === -1 && !tecla_especial) {
        alert("ingrese solo letras");
        return false;
    }
}


function SoloNumeros(evt) {
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
        return false;
    }
}

function agregarRecibo() {
    var datosFormulario = $("#formLinea").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregarRecibo.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

           $("#id").val(json.id);
           $("#id_caja").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_caja").focus();
        }
    });
}

function buscarFormaPago2() {
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
                buscarIdPagos2();
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

function buscarNombreTipofactura() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
       type: 'POST',
       url: 'jsp/buscarNombreTipoFactura.jsp',
       data: datosFormulario,
       dataType: 'json',
       beforeSend: function (objeto) {
           $("#mensajes").html("Enviando datos al Servidor ...");
           $("#contenidoBusqueda").css("display", "none");
       },
       success: function (json){
           $("#mensajes").html(json.mensaje);
           $("#contenidoBusqueda").html(json.contenido);
           $("#contenidoBusqueda").fadeIn("slow");
           $("tbody tr").on("click", function(){
              var id = $(this).find("td:first").html();
              $("#panelBuscar").html("");
              $("#id_tipo_factura").val(id);
              $("#nombre_tipo_factura").focus();
              buscarIdTipo_factura();
              $("#buscar").fadeOut("slow");
              $("#panelPrograma").fadeIn("slow");
          });
       },
       error: function(e) {
           $("#mensajes").html("No se pudo recuperar los datos.");
       },
       complete: function(objeto, exito, error) {
           if (exito === "success"){
               
           }
       }
    });
}

function buscarIdTipo_factura() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdTipoFactura.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_tipo_factura").val(json.id_tipo_factura);
            $("#nombre_tipo_factura").val(json.nombre_tipo_factura);
            
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error){
           if (exito === "success"){
           }
        }
    });
}
function buscarNumeroFactura() {
    var datosFormulario = $("#formLinea").serialize();
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
function soloNumeros(e) {
    var key = window.Event ? e.which : e.keyCode();
    return (key >= 48 && key <= 57);
}