package com.sgra.interfaz;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.sgra.modelo.Receta_Asignada;

@Repository
public interface IRecetaAsignada extends JpaRepository<Receta_Asignada, Long> {

	
	/*List<Receta_Asignada> findByIdcliente(Cliente idcliente);*/
	
	@Query("select r from Receta_Asignada r where r.id.email=:email ")
	public List<Receta_Asignada> BuscarRA(@Param("email")String email	);
	
	@Query("SELECT ra FROM Receta_Asignada ra where ra.fecha BETWEEN :des AND :has ")
	public List<Receta_Asignada> buscarFecha(@Param("des")String des, @Param("has")String has);
	@Query("select r from Receta_Asignada r where r.idreceta.idreceta=:idreceta ")
	public List<Receta_Asignada> buscarReceta(@Param("idreceta")Long idreceta);
	@Query("select count(*) from Receta_Asignada  where estado=:estado")
	public int contador(@Param("estado")String estado);
}
