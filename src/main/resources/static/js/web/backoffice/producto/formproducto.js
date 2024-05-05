$(document).on("click", "#btnagregar", function(){
    $("#txtnomproduct").val("");
    $("#txtmarca").val("");
    $("#hddprodcod").val("0");
    listarCboCategoryEspecie(0, 0);
    $("#txtprecio").val("");
    $("#modalproduct").modal("show");
});


$(document).on("click", ".btnactualizar", function(){
    $("#txtnomproduct").val($(this).attr("data-prodnom"));
    $("#txtmarca").val($(this).attr("data-prodmarca"));
    $("#hddprodcod").val($(this).attr("data-prodcod"));
    $("#txtprecio").val($(this).attr("data-prodprecio"));
    $("#cbocategory").empty();
    $("#cboespecie").empty();
    listarCboCategoryEspecie($(this).attr("data-prodcateg"),
                $(this).attr("data-prodespe"));
    $("#modalproduct").modal("show");
})

$(document).on("click", "#btnguardar", function(){
    $.ajax({
        type: "POST",
        url: "/producto/register",
        contentType: "application/json",
        data: JSON.stringify({
            codigoproducto: $("#hddprodcod").val(),
            nombreproducto: $("#txtnomproduct").val(),
            codigocategoria: $("#cbocategory").val(),
            marca: $("#txtmarca").val(),
            codigoespecie: $("#cboespecie").val(),
            precio: $("#txtprecio").val(),
        }),
        success: function(resultado){
            if(resultado.respuesta){
                listarProductos()
            }
            alert(resultado.mensaje);
        }
    });
    $("#modalproduct").modal("hide");
});

function listarProductos(){
    $.ajax({
        type: "GET",
        url: "/producto/list",
        dataType: "json",
        success: function(resultado){
            $("#tblproducto > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblproducto > tbody").append(`<tr>`+
                `<td>${value.codigoproducto}</td>`+
                `<td>${value.nombreproducto}</td>`+
                `<td>${value.categoria.nombrecategoria}</td>`+
                `<td>${value.marca}</td>`+
                `<td>${value.especie.descripcion}</td>`+
                `<td>${value.precio}</td>`+
                `<td><button type='button' class='btn btn-primary btnactualizar' `+
                    `data-prodcod="${value.codigoproducto}" `+
                    `data-prodnom="${value.nombreproducto}" `+
                    `data-prodcateg="${value.categoria.codigocategoria}" `+
                    `data-prodmarca="${value.marca}" `+
                    `data-prodespe="${value.especie.codigoespecie}" `+
                    `data-prodprecio="${value.precio}">Actualizar`+
                `</button></td>`+
                `</tr>`);
            });
        }
    });
}

function listarCboCategoryEspecie(codigocategoria, codigoespecie){
    $.ajax({
        type: "GET",
        url: "/categoria/get",
        dataType: "json",
        success: function(resultado){
            $.each(resultado, function(index, value){
                $("#cbocategory").append(
                    `<option value="${value.codigocategoria}">${value.nombrecategoria}</option>`
                )
            });
            if(codigocategoria > 0){
                $("#cbocategory").val(codigocategoria);
            }
            $.ajax({
                    type: "GET",
                    url: "/especie/get",
                    dataType: "json",
                    success: function(resultado){
                        $.each(resultado, function(index, value){
                            $("#cboespecie").append(
                                `<option value="${value.codigoespecie}">${value.descripcion}</option>`
                            )
                        });
                        if(codigoespecie > 0){
                            $("#cboespecie").val(codigoespecie);
                        }
                    }
                })
        }
    })
}