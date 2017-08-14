$(document).ready(function() {

	$("#id_preso").change(function() {
		var valor = $("#id_preso").val();
		if (valor = -1) {
			$.post("RegistrarEntrevista", {
				cedula : $("#cedulaVisitante").val()
			}, function(data, status) {
				document.location.href = "RegistrarEntrevista";
			});

		}
	});

});
