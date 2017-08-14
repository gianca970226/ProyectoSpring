package com.example.demoProyecto.database;

import org.springframework.data.repository.CrudRepository;

import com.example.demoProyecto.modells.Celda;
import com.example.demoProyecto.modells.Visitante;


public interface RepositorioVisitante  extends CrudRepository<Visitante ,Integer> {
	
	

}
