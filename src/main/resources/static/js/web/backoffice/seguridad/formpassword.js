$(document).on("click", "#btnguardar", function(){
    $.ajax({
        type: "POST",
        url: "/seguridad/usuario/password",
        contentType: "application/json",
        data: JSON.stringify({
            idusuario: $("#hddidusuario").val(),
            passwordActual: $("#password1").val(),
            passwordNuevo: $("#password2").val(),
            passwordConfirmacion: $("#password3").val(),

        }),
        success: function(resultado){
            alert(resultado.mensaje);
        }
    });
});