package com.example.demoProyecto.modells;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Celda {
	@Id
	private Integer id;
	private Integer capacidad;
	@OneToMany
	private List<Preso> presos;

	public void setPresos(List<Preso> presos) {
		this.presos = presos;
	}

	public List<Preso> getPresos() {
		return presos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}
}
