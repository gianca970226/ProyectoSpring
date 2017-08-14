package com.example.demoProyecto.modells;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Bloque {
	@Id
	private Integer id;
	private String nombre_patio;
	@OneToMany
	private List<Celda> celdas;

	public void setCeldas(List<Celda> celdas) {
		this.celdas = celdas;
	}

	public List<Celda> getCeldas() {
		return celdas;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre_patio() {
		return nombre_patio;
	}

	public void setNombre_patio(String nombre_patio) {
		this.nombre_patio = nombre_patio;
	}

}
