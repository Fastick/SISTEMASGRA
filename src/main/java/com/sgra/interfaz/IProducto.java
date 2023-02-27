package com.sgra.interfaz;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sgra.modelo.Producto;




@Repository
public interface IProducto extends CrudRepository<Producto, Long>{

	 @Query("select p from Producto p where p.nombre_producto = :nombre_producto and p.contenido = :contenido and p.unidad = :unidad")
	 Producto findByNombre_producto(@Param("nombre_producto")String nombre_producto, @Param("contenido")float contenido,@Param("unidad")String unidad );

	 
}
