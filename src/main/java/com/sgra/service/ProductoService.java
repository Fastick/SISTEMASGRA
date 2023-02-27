package com.sgra.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgra.interfaceservice.IProductoService;

import com.sgra.interfaz.IProducto;
import com.sgra.modelo.Producto;



@Service
public class ProductoService implements IProductoService{

	@Autowired
	private IProducto data;

	@Override
	public List<Producto> listar() {
		return (List<Producto>)data.findAll();
	}
	
	@Override
	public Optional<Producto> listarId(Long idproducto) {
		return data.findById(idproducto);
	}

	@Override
	public long save(Producto p) {
		int resp=0;
		Producto producto = data.save(p);
		if(!producto.equals(null)) {
			resp =1;
		}
		return resp;
	}

	@Override
	public void delete(Long id) {
		data.deleteById(id);
	}


	
	@Override
	public Producto findByNombre_producto(String nombre_producto, float contenido, String unidad) {
	return data.findByNombre_producto(nombre_producto, contenido, unidad);
	}


	



	

	
	
}

