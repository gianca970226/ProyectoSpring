package com.example.demoProyecto.modells;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;

@Entity
public class Visita {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String tipo;
	private Date fecha_visita;
	@OneToOne
	private Visitante visitante;
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Visitante getVisitante() {
		return visitante;
	}

	public void setVisitante(Visitante visitante) {
		this.visitante = visitante;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getFecha_visita() {
		return fecha_visita;
	}

	public void setFecha_visita(Date fecha_visita) {
		this.fecha_visita = fecha_visita;
	}

}
