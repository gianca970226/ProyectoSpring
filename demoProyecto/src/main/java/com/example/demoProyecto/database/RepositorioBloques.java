package com.example.demoProyecto.database;

import org.springframework.data.repository.CrudRepository;

import com.example.demoProyecto.modells.Bloque;


public interface RepositorioBloques  extends CrudRepository<Bloque ,Integer> {


}
