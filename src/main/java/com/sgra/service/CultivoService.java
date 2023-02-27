package com.sgra.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgra.interfaceservice.ICultivoService;
import com.sgra.interfaz.ICultivo;
import com.sgra.modelo.Cultivo;



@Service
public class CultivoService implements ICultivoService {

	@Autowired
	private ICultivo data;
	
	@Override
	public List<Cultivo> listar() {
		
		return (List<Cultivo>)data.findAll();
	}

	@Override
	public Optional<Cultivo> listarId(Long idcultivo) {
		
		return data.findById(idcultivo);
	}

	@Override
	public long save(Cultivo p) {
		int resp=0;
		Cultivo cultivo = data.save(p);
		if(!cultivo.equals(null)) {
			resp =1;
		}
		return resp;
	}

	@Override
	public void delete(Long idcultivo) {
		data.deleteById(idcultivo);
		
	}

	@Override
	public Cultivo findByNombreCultivo(String nombre_cultivo, String variedad) {
		return data.findByNombreCultivo(nombre_cultivo, variedad);
	}

	


	
	
	
	
}
