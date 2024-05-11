$(document).on("click", "#btnagregar", function(){
    $("#txtnombrecliente").val("");
    $("#txtapellidocliente").val("");
    $("#hddidcliente").val("0");
    $("#txtcorreocliente").val("");
    $("#txtdireccioncliente").val("");
    $("#txttelefonocliente").val("");
    $("#txtdnicliente").val("");
    $("#modalcliente").modal("show");
});

$(document).on("click", ".btnactualizar", function()
{
    $("#txtnombrecliente").val($(this).attr("data-clientename"));
    $("#txtapellidocliente").val($(this).attr("data-clienteape"));
    $("#hddidcliente").val($(this).attr("data-clientecod"));
    $("#txtcorreocliente").val($(this).attr("data-clientecorreo"));
    $("#txtdireccioncliente").val($(this).attr("data-clientedirec"));
    $("#txttelefonocliente").val($(this).attr("data-clientetelef"));
    $("#txtdnicliente").val($(this).attr("data-clientedni"));
    $("#modalcliente").modal("show");
})

$(document).on("click", "#btnguardar", function(){
    $.ajax({
        type: "POST",
        url: "/cliente/register",
        contentType: "application/json",
        data: JSON.stringify({
            codigocliente: $("#hddidcliente").val(),
            nombrecli: $("#txtnombrecliente").val(),
            apellidoscli: $("#txtapellidocliente").val(),
            correo: $("#txtcorreocliente").val(),
            direccion: $("#txtdireccioncliente").val(),
            telefono: $("#txttelefonocliente").val(),
            dni: $("#txtdnicliente").val(),
        }),
        success: function(resultado){
            if(resultado.respuesta){
                listarClientes()
            }
            alert(resultado.mensaje);
        }
    });
    $("#modalcliente").modal("hide");
});

function listarClientes(){
    $.ajax({
        type: "GET",
        url: "/cliente/list",
        dataType: "json",
        success: function(resultado){
            $("#tblcliente > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblcliente > tbody").append(`<tr>`+
                `<td>${value.codigocliente}</td>`+
                `<td>${value.nombrecli}</td>`+
                `<td>${value.apellidoscli}</td>`+
                `<td>${value.correo}</td>`+
                `<td>${value.direccion}</td>`+
                `<td>${value.telefono}</td>`+
                `<td>${value.dni}</td>`+
                `<td><button type='button' class='btn btn-success btn-sm btnactualizar' `+
                    `data-clientecod="${value.codigocliente}" `+
                    `data-clientename="${value.nombrecli}" `+
                    `data-clienteape="${value.apellidoscli}" `+
                    `data-clientecorreo="${value.correo}" `+
                    `data-clientedirec="${value.direccion}"`+
                    `data-clientetelef="${value.telefono}"`+
                    `data-clientedni="${value.dni}"><i class="bi bi-pencil-square"></i> Actualizar`+
                `</button></td>`+
                `</tr>`);
            });
        }
    });
}

//Solo numeros en caja de texto
function validarSoloNumerosEnteros(e) { // 1
	tecla = (document.all) ? e.keyCode : e.which; // 2
	if (tecla == 8) return true; // 3
	patron = /[0-9]/;// Solo acepta números
	te = String.fromCharCode(tecla); // 5
	return patron.test(te); // 6
}
