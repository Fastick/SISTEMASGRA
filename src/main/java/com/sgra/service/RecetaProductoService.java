package com.sgra.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgra.interfaceservice.IRecetaProductoService;
import com.sgra.interfaz.IRecetaProducto;
import com.sgra.modelo.Producto;
import com.sgra.modelo.Receta;
import com.sgra.modelo.RecetaProducto;

@Service
public class RecetaProductoService implements IRecetaProductoService{

	@Autowired
	private IRecetaProducto data;
	@Override
	public List<RecetaProducto> listar() {
		
		return (List<RecetaProducto>)data.findAll();
	}

	@Override
	public Optional<RecetaProducto> listarId(Long idrecetaproducto) {
		
		return data.findById(idrecetaproducto);
	}

	@Override
	public long save(RecetaProducto p) {
		int resp=0;
		RecetaProducto rp = data.save(p);
		if(!rp.equals(null)) {
			resp=1;
		}
		
		return resp;
	}

	@Override
	public void delete(Long idrecetaproducto) {
		data.deleteById(idrecetaproducto);
		
	}

	@Override
	public List<RecetaProducto> findByRecetaProducto(Long idreceta) {
		
		return (List<RecetaProducto>)data.findByRecetaProducto(idreceta);
	}

	@Override
	public RecetaProducto BuscarRepetido( Long idreceta, Long idproducto) {

		return data.BuscarRepetido( idreceta, idproducto);
	}

	@Override
	public List<Producto> Clasificador(float con, float max) {
		
		return (List<Producto>)data.Clasificador(con,max);
	}

	@Override
	public Receta BuscarDosificacion(Long idreceta) {
		
		return data.BuscarDosificacion(idreceta);
	}


	




}
