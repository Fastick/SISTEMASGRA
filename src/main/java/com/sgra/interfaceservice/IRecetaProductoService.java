package com.sgra.interfaceservice;

import java.util.List;
import java.util.Optional;
import com.sgra.modelo.Producto;
import com.sgra.modelo.Receta;
import com.sgra.modelo.RecetaProducto;

public interface IRecetaProductoService {

	public List<RecetaProducto> listar();
	public Optional<RecetaProducto> listarId(Long idrecetaproducto);
	public long save(RecetaProducto p);
	public List<RecetaProducto> findByRecetaProducto(Long idreceta);
	public RecetaProducto BuscarRepetido(Long idreceta,Long idproducto);
	public List<Producto> Clasificador(float con,float max);
	public Receta BuscarDosificacion(Long idreceta);
	public void delete(Long idrecetaproducto);


	
}
