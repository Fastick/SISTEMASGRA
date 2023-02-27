package com.sgra.interfaz;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sgra.modelo.Enfermedad;

@Repository
public interface IEnfermedad extends CrudRepository<Enfermedad, Long>{

	 @Query("select e from Enfermedad e where e.idcultivo.idcultivo =:idcultivo")
	List<Enfermedad> findByIdcultivo(
			@Param("idcultivo")Long idcultivo
			);
	 @Query("select e from Enfermedad e where e.idenfermedad =:idenfermedad")
	List<Enfermedad> ListaIdEnfermedad(Long idenfermedad);
	 @Query("select e from Enfermedad e where e.nombre_enfermedad =:nombre_enfermedad and e.idcultivo.idcultivo=:idcultivo")
	 public Optional<Enfermedad> buscarRepetido(@Param("nombre_enfermedad")String nombre_enfermedad,@Param("idcultivo")Long idcultivo);
	
}
