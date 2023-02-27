package com.sgra.modelo;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="Receta")
public class Receta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY	)
	private Long idreceta;

	@Column(name="nombre_receta")
	private String nombre_receta;
	
	@Column(name="dosificacion")
	private int dosificacion;
		

	
	@Column(name="etapa")
	private String etapa;
	
	@ManyToOne
	@JoinColumn(name="idenfermedad")
	private Enfermedad idenfermedad;

	@Column(name="costo")
	private float costo;
	
	
	@OneToMany(mappedBy = "idreceta")
	private List<Receta> receta;








	public Receta(Long idreceta, String nombre_receta, int dosificacion, String etapa, Enfermedad idenfermedad,
			float costo, List<Receta> receta) {
		super();
		this.idreceta = idreceta;
		this.nombre_receta = nombre_receta;
		this.dosificacion = dosificacion;
		this.etapa = etapa;
		this.idenfermedad = idenfermedad;
		this.costo = costo;
		this.receta = receta;
	}




	public Receta() {
		super();
		// TODO Auto-generated constructor stub
	}




	public Long getIdreceta() {
		return idreceta;
	}




	public void setIdreceta(Long idreceta) {
		this.idreceta = idreceta;
	}




	public String getNombre_receta() {
		return nombre_receta;
	}




	public void setNombre_receta(String nombre_receta) {
		this.nombre_receta = nombre_receta;
	}




	public int getDosificacion() {
		return dosificacion;
	}




	public void setDosificacion(int dosificacion) {
		this.dosificacion = dosificacion;
	}






	public String getEtapa() {
		return etapa;
	}




	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}




	public Enfermedad getIdenfermedad() {
		return idenfermedad;
	}




	public void setIdenfermedad(Enfermedad idenfermedad) {
		this.idenfermedad = idenfermedad;
	}




	public float getCosto() {
		return costo;
	}




	public void setCosto(float costo) {
		this.costo = costo;
	}




	public List<Receta> getReceta() {
		return receta;
	}




	public void setReceta(List<Receta> receta) {
		this.receta = receta;
	}


	
}
