package com.example.demoProyecto.modells;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Entrevista {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String parentezco;
	private Date fecha_entrevista;
	private String descripcion;
	@OneToOne
	private Preso preso;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Preso getPreso() {
		return preso;
	}

	public void setPreso(Preso preso) {
		this.preso = preso;
	}

	public String getParentezco() {
		return parentezco;
	}

	public void setParentezco(String parentezco) {
		this.parentezco = parentezco;
	}

	public Date getFecha_entrevista() {
		return fecha_entrevista;
	}

	public void setFecha_entrevista(Date fecha_entrevista) {
		this.fecha_entrevista = fecha_entrevista;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
