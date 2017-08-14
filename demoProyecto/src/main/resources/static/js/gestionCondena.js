/**
 * 
 */

$(document).ready(function() {
	$("#crearcondena").on("click",function(){

		crearCondena();
	});
});



function crearCondena() {
	var cadena = '<div id="prod" align="center"> <h3 class="text-success">CONDENA PRESO</h3><table id="jqGrid"></table> <div id="jqGridPager"> </div>\n\
        <div class="text-center modal-header"> \n\
		<label class="pull-left">Tipo de Condena:  </label> <input type="text" class="form-control"/><br/><br/> \n\
		<label class="pull-left">Fecha Inicio:  </label> <input type="date" class="form-control"/><br/><br/> \n\
		<label class="pull-left">Fecha Fin:  </label> <input type="date" class="form-control"/><br/><br/> \n\
		<label class="pull-left">Bloque:  </label> <input type="text" class="form-control"/><br/><br/> \n\
		<label class="pull-left">Celda:  </label> <input type="text" class="form-control"/><br/><br/> \n\
		<button id="crearcond" class="buttom btn-md btn-info"> Crear Condena </button> \n\
		</div></div>';
    $("#contenido").html(cadena);
    jQgridCondena();

    $("#crearcond").click(function(){
        alert('crear condena');
    });
    
    
}

function jQgridCondena(){
	$.jgrid.defaults.responsive = true;
	$.jgrid.defaults.styleUI = 'Bootstrap';
        jQuery("#jqGrid").jqGrid({
            url:'/PeticionPresos',
            datatype: "json",//json
            colNames:['Nombre','Apellido', 'Edad','Cedula'],
            colModel:[
                    {name:'Nombre', width:40, key:true, searchoptions: { sopt: ['eq', 'ne', 'gt']}},
                    {name:'Apellido', width:40,searchoptions: { sopt: ['eq', 'ne', 'cn']}},
                    {name:'Edad', width:40,formatter: 'integer' ,searchoptions: { sopt: ['eq', 'ne', 'cn']}},
                    {name:'Cedula', width:40,formatter: 'integer' ,searchoptions: { sopt: ['eq', 'ne', 'cn']}}
            ],
            mtype: "POST",
            postData:{Operacion:"allpresos"}, //La operacion que se va a enviar ejemplo all productos
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