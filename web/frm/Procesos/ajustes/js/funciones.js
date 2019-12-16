function buscarIdAjuste() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
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
            $("#id_ajuste_stock").val(json.id_ajuste_stock);
            $("#fecha_ajuste_stock").val(json.fecha_ajuste_stock);
            $("#motivo_ajuste_stock").val(json.motivo_ajuste_stock);
            $("#contenidoDetalle").html(json.contenido_detalle);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonImprimir").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);

                //siguienteCampo("#id_tipoajuste", "#botonAgregar", true);
                $("#detalle").prop('hidden', true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonImprimir").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                //$("#").prop('disabled', false);
                //siguienteCampo("#id_tipoajuste", "#botonModificar", true);
                $("#detalle").prop('hidden', false);

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
function buscarNombreAjuste() {
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
                $("#id_ajuste_stock").val(id);
                $("motivo_ajuste_stock").focus();
                buscarIdAjuste();
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

function buscarIdUsuario() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
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
            $("#usuario_usuario").val(json.usuario_usuario);
            $("#clave_usuario").val(json.clave_usuario);
            $("#id_rol").val(json.id_rol);
            $("#nombre_rol").val(json.nombre_rol);
            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                siguienteCampo("#id_rol", "#botonAgregar", false);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                //  $("#id_cliente").prop('disabled',true);

                siguienteCampo("#id_rol", "#botonAgregar", false);
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
function buscarNombreUsuario() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreUsuario.jsp',
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
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });

}
function agregarAjuste() {
    var datosFormulario = $("#formPrograma").serialize();
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
            $("#id_ajuste_stock").val(json.id_ajuste_stock);
            buscarIdAjuste();
            abrir();
            $("#id_ajuste_stock").focus;
            $("#id_ajuste_stock").select();

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
function modificarAjuste() {
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
            $("#id_ajuste_stock").focus;
            $("#id_ajuste_stock").select();
            buscarIdAjuste();
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
function eliminarAjustes() {
    var datosFormulario = $("#formPrograma").serialize();
    alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            //eliminarAjusteDetalle();
            limpiarFormulario();
            $("#mensajes").html(json.mensaje);
            $("#id_ajuste_stock").focus;
            $("#id_ajuste_stock").select();
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


function validarFormularioAjuste() {
    var valor = true;

    var canti = $("#cantidad_ajuste_stock").val();
    var produc = $("#id_producto").val();
    var motivo = $("#motivo_ajuste_stock").val();
    if (motivo === "") {
        valor = false;
        $("#motivo_ajuste_stock").val("");
        $("#motivo_ajuste_stock").focus();
        $("#mensajes").html("Motivo No puede estar Vacio.");
    } else {
        if (produc === "0") {
            valor = false;
            $("#id_producto").val("");
            $("#id_producto").focus();
            $("#mensajes").html("Producto no puede estar vacio.");
        }
        if (canti === "0") {
            valor = false;
            $("#cantidad_ajuste_stock").val("");
            $("#cantidad_ajuste_stock").focus();
            $("#mensajes").html("Cantidad ajuste no puede estar vacia.");
        }
    }


    return valor;

}
function limpiarFormulario() {
    $("#id_ajuste_stock").val("0");
    // $("#id_usuario").val("");
    //  $("#nombre_usuario").val("");
    $("#fecha_ajuste_stock").val("");
    $("#motivo_ajuste_stock").val("");
}
function agregarLinea() {
    $("#id_ajuste_stock_detalle").val("0");
    $("#id_producto").val("0");
    $("#nombre_producto").val("");
    $("#preciocompra_producto").val("0");
    $("#cantidad_ajuste_stock").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_producto").focus();
    $("#id_producto").select();
    $("#botonRestarLinea").prop('disabled', false);
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', true);
    $("#botonEliminarLinea").prop('disabled', true);
    siguienteCampo("#horas_detalleajuste", "#botonAgregarLinea", true);
}
function RestarLinea() {
    $("#id_ajuste_stock_detalle").val("0");
    $("#id_producto").val("0");
    $("#nombre_producto").val("");
    $("#precio_compra_producto").val("0");
    $("#cantidad_ajuste_stock").val("0");
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#id_producto").focus();
    $("#id_producto").select();
    //   $("#botonRestarLinea").prop('disabled', false);
    $("#botonAgregarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', true);
    siguienteCampo("#horas_detalleajuste", "#botonAgregarLinea", false);
}
function editarLinea(id) {
    $("#id_ajuste_stock_detalle").val(id);
    $("#panelLinea").fadeIn("slow");
    $("#panelPrograma").fadeOut("slow");
    $("#cantidad_ajuste_stock").focus();
    $("#cantidad_ajuste_stock").select();
    $("#botonAgregarLinea").prop('disabled', false);
    $("#botonRestarLinea").prop('disabled', true);
    $("#botonModificarLinea").prop('disabled', false);
    $("#botonEliminarLinea").prop('disabled', false);
    buscarIdAjusteDetalle();
    //siguienteCampo("#cantidad_ajuste_stock", "#botonModificarLinea", true);
}
// ajustesproductos
function buscarIdAjusteDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdAjusteDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_producto").val(json.id_producto);
            $("#nombre_producto").val(json.nombre_producto);
            $("#preciocompra_producto").val(json.preciocompra_producto);

            $("#cantidad_ajuste_stock").val(json.cantidad_ajuste_stock);

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
function SumarAjusteDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_ajuste = $("#id_ajuste_stock").val();
    datosFormulario += "&id_ajuste_stock=" + id_ajuste;

    alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/SumarAjusteDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdAjuste();
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
function RestarAjusteDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_ajuste = $("#id_ajuste_stock").val();
    datosFormulario += "&id_ajuste_stock=" + id_ajuste;
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/RestarAjusteDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {

            if (json.mensaje === "Datos agregados.") {
                $("#panelLinea").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
                buscarIdAjuste();
                abrirR();
            } else {
                //alert("el producto ya existe en el pedido");
                abrirRe();

                $("#mensajes").html(json.mensaje);

            }

        },
        error: function (e) {
            alert("datos repetidos");
            $("#mensajes").html("No se ;pudo agregar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function modificarAjusteDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_ajuste = $("#id_ajuste").val();
    datosFormulario += "&id_ajuste=" + id_ajuste;
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarAjusteDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#panelLinea").fadeOut("slow");
            $("#panelPrograma").fadeIn("slow");
            buscarIdAjuste();
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
function p(id) {

    $("#id_ajuste_stock_detalle").val(id);

    var datosFormulario = $("#formLinea").serialize();
    // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdAjusteDetalle.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_producto").val(json.id_producto);
            $("#nombre_producto").val(json.nombre_producto);
            $("#preciocompra_producto").val(json.preciocompra_producto);
            $("#cantidad_ajuste_stock").val(json.cantidad_ajuste_stock);
            eliminarAjusteDetalle();
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
function eliminarAjusteDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_ajuste_stock = $("#id_ajuste_stock").val();
    datosFormulario += "&id_ajuste_stock=" + id_ajuste_stock;
    // alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarAjusteDetalle.jsp',
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
            buscarIdAjuste();

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

function eliminarTodoAjusteDetalle() {
    var datosFormulario = $("#formLinea").serialize();
    var id_ajuste_stock = $("#id_ajuste_stock").val();
    datosFormulario += "&id_ajuste_stock=" + id_ajuste_stock;
    alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminarTodoAjusteDetalle.jsp',
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
            buscarIdAjuste();

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
//// productos
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
            //$("#precio_venta").val(json.precio_venta);
            $("#preciocompra_producto").val(json.preciocompra_producto);
            // alert(json.codigo_producto);
            $("#cantidad_ajuste_stock").focus();
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
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {

            }
        }
    });
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
        return false;
    }
}
function soloNumeros(e) {
    var key = window.Event ? e.which : e.keyCode();
    return (key >= 48 && key <= 57);
}
/*function validarFormulario() {
 var valor = true;
 if ($("#nombre_categoria").val().length < 3) {
 valor = false;
 $("#mensajes").html("Nombre categorias no puede estar vacio.");
 $("#nombre_categoria").focus();
 }
 return valor;
 }*/
function abrir() {
    document.getElementById("vent").style.display = "block";

}

function cerrar() {
    document.getElementById("vent").style.display = "none";
    $("#id_ajuste_stock").focus;
    $("#id_ajuste_stock").select();
}

function abrirR() {
    document.getElementById("Restar").style.display = "block";

}

function cerrarR() {
    document.getElementById("Restar").style.display = "none";

}

function abrirRe() {
    document.getElementById("Repetido").style.display = "block";

}

function cerrarRe() {
    document.getElementById("Repetido").style.display = "none";
    $("#id_producto").val("0");
    $("#nombre_producto").val("");
    $("#preciocompra_producto").val("");
    $("#id_producto").focus();
    $("#cantidad_ajuste_stock").val("");
    $("#botonSalir").prop('disabled', false);

}