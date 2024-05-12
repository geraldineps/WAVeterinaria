//Al pulsar el botón cliente
$(document).on("click", "#id_btnCliente", function(){
    muestraCliente();
    $("#idBuscaCliente").modal("show");
});

//Al pulsar el botón servicio
$(document).on("click", "#id_btnServicio", function(){
    muestraServicio();
    $("#idBuscaServicio").modal("show");
});

//Al escribir en la caja de texto del cliente
$(document).on("keyup", "#id_txtCliente", function(e){
	muestraCliente();
});

//Al escribir en la caja de texto del servicio
$(document).on("keyup", "#id_txtServicio", function(e){
	muestraServicio();
});

//Se anula el enter
$(document).on("keypress", "form", function (event) {
	return event.keyCode != 13;
});


        //Al pulsar selecciona cliente
		function f_seleccione_cliente(id, nombre, apellido, dni) {
			$("#id_cliente_id").val(id);
			$("#id_cliente_nombre").val(nombre);
			$("#id_cliente_apellido").val(apellido);
			$("#id_cliente_dni").val(dni);
			$("#idBuscaCliente").modal("hide");
		}

        //Al pulsar selecciona servicio
		function f_seleccione_servicio(id, nombre, precio) {
			$("#id_servicio_id").val(id);
			$("#id_servicio_nombre").val(nombre);
			$("#id_servicio_precio").val(precio);
			$("#idBuscaServicio").modal("hide");
		}

        function muestraCliente() {
			var var_cliente = $("#id_txtCliente").val();
			//limpiar la tabla
			$("#id_table_cliente tbody").empty();
			//Se añade los clientes a la tabla
			$.getJSON("cargaCliente", {"filtro": var_cliente}, function (data) {
				$.each(data, function (index, item) {
					$('#id_table_cliente').append("<tr><td>" + item.codigocliente + "</td><td>" + item.nombrecli + "</td><td>" + item.apellidoscli + "</td><td>" + item.dni + "</td><td><button type='button' class='btn btn-success btn-sm' aria-label='Left Align' onclick=\"f_seleccione_cliente('" + item.codigocliente + "','" + item.nombrecli +"','" + item.apellidoscli + "','" + item.dni + "');\" ><i class='bi bi-check2-square'></i></button></td></tr>");
				});
			});
		}

        function muestraServicio() {
			var var_servicio = $("#id_txtServicio").val();
			//limpiar la tabla
			$("#id_table_servicio tbody").empty();
			//Se añade los Productos a la tabla
			$.getJSON("cargaServicio", {"filtro": var_servicio}, function (data) {
				$.each(data, function (index, item) {
					$('#id_table_servicio').append("<tr><td>" + item.codigoservicio + "</td><td>" + item.nombreservicio + "</td><td>" + item.precio + "</td><td><button type='button' class='btn btn-success btn-sm' aria-label='Left Align' onclick=\"f_seleccione_servicio('" + item.codigoservicio + "','" + item.nombreservicio + "','" + item.precio + "');\" ><i class='bi bi-check2-square'></i></button></td></tr>");
				});
			});
		}

$(document).ready(function () {
	//Se añade los servicio a la tabla
    $.getJSON("listaSeleccion", {}, function (data) {
        $.each(data, function (index, item) {
            $('#id_table_boleta_body').append("<tr><td>" + item.codigoservicio + "</td><td>" + item.nombreservicio + "</td><td>" + item.precio + "</td><td><button type='button' onclick='f_elimina_seleccion(" + item.codigoservicio + ");' class='btn btn-default btn-sm' aria-label='Left Align' ><i class='bi bi-x-square'></i></button></td></tr>");
        });
    });
});

//Al pulsar el botón eliminar
function f_elimina_seleccion(id) {
	//limpiar la tabla
	$("#id_table_boleta_body").empty();

	//Se añade los clientes a la tabla
	$.getJSON("eliminaSeleccion", {"codigoservicio": id}, function (data) {
		$.each(data, function (index, item) {
			$('#id_table_boleta_body').append("<tr><td>" + item.codigoservicio + "</td><td>" + item.nombreservicio + "</td><td>" + item.precio + "</td><td><button type='button' onclick='f_elimina_seleccion(" + item.codigoservicio + ");' class='btn btn-default' aria-label='Left Align' ><i class='bi bi-x-square'></button></td><tr>");
		});
	});

	//limpia las cajas de texto
	$("#id_servicio_id").val("-1");
	$("#id_servicio_nombre").val("");
	$("#id_servicio_precio").val("");
}



        //Al pulsar el botón agregar
        $(document).on("click", "#id_btnAgregar", function(){
			var var_pro = $("#id_servicio_id").val();

			//Validar duplicados
			var yaExiste = false;
			$("#id_table_boleta_body tr").each(function () {
				if ($(this).find('td:eq(0)').html() == var_pro) {
					yaExiste = true;
				}
			});

			if (var_pro == '-1') {
				$("#idMensajeTexto").text("Añada servicios a la lista.");
				$("#idMensaje").modal("show");
			} else if (yaExiste) {
				$("#idMensajeTexto").text("El servicio elegido ya existe.");
				$("#idMensaje").modal("show");
			} else {

				var var_nom = $("#id_servicio_nombre").val();
				var var_pre = $("#id_servicio_precio").val();

				//limpiar la tabla
				$("#id_table_boleta_body").empty();

				var jsonParam = {"codigoservicio": var_pro, "nombreservicio": var_nom, "precio": var_pre};

				$.ajax({
					url: 'agregarSeleccion',
					type: 'POST',
					dataType: 'json',
					data: jsonParam,
					success: function (data) {
						console.log(data);
						if (data != null) {
							$.each(data, function (index, item) {
								$('#id_table_boleta_body').append("<tr><td>" + item.codigoservicio + "</td><td>" + item.nombreservicio + "</td><td>" + item.precio + "</td><td><button type='button' onclick='f_elimina_seleccion(" + item.codigoservicio + ");' class='btn btn-default' aria-label='Left Align' ><i class='bi bi-x-square'></i></button></td></tr>");
							});

						} else
							swal("Error al agregar la selección del servicio", "", "error");
						return false;
					},
					error: function (jqXhr) {
						swal("Error en la conexión", "", "error");
					}
				});

				//limpia las cajas de texto
				$("#id_servicio_id").val("-1");
				$("#id_servicio_nombre").val("");
				$("#id_servicio_precio").val("");
			}
		});




        //Al pulsar el botón registrar
        $(document).on("click", "#id_btnRegistrar", function(){
            var var_id_u = $("#id_usuario_id").val();
            var var_nom_u = $("#id_usuario_nombre").val();
			var var_cli = $("#id_cliente_id").val();
			var var_dni = $("#id_cliente_dni").val();
			var var_nom = $("#id_cliente_nombre").val();
			var var_ape = $("#id_cliente_apellido").val();
			//var var_nom_com =  var_nom + ' ' + var_ape;

			var var_count = 0;
			$("#id_table_boleta_body tr").each(function () {
				var_count = var_count + 1;
			});

			if (var_cli == "-1") {
				$("#idMensajeTexto").text("Seleccione un cliente");
				$("#idMensaje").modal("show");
			} else if (var_count < 1) {
				$("#idMensajeTexto").text("Seleccione un servicio");
				$("#idMensaje").modal("show");
			} else {

				var jsonParam = {"idusuario":var_id_u,"nombres" : var_nom_u,"codigocliente": var_cli, "nombrecli": var_nom, "apellidoscli": var_ape, "dni":var_dni};

				$.ajax({
					url: 'registraBoleta',
					type: 'POST',
					dataType: 'json',
					data: jsonParam,
					success: function (data) {
						if (data.texto != "-1") {
							console.log(data.texto);
							$("#idMensajeTexto").html(data.texto);
							$("#idMensaje").modal("show");
							$("#id_table_boleta_body").empty();
                            $("#id_usuario_id").val("-1");
                            $("#id_usuario_nombre").val("");
							$("#id_cliente_id").val("-1");
							$("#id_cliente_nombre").val("");
							$("#id_cliente_apellido").val("");
							$("#id_cliente_dni").val("");
						} else
							swal("Error al agregar la Boleta", "", "error");
						return false;
					},
					error: function (jqXhr) {
						swal("Error en la conexión", "", "error");
					}
				});

			}
		});


$(document).on("click", "#id_btnNuevo", function(){
    $("#txtnombrecliente").val("");
    $("#txtapellidocliente").val("");
    $("#hddidcliente").val("0");
    $("#txtcorreocliente").val("");
    $("#txtdireccioncliente").val("");
    $("#txttelefonocliente").val("");
    $("#txtdnicliente").val("");
    $("#modalcliente").modal("show");
});


$(document).on("click", "#btnguardarCliente", function(){
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
            alert(resultado.mensaje);
        }
    });
    $("#modalcliente").modal("hide");
});

