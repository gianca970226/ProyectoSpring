package com.example.demoProyecto.modells;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Preso {
	@Id
	private Integer cedula;
	private String nombre;
	private String apellido;
	private Integer edad;
	private Integer huella;
	private Date fecha_nacimiento;
	@OneToMany
	private List<Condena> condenas;
	@OneToMany
	private List<Visita> visitas;
	

	public List<Visita> getVisitas() {
		return visitas;
	}

	public void setVisitas(List<Visita> visitas) {
		this.visitas = visitas;
	}

	public List<Condena> getCondenas() {
		return condenas;
	}

	public void setCondenas(List<Condena> condenas) {
		this.condenas = condenas;
	}

	public Integer getCedula() {
		return cedula;
	}

	public void setCedula(Integer cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Integer getHuella() {
		return huella;
	}

	public void setHuella(Integer huella) {
		this.huella = huella;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	
	

}
