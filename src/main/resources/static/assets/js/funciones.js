function eliminar(id) {
	console.log(id);
	swal({
		  title: "Esta seguro de Eliminar?",
		  text: "Una vez eliminado no se puede restablecer",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		})
		.then((OK) => {
		  if (OK) {
			  $.ajax({
				 url:"/eliminar/"+id,
				 success: function(res) {
					console.log(res);
				},			
			  });
		    swal("Registro eliminado", {
		      icon: "success",
		    }).then((ok)=>{
		    	if(ok){
		    		location.href="/Listar/";
		    	}
		    });
		  } 
		});
}