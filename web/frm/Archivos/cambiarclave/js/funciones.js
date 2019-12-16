/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function buscarIdUsuarioClave() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdClave.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {

            $("#mensajes").html("Enviando datos al Servidor ...");

        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_usuario").val(json.id_usuario);
            $("#usuario_usuario").val(json.usuario_usuario);
            $("#nombre_usuario").val(json.nombre_usuario);
            $("#claveactual_usuario").focus();
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

function modificarClave() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/modificarClave.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("enviado al servidor...");

        },
        succeess: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#usuario_usuario").focus();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            limpiarFormulario();
            $("#nombre_usuario").focus();
        }
    });
}

function validarFormulario() {
    var valor = true;
    if ($("#claveactual_usuario").val().trim() === "") {
        valor = false;
        $("#mensajes").html("El campo Clave Actual no puede estar vacio.");
        $("#claveactual_usuario").focus();
    } else if ($("#clavenueva_usuario").val().trim() === "") {
        valor = false;
        $("#mensajes").html("El campo Clave nueva no puede estar vacio.");
        $("#clavenueva_usuario").focus();
    }
    return valor;
}
function limpiarFormulario() {
    $("#id_usuario").focus;
    $("#claveactual_usuario").val("");
    $("#claveactual_usuario").focus;
    $("#clavenueva_usuario").val("");
}
function siguienteCampo(actual, siguiente, preventDefault) {
    $(actual).keydown(function (event) {
        if (event.which === 13) {
            if (preventDefault) {
                event.preventDefault();
            }
            $(siguiente).focus();
            $(siguiente).select();
        }
    });
}

function validarFormulario2() {

    var valor = true;
    var pswd = $("#clavenueva_usuario").val();
    var log = $("#usuario_usuario").val();
    var nom = $("#nombre_usuario").val();

    if (nom === "") {
        valor = false;
        $("#nombre_usuario").val("");
        $("#nombre_usuario").focus();
        $("#mensajes").html("Nombre no puede estar vacio.");

    } else {
        if (log === "") {
            valor = false;

            $("#usuario_usuario").val("");
            $("#usuario_usuario").focus();
            $("#mensajes").html("Usuario no puede estar vacia.");
        } else {
            if (pswd === "") {
                valor = false;

                $("#clavenueva_usuario").val("");
                $("#clavenueva_usuario").focus();
                $("#mensajes").html("Contraseña no puede estar vacia.");

            } else {
                if (pswd.length < 5) {
                    valor = false;

                    $("#clavenueva_usuario").val("");
                    $("#clavenueva_usuario").focus();
                    $("#mensajes").html("Clave Nueva ser superior a 5 caracteres.");
                } else {

                    if (pswd.search(/[A-Z]/) === -1) {
                        valor = false;

                        $("#clavenueva_usuario").val("");
                        $("#clavenueva_usuario").focus();
                        $("#mensajes").html("Clave debe tener al menos una letra MAYUSCULA");
                    } else {
                        if (pswd.search(/[a-z]/) === -1) {
                            valor = false;

                            $("#clavenueva_usuario").val("");
                            $("#clavenueva_usuario").focus();
                            $("#mensajes").html("Clave debe tener al menos una letra minuscula");
                        } else {
                            if (pswd.search(/[0-9]/) === -1) {
                                valor = false;

                                $("#clavenueva_usuario").val("");
                                $("#clavenueva_usuario").focus();
                                $("#mensajes").html("Clave debe tener al menos un número");
                            }
                            return valor;
                        }
                    }
                }
            }
        }
    }
}
