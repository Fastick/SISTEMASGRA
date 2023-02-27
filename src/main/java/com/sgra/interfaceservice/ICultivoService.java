package com.sgra.interfaceservice;

import java.util.List;
import java.util.Optional;

import com.sgra.modelo.Cultivo;

public interface ICultivoService {
	public List<Cultivo>listar();
	public Cultivo findByNombreCultivo(String nombre_cultivo,String variedad);
	public Optional<Cultivo>listarId(Long idcultivo);
	public long save(Cultivo p);
	public void delete(Long idcultivo);
}
