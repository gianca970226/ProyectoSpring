package com.example.demoProyecto.database;

import org.springframework.data.repository.CrudRepository;

import com.example.demoProyecto.modells.Bloque;
import com.example.demoProyecto.modells.Usuario;


public interface RepositorioUsuarios  extends CrudRepository<Usuario ,Integer> {

}
