/**
 * 
 */




$(document).ready(function () {
	
	$("#ingresarcelda").on("click", function(){		
		cargarenDiv("/ingresarCelda");
	});
	
	$("#ingresarbloque").on("click", function(){		
		cargarenDiv("/ingresarBloque");
	});
});


function cargarenDiv(ruta) {
    $("#contenido").load(ruta);	
}