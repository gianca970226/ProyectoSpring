package com.example.demoProyecto.database;

import org.springframework.data.repository.CrudRepository;

import com.example.demoProyecto.modells.Celda;
import com.example.demoProyecto.modells.Preso;

public interface RepositorioPresos extends CrudRepository<Preso ,Integer> {

}
