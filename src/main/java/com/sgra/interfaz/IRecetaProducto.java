package com.sgra.interfaz;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sgra.modelo.Producto;
import com.sgra.modelo.Receta;
import com.sgra.modelo.RecetaProducto;
@Repository
public interface IRecetaProducto extends CrudRepository<RecetaProducto, Long>{
	

	@Query("select p from RecetaProducto p where p.receta.idreceta=:idreceta")
	List<RecetaProducto> findByRecetaProducto(@Param("idreceta")Long idreceta);
	

	@Query("select p from RecetaProducto p where p.receta.idreceta=:idreceta and p.producto.idproducto=:idproducto")
	RecetaProducto BuscarRepetido(
			@Param("idreceta")Long idreceta,
			@Param("idproducto")Long idproducto
			);
	
	
	@Query("select p from Receta p where p.idreceta=:idreceta")
	Receta BuscarDosificacion(@Param("idreceta")Long idreceta);
	

	@Query("select p from Producto p where p.contenido>:con and p.contenido<:max")
	List<Producto> Clasificador(@Param("con")float con,@Param("max")float max);

	
	
	

}
