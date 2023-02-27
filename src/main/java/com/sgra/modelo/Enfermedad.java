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
@Table(name="Enfermedad")
public class Enfermedad{
		


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY	)
	private Long idenfermedad;

	
	@Column(name="nombre_enfermedad")
	private String nombre_enfermedad;
	
	@Column(name="detalles")
	private String detalles;
	
	@ManyToOne
	@JoinColumn(name="idcultivo")
	private Cultivo idcultivo;
	
	@OneToMany(mappedBy = "idenfermedad")
	private List<Enfermedad> enfermedadList;
	
	 @Column(name="imagen")
	 private String imagen;



	public Enfermedad(Long idenfermedad, String nombre_enfermedad, String detalles, Cultivo idcultivo,
			List<Enfermedad> enfermedadList, String imagen) {
		super();
		this.idenfermedad = idenfermedad;
		this.nombre_enfermedad = nombre_enfermedad;
		this.detalles = detalles;
		this.idcultivo = idcultivo;
		this.enfermedadList = enfermedadList;
		this.imagen = imagen;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}

	public Enfermedad() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdenfermedad() {
		return idenfermedad;
	}

	public void setIdenfermedad(Long idenfermedad) {
		this.idenfermedad = idenfermedad;
	}

	public String getNombre_enfermedad() {
		return nombre_enfermedad;
	}

	public void setNombre_enfermedad(String nombre_enfermedad) {
		this.nombre_enfermedad = nombre_enfermedad;
	}

	public Cultivo getIdcultivo() {
		return idcultivo;
	}

	public void setIdcultivo(Cultivo idcultivo) {
		this.idcultivo = idcultivo;
	}

	public List<Enfermedad> getEnfermedadList() {
		return enfermedadList;
	}

	public void setEnfermedadList(List<Enfermedad> enfermedadList) {
		this.enfermedadList = enfermedadList;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	

}
