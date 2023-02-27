package com.sgra.modelo;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;




@Entity
@Table(name="Producto")
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY	)
	private Long idproducto;
	
	@Column(name="nombre_producto") 
	private String nombre_producto;
	

	@Column(name="pimagen")
	private String pimagen;
	
	@Column(name = "contenido")
	private float contenido;
	
	@Column(name = "unidad")
	private String unidad;
	

	@Column(name="costo")
	private float costo;
	
	@Column(name="tipoproducto")
	private String tipoproducto;
	
	@Column(name="detalles")
	private String detalles;
	
	///lista de mapeo de uno a uno con la tabla tipo producto
	
	@OneToMany(mappedBy = "idproducto")
	private List<Producto> producto;
	
	



	public Producto(Long idproducto, String nombre_producto, String pimagen, float contenido, String unidad,
			float costo, String tipoproducto, String detalles, List<Producto> producto) {
		super();
		this.idproducto = idproducto;
		this.nombre_producto = nombre_producto;
		this.pimagen = pimagen;
		this.contenido = contenido;
		this.unidad = unidad;
		this.costo = costo;
		this.tipoproducto = tipoproducto;
		this.detalles = detalles;
		this.producto = producto;
	}




	public String getDetalles() {
		return detalles;
	}




	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}




	public String getPimagen() {
		return pimagen;
	}




	public void setPimagen(String pimagen) {
		this.pimagen = pimagen;
	}




	public Producto() {
		super();
	}




	public String getTipoproducto() {
		return tipoproducto;
	}



	public void setTipoproducto(String tipoproducto) {
		this.tipoproducto = tipoproducto;
	}



	public Long getIdproducto() {
		return idproducto;
	}



	public void setIdproducto(Long idproducto) {
		this.idproducto = idproducto;
	}



	public String getNombre_producto() {
		return nombre_producto;
	}



	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}






	public float getContenido() {
		return contenido;
	}



	public void setContenido(float contenido) {
		this.contenido = contenido;
	}



	public String getUnidad() {
		return unidad;
	}



	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}




	public float getCosto() {
		return costo;
	}



	public void setCosto(float costo) {
		this.costo = costo;
	}



	public List<Producto> getProducto() {
		return producto;
	}



	public void setProducto(List<Producto> producto) {
		this.producto = producto;
	}






}
