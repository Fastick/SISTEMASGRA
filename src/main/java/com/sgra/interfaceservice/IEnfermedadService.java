package com.sgra.interfaceservice;

import java.util.List;
import java.util.Optional;

import com.sgra.modelo.Enfermedad;

public interface IEnfermedadService {

	public List<Enfermedad>listar();
	
	public Optional<Enfermedad>listarId(Long idenfermedad);
	public long save(Enfermedad p);
	public void delete(Long idenfermedad);
	public List<Enfermedad> findByIdcultivo(Long idcultivo);
	public List<Enfermedad> listarIdEnfermedad(Long idenfermedad);
	public Optional<Enfermedad> buscarRepetido(String nombre_enfermedad,Long idcultivo);
}
