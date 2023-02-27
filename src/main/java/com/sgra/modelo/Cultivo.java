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
@Table(name="Cultivo")
public class Cultivo {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY	)
		private Long idcultivo;
		
		
		@Column(name="nombre_cultivo") 
		private String nombre_cultivo;
		
		@Column(name="variedad")
		private String variedad;
		
		@Column(name="cimagen")
		private String cimagen;
		

		@OneToMany(mappedBy = "idcultivo")
		private List<Cultivo> cultivoList;


		public Cultivo() {
			super();
		
		}


		

		public Cultivo(Long idcultivo, String nombre_cultivo, String variedad, String cimagen,
				List<Cultivo> cultivoList) {
			super();
			this.idcultivo = idcultivo;
			this.nombre_cultivo = nombre_cultivo;
			this.variedad = variedad;
			this.cimagen = cimagen;
			this.cultivoList = cultivoList;
		}




		public Long getIdcultivo() {
			return idcultivo;
		}


		public void setIdcultivo(Long idcultivo) {
			this.idcultivo = idcultivo;
		}


		public String getNombre_cultivo() {
			return nombre_cultivo;
		}


		public void setNombre_cultivo(String nombre_cultivo) {
			this.nombre_cultivo = nombre_cultivo;
		}


		public String getVariedad() {
			return variedad;
		}


		public void setVariedad(String variedad) {
			this.variedad = variedad;
		}


		public List<Cultivo> getCultivoList() {
			return cultivoList;
		}


		public void setCultivoList(List<Cultivo> cultivoList) {
			this.cultivoList = cultivoList;
		}




		public String getCimagen() {
			return cimagen;
		}




		public void setCimagen(String cimagen) {
			this.cimagen = cimagen;
		}






	}

