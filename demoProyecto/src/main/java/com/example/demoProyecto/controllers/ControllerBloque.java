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
import com.example.demoProyecto.modells.Bloque;
import com.example.demoProyecto.modells.Usuario;

@Controller
public class ControllerBloque implements Sessiones {
	@Autowired
	RepositorioBloques repositorioBloque;

	@GetMapping(value ="/IngresarBloque")
	public String IngresarBloque(Model modelo, HttpServletRequest request) {
		if (validarSession(request)) {
			modelo.addAttribute("bloque", new Bloque());
			modelo.addAttribute("control", "bloque");
			return "index";
		} else {
			return "redirect:/";
		}

	}

	@PostMapping(value = "/IngresarBloque")
	public String IngresarBloqueRegistro(Model modelo, @ModelAttribute Bloque bloque, HttpServletRequest request) {
		String resultado = "";
		if (validarSession(request)) {
			if (repositorioBloque.findOne(bloque.getId()) == null) {
				repositorioBloque.save(bloque);
				modelo.addAttribute("resultado", "ok");
				modelo.addAttribute("control","bloque");
				modelo.addAttribute("bloque", new Bloque());
				resultado = "index";
			} else {
				modelo.addAttribute("resultado", "error");
				modelo.addAttribute("control","bloque");
				modelo.addAttribute("bloque", new Bloque());
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
