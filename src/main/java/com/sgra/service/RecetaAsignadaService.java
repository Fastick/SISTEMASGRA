package com.sgra.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgra.interfaceservice.IRecetaAsignadaService;
import com.sgra.interfaz.IRecetaAsignada;
import com.sgra.modelo.Receta_Asignada;


@Service
public class RecetaAsignadaService implements IRecetaAsignadaService{

	@Autowired 
	private IRecetaAsignada data;
	@Override
	public List<Receta_Asignada> listar() {
		
		return (List<Receta_Asignada>)data.findAll();
	}

	@Override
	public Optional<Receta_Asignada> listarId(Long idrecetaasignada) {
		return data.findById(idrecetaasignada);
	}

	@Override
	public long save(Receta_Asignada p) {
		int resp=0;
		Receta_Asignada pa = data.save(p);
		if(!pa.equals(null)) {
			resp =1;
		}
		return resp;
	}

	@Override
	public void delete(Long idrecetaasignada) {
		data.deleteById(idrecetaasignada);
		
	}
/*
	@Override
	public List<Receta_Asignada> findByCliente(Cliente idcliente) {
		// TODO Auto-generated method stub
		return data.findByIdcliente(idcliente);
	}

	@Override
	public Receta_Asignada BuscarRA(Usuario id, Receta idreceta, String fecha) {
		return data.BuscarRA(id, idreceta, fecha);
	}

*/

	@Override
	public List<Receta_Asignada> BuscarRA(String email) {
		
		return (List<Receta_Asignada>)data.BuscarRA(email);
	}

	@Override
	public List<Receta_Asignada> buscarFecha(String des, String has) {
	
		
		return (List<Receta_Asignada>)data.buscarFecha(des, has);
	}

	@Override
	public List<Receta_Asignada> buscarReceta(Long idreceta) {
		
		return (List<Receta_Asignada>)data.buscarReceta(idreceta);
	}

	@Override
	public int contador(String estado) {
		
		return data.contador(estado);
	}

	
	


}
