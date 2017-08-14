package com.example.demoProyecto.controllers;

import java.util.ArrayList;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demoProyecto.database.RepositorioBloques;
import com.example.demoProyecto.database.RepositorioCeldas;
import com.example.demoProyecto.modells.Bloque;
import com.example.demoProyecto.modells.Celda;
import com.example.demoProyecto.modells.Usuario;

@Controller
public class ControllerCelda implements Sessiones {
	@Autowired
	RepositorioCeldas repositorioCeldas;
	@Autowired
	RepositorioBloques repositorioBloques;
	@GetMapping(value = "/IngresarCelda")
	public String IngresarBloque(Model modelo, HttpServletRequest request) {
		if (validarSession(request)) {
			modelo.addAttribute("celda", new Celda());
			modelo.addAttribute("control", "celda");
			modelo.addAttribute("bloques", repositorioBloques.findAll());
			
			return "index";
		} else {
			return "redirect:/";
		}

	}
	@PostMapping(value = "/IngresarCelda")
	public String IngresarBloqueRegistro(Model modelo, @ModelAttribute Celda celda,@ModelAttribute (value = "bloque_id") Integer bloque_id, HttpServletRequest request) {
		String resultado = "";
		if (validarSession(request)) {
			if (repositorioCeldas.findOne(celda.getId()) == null) {
				repositorioCeldas.save(celda);
				Bloque bloqueAux= repositorioBloques.findOne(bloque_id);
				bloqueAux.getCeldas().add(celda);
				repositorioBloques.save(bloqueAux);
				modelo.addAttribute("resultado", "ok");
				modelo.addAttribute("control","celda");
				modelo.addAttribute("bloques", repositorioBloques.findAll());
				modelo.addAttribute("celda", new Celda());
				resultado = "index";
			} else {
				modelo.addAttribute("resultado", "error");
				modelo.addAttribute("bloques", repositorioBloques.findAll());
				modelo.addAttribute("control","celda");
				resultado = "index";
			}
		} else {
			resultado = "redirect:/";
		}
		return resultado;
	}

	@Override
	public Boolean validarSession(HttpServletRequest request) {
		try {
			Usuario user = (Usuario) request.getSession().getAttribute("usuario");
			System.out.println(user.getCedula());
			return true;
		} catch (Exception e) {
			return false;
		}
	}


}
