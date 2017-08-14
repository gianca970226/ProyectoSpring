package com.example.demoProyecto.database;

import org.springframework.data.repository.CrudRepository;

import com.example.demoProyecto.modells.Celda;


public interface RepositorioCeldas  extends CrudRepository<Celda ,Integer> {
	
	

}
