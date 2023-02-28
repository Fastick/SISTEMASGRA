package com.sgra.interfaceservice;

import java.util.List;
import java.util.Optional;

import com.sgra.modelo.Receta_Asignada;

public interface IRecetaAsignadaService {

	public List<Receta_Asignada>listar();
	public Optional<Receta_Asignada>listarId(Long idrecetaasignada);
	///public List<Receta_Asignada>findByCliente(Cliente idcliente);
	///public Receta_Asignada BuscarRA(Usuario id,Receta idreceta,String fecha);
	public long save(Receta_Asignada p);
	public void delete(Long idrecetaasignada);
	public List<Receta_Asignada> BuscarRA(String email);
	public List<Receta_Asignada> buscarFecha(String des,String has);
	public List<Receta_Asignada> buscarReceta(Long idreceta);
	public int contador(String estado);
}
