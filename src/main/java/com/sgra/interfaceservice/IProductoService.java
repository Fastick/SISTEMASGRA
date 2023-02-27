package com.sgra.interfaceservice;

import java.util.List;
import java.util.Optional;

import com.sgra.modelo.Producto;

public interface IProductoService {
	

	public List<Producto>listar();
	public Producto findByNombre_producto(String nombre_producto,float contenido,String unidad );
	public Optional<Producto>listarId(Long idproducto);
	public long save(Producto p);
	public void delete(Long id);


	
	
}
