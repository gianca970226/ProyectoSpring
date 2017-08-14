package com.example.demoProyecto.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demoProyecto.database.RepositorioPresos;
import com.example.demoProyecto.modells.Preso;

@Controller
public class ControllerCondena {
	@Autowired
    RepositorioPresos repositorioPreso;
	
	@PostMapping(value="/PeticionPresos")
	public  void Condena(Model modelo,  HttpServletResponse response) {

		 String contenido = "";
		 int contador= 0;
		 int total = (int) repositorioPreso.count();
		 for(Preso p:repositorioPreso.findAll()) {
			 contador++;
			 if(contador==total) {
			 contenido = contenido+"{"+"\"Nombre\":\""+p.getNombre()+"\","+"\"Apellido\":\""+p.getApellido()+"\","+"\"Edad\":\""+p.getEdad()+"\","+"\"Cedula\":\""+p.getCedula()+"\"}";
			 }else {
				 contenido = contenido+"{"+"\"Nombre\":\""+p.getNombre()+"\","+"\"Apellido\":\""+p.getApellido()+"\","+"\"Edad\":\""+p.getEdad()+"\","+"\"Cedula\":\""+p.getCedula()+"\"},";
			 }
			 
		 }
		 contenido = "{\"rows\":[" + contenido + "]}";
		 
    try {
		response.getWriter().print(contenido);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
}
