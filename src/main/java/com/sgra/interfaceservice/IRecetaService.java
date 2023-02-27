package com.sgra.interfaceservice;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import com.sgra.modelo.Receta;

public interface IRecetaService {
	public List<Receta>listar();
	public Optional<Receta>listarId(Long idreceta);
	public List<Receta> findByRecetas(Long idreceta);
	///public Receta findByReceta(String nombre_receta,String etapa,Cultivo cultivo,int dosificacion,Enfermedad enfermedad);
	public Receta buscarReceta(String etapa);
	public Receta BuscarId(Long idreceta);
	public long save(Receta p);
	public void delete(Long idreceta);
	public List<Receta> buscarEnfermedadEtapa(Long enfer, String etapa);
	public List<Receta> buscarHerbicida(String etapa);
	
	
}
