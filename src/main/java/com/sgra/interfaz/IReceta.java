package com.sgra.interfaz;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sgra.modelo.Receta;

@Repository
public interface IReceta extends CrudRepository<Receta, Long>{
//buscar producto
	List<Receta> findByIdreceta(Long idreceta);
	/*
	 @Query("select r from Receta r where r.nombre_receta =:nombre_receta and r.etapa=:etapa and r.idcultivo=:idcultivo and r.dosificacion=:dosificacion and r.idenfermedad=:idenfermedad ")
	 Receta findByReceta(
			 @Param("nombre_receta")String nombre_receta,
			 @Param("etapa")String etapa,
			 @Param("idcultivo")Cultivo idcultivo,
			 @Param("dosificacion")int dosificacion,
			 @Param("idenfermedad")Enfermedad idenfermedad);
	 */
	 
	 @Query("select r from Receta r where r.idreceta =:idreceta")
	Receta BuscarId(@Param("idreceta")Long idreceta);
	 
	// @Query("select r from Receta r where r.idenfermedad =:idenfermedad")
		//Receta BuscarEnfermedad(@Param("idenfermedad")Long idenfermedad);
	 
	
	 
	 @Query("select r from Receta r where r.etapa=:etapa")
	 Receta buscarReceta(
	 		@Param("etapa")String etapa);
	 
	 
	
	 @Query("select p from Receta p where p.idenfermedad.idenfermedad=:idenfermedad and p.etapa=:etapa")
	List<Receta> BuscarEnfermedadReceta(
				@Param("idenfermedad")Long idenfermedad,
				@Param("etapa")String etapa
				);

	 @Query("select r from Receta r where r.etapa=:etapa")
	 List<Receta> buscarHerbicida(@Param("etapa")String etapa);

		
	
}
