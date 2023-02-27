package com.sgra.interfaz;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sgra.modelo.Cultivo;


@Repository
public interface ICultivo extends CrudRepository<Cultivo, Long> {
 
	@Query("select p from Cultivo p where p.nombre_cultivo = :nombre_cultivo and p.variedad =:variedad")
	Cultivo findByNombreCultivo(@Param("nombre_cultivo")String nombre_cultivo, @Param("variedad")String variedad);
	
}
