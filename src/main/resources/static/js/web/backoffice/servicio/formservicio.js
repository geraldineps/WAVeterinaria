$(document).on("click", "#btnagregar", function(){
    $("#txtnombreservicio").val("");
    $("#txtprecioservicio").val("");
    $("#hddidservicio").val("0");
    $("#modalservicio").modal("show");
});

$(document).on("click", ".btnactualizar", function()
{
    $("#txtnombreservicio").val($(this).attr("data-servicioname"));
    $("#txtprecioservicio").val($(this).attr("data-servicioprecio"));
    $("#hddidservicio").val($(this).attr("data-serviciocod"));
    $("#modalservicio").modal("show");
})

$(document).on("click", "#btnguardar", function(){
    $.ajax({
        type: "POST",
        url: "/servicio/register",
        contentType: "application/json",
        data: JSON.stringify({
            codigoservicio: $("#hddidservicio").val(),
            nombreservicio: $("#txtnombreservicio").val(),
            precio: $("#txtprecioservicio").val(),
        }),
        success: function(resultado){
            if(resultado.respuesta){
                listarServicios()
            }
            alert(resultado.mensaje);
        }
    });
    $("#modalservicio").modal("hide");
});

function listarServicios(){
    $.ajax({
        type: "GET",
        url: "/servicio/list",
        dataType: "json",
        success: function(resultado){
            $("#tblservicio > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblservicio > tbody").append(`<tr>`+
                `<td>${value.codigoservicio}</td>`+
                `<td>${value.nombreservicio}</td>`+
                `<td>${value.precio}</td>`+
                `<td><button type='button' class='btn btn-primary btnactualizar' `+
                    `data-serviciocod="${value.codigoservicio}" `+
                    `data-servicioname="${value.nombreservicio}" `+
                    `data-servicioprecio="${value.precio}">Actualizar`+
                `</button></td>`+
                `</tr>`);
            });
        }
    });
}