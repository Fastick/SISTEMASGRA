package com.sgra.modelo;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="RecetaProducto")
public class RecetaProducto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idrecetaproducto;
	
	@OneToOne
	@JoinColumn(name="idreceta")
	private Receta receta;
	
	@OneToOne
	@JoinColumn(name="idproducto")
	private Producto producto;





	public RecetaProducto(Long idrecetaproducto, Receta receta, Producto producto) {
		super();
		this.idrecetaproducto = idrecetaproducto;
		this.receta = receta;
		this.producto = producto;
	}





	public RecetaProducto() {
		super();
		// TODO Auto-generated constructor stub
	}





	public Long getIdrecetaproducto() {
		return idrecetaproducto;
	}





	public void setIdrecetaproducto(Long idrecetaproducto) {
		this.idrecetaproducto = idrecetaproducto;
	}





	public Receta getReceta() {
		return receta;
	}





	public void setReceta(Receta receta) {
		this.receta = receta;
	}





	public Producto getProducto() {
		return producto;
	}


 


	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
}
