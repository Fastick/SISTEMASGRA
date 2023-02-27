package com.sgra.modelo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;


import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="Receta_Asignada")
public class Receta_Asignada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY	)
	private Long idrecetaasignada;

	
	@OneToOne
	@JoinColumn(name="idreceta")
	private Receta idreceta;
	
	@OneToOne
	@JoinColumn(name="id")
	private Usuario id;
	
	@Column(name="estado")
	private String estado;
	
	
	@Column(name="fecha")
	private String fecha;


	public Long getIdrecetaasignada() {
		return idrecetaasignada;
	}


	public void setIdrecetaasignada(Long idrecetaasignada) {
		this.idrecetaasignada = idrecetaasignada;
	}


	public Receta getIdreceta() {
		return idreceta;
	}


	public void setIdreceta(Receta idreceta) {
		this.idreceta = idreceta;
	}


	public Usuario getId() {
		return id;
	}


	public void setId(Usuario id) {
		this.id = id;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public String getFecha() {
		return fecha;
	}


	public void setFecha(String fecha) {
		this.fecha = fecha;
	}


	public Receta_Asignada(Long idrecetaasignada, Receta idreceta, Usuario id, String estado, String fecha) {
		super();
		this.idrecetaasignada = idrecetaasignada;
		this.idreceta = idreceta;
		this.id = id;
		this.estado = estado;
		this.fecha = fecha;
	}


	public Receta_Asignada() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
