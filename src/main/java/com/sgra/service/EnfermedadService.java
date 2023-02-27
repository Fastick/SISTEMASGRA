package com.sgra.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgra.interfaceservice.IEnfermedadService;
import com.sgra.interfaz.IEnfermedad;
import com.sgra.modelo.Enfermedad;

@Service
public class EnfermedadService implements IEnfermedadService{

	@Autowired
	private IEnfermedad data;
	@Override
	public List<Enfermedad> listar() {
		return (List<Enfermedad>)data.findAll();
	}

	@Override
	public Optional<Enfermedad> listarId(Long idenfermedad) {
		return data.findById(idenfermedad);
	}

	@Override
	public long save(Enfermedad p) {
		int resp=0;
		Enfermedad en = data.save(p);
		if(!en.equals(null)) {
			resp =1;
		}
		return resp;
	}

	@Override
	public void delete(Long idenfermedad) {
		data.deleteById(idenfermedad);
		
	}

	@Override
	public List<Enfermedad> findByIdcultivo(Long idcultivo) {
	
		return (List<Enfermedad>)data.findByIdcultivo(idcultivo);
	}

	@Override
	public List<Enfermedad> listarIdEnfermedad(Long idenfermedad) {
	
		return (List<Enfermedad>)data.ListaIdEnfermedad(idenfermedad);
	}

	@Override
	public Optional<Enfermedad> buscarRepetido(String nombre_enfermedad, Long idcultivo) {
	
		return data.buscarRepetido(nombre_enfermedad,idcultivo);
	}

	


}
