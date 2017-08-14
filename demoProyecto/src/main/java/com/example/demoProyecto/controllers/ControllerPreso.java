package com.example.demoProyecto.controllers;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demoProyecto.database.RepositorioPresos;
import com.example.demoProyecto.modells.Preso;
import com.example.demoProyecto.modells.Usuario;

@Controller
@RequestMapping
public class ControllerPreso implements Sessiones {

	@Autowired
	RepositorioPresos repositorioPreso;
	
	@GetMapping(value="/IngresarPreso")
	public String ingresarPreso(Model modelo,  HttpServletRequest request) {
		
		if (validarSession(request)) {
			modelo.addAttribute("preso", new Preso());
			modelo.addAttribute("control", "preso");
			return "index";
		} else {
			return "redirect:/";
		}
		
	}
		
	@PostMapping(value = "/IngresarPreso")
	public String ingresarRegistroPreso(Model modelo, @ModelAttribute (value="fechanacimiento") String fechanacimiento, @ModelAttribute Preso preso, HttpServletRequest request) {
		
		String resultado = "";
		if (validarSession(request)) {
			if (repositorioPreso.findOne(preso.getCedula()) == null) {
				//SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
				//String f = formatoDeFecha.format(fechanacimiento);
				//try {
				//	Date fecha = (Date) formatoDeFecha.parse(f);
				//	preso.setFecha_nacimiento(fecha);
				//} catch (ParseException e) {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
				//} 
				repositorioPreso.save(preso);
			    modelo.addAttribute("resultado", "ok");
				modelo.addAttribute("control","preso");
				modelo.addAttribute("preso", new Preso());
				resultado = "index";
			} else {
				modelo.addAttribute("resultado", "error");
				modelo.addAttribute("control","preso");
				modelo.addAttribute("preso", new Preso());
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
