/**
 * 
 */

$(document).ready(function() {
	$("#ingresarbloquebutton").on("click",function(){
		ingresarBloque("/ingresarBloqueDos");
	});
});


function ingresarBloque(ruta) {
	//$("#contenido").html(ruta);
	var cadena = '<div id="prod" align="center"> <h3 class="text-success">BLOQUE</h3><table id="jqGrid"></table> <div id="jqGridPager"> </div>\n\
        <div class="text-center modal-header"> \n\
        </div></div>';
    $("#contenido").html(cadena);
    jQgridBloques();
}

function jQgridBloques(){
	$.jgrid.defaults.responsive = true;
	$.jgrid.defaults.styleUI = 'Bootstrap';
        jQuery("#jqGrid").jqGrid({
            //url:'../../PeticjqGridionesJsPedidos',
            datatype: "local",//json
            colNames:['Id Bloque','Id Patio', '      '],
            colModel:[
                    {name:'Id Bloque', width:40, key:true, formatter: 'integer',searchoptions: { sopt: ['eq', 'ne', 'gt']}},
                    {name:'Id Patio', width:40,formatter: 'integer' ,searchoptions: { sopt: ['eq', 'ne', 'cn']}},
                    {name:'      ', width:40,formatter: 'integer', search:false}		
            ],
            //mtype: "POST",
            //postData:{Operacion:"allProductos"}, //La operacion que se va a enviar ejemplo all productos
            loadonce: true,
            width: 850,
            height: 300,
            rowList:[10,20,30],
            pager: '#jqGridPager',
            viewrecords: true,
            //caption:"Tabla de Bloques",
            recordtext: "Ver {0} - {1} de {2}",
            emptyrecords: "No Existen datos para mostrar",
            loadtext: "Cargando...",
            pgtext : "Pagina {0} de {1}"
            
        });
        jQuery("#jqGrid").jqGrid('navGrid','#jqGridPager',{search:true,del:false,add:false,edit:false},{},
            {},
            {},
            {
                caption: "Buscar...",
                Find: " Buscar",
                Reset: " Cargar"
            }
        );
	
}