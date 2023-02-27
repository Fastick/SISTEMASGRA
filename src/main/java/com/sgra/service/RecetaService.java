package com.sgra.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sgra.interfaceservice.IRecetaService;
import com.sgra.interfaz.IReceta;
import com.sgra.modelo.Receta;

@Service
public class RecetaService implements IRecetaService{

	@Autowired
	private IReceta data;
	@Override
	public List<Receta> listar() {
		
		return (List<Receta>)data.findAll();
	}

	@Override
	public Optional<Receta> listarId(Long idreceta) {
		
		return data.findById(idreceta);
	}

	@Override
	public long save(Receta p) {
		int resp=0;
		Receta receta = data.save(p);
		if(!receta.equals(null)) {
			resp =1;
		}
		return resp;
	}
	

	@Override
	public void delete(Long idreceta) {
		data.deleteById(idreceta);
		
	}





	@Override
	public List<Receta> findByRecetas(Long idreceta) {
	
		return data.findByIdreceta(idreceta);
	}

	
	@Override
	public Receta BuscarId(Long idreceta) {
	
		return data.BuscarId(idreceta);
	}
/*
	@Override
	public Receta findByReceta(String nombre_receta, String etapa, Cultivo idcultivo, int dosificacion, Enfermedad idenfermedad) {
		
		return data.findByReceta(nombre_receta, etapa, idcultivo, dosificacion, idenfermedad);
	}
 */
	@Override
	public Receta buscarReceta(String etapa) {
	
		return data.buscarReceta(etapa);
	}

	@Override
	public List<Receta> buscarEnfermedadEtapa(Long enfer, String etapa) {
		
		return (List<Receta>)data.BuscarEnfermedadReceta(enfer, etapa);
	}

	@Override
	public List<Receta> buscarHerbicida(String etapa) {
		
		return (List<Receta>)data.buscarHerbicida(etapa);
	}

	




}
