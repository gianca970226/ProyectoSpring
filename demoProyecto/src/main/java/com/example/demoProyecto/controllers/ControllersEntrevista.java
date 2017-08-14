package com.example.demoProyecto.controllers;

import java.sql.Date;
import java.util.ArrayList;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demoProyecto.database.RepositorioEntrevistas;
import com.example.demoProyecto.database.RepositorioPresos;
import com.example.demoProyecto.database.RepositorioVisitante;
import com.example.demoProyecto.modells.Celda;
import com.example.demoProyecto.modells.Entrevista;
import com.example.demoProyecto.modells.Preso;
import com.example.demoProyecto.modells.Usuario;
import com.example.demoProyecto.modells.Visitante;

@Controller
public class ControllersEntrevista implements Sessiones {
	@Autowired
	RepositorioVisitante repositorioVisitantes;
	@Autowired
	RepositorioPresos repositorioPresos;
	@Autowired
	RepositorioEntrevistas repositorioEntrevistas;
	Visitante auxVisitante;
	Preso preso;

	@PostMapping(value = "/RegistrarEntrevista")
	public String irRegistrarVisitante(Model modelo, @ModelAttribute(value = "cedula") Integer cedula,
			HttpServletRequest request) {
		if (validarSession(request)) {
			auxVisitante = repositorioVisitantes.findOne(cedula);
			return "index";
		} else {
			return "redirect:/";
		}

	}

	@PostMapping(value = "/BuscarPreso")
	public String irBuscarPreso(Model modelo, @ModelAttribute(value = "cedulaPreso") Integer cedula,
			HttpServletRequest request) {
		if (validarSession(request)) {

			preso = repositorioPresos.findOne(cedula);
			if (preso != null) {
				Visitante aVisitante = repositorioVisitantes.findOne(auxVisitante.getCedula());
				boolean bandera = false;
				for (int i = 0; i < aVisitante.getEntrevistas().size(); i++) {
					if (aVisitante.getEntrevistas().get(i).getPreso().getCedula() == preso.getCedula()) {
						bandera = true;
					}
				}
				if (!bandera) {
					modelo.addAttribute("control", "buscarVisitante");
					modelo.addAttribute("nombre", preso.getNombre());
					modelo.addAttribute("nombreVisitante", auxVisitante.getNombre());
					modelo.addAttribute("control2", "entrevista");
					modelo.addAttribute("control4", "ok");
					modelo.addAttribute("entrevista", new Entrevista());
					System.out.println(preso.getNombre());
					return "index";
				} else {
					modelo.addAttribute("control", "buscarVisitante");
					modelo.addAttribute("nombreVisitante", auxVisitante.getNombre());
					modelo.addAttribute("control2", "entrevista");
					modelo.addAttribute("resultado", "error2");
					return "index";
				}
			} else {

				modelo.addAttribute("control", "buscarVisitante");
				modelo.addAttribute("nombreVisitante", auxVisitante.getNombre());
				modelo.addAttribute("control2", "entrevista");
				modelo.addAttribute("resultado", "error");
				return "index";

			}
		} else {
			return "redirect:/";
		}

	}

	@PostMapping(value = "/AgregarEntrevista")
	public String irRegistrarPreso(Model modelo, HttpServletRequest request, @ModelAttribute Entrevista entrevista) {
		if (validarSession(request)) {
			modelo.addAttribute("control", "buscarVisitante");
			entrevista.setPreso(preso);
			Visitante visitante = repositorioVisitantes.findOne(auxVisitante.getCedula());
			java.util.Date fecha= new java.util.Date();
			Date fechaActual = new Date(fecha.getYear(), fecha.getMonth(), fecha.getDay());
			entrevista.setFecha_entrevista(fechaActual);
			repositorioEntrevistas.save(entrevista);
			visitante.getEntrevistas().add(repositorioEntrevistas.findOne(entrevista.getId()));
			repositorioVisitantes.save(visitante);
			// repositorioVisitantes.save(auxVisitante);

			return "redirect:/BuscarVisitante";
		} else {
			return "redirect:/";
		}

	}

	@GetMapping(value = "/RegistrarEntrevista")
	public String irRegistrarVisitante(Model modelo, HttpServletRequest request) {
		if (validarSession(request)) {
			modelo.addAttribute("control", "buscarVisitante");
			modelo.addAttribute("nombreVisitante", auxVisitante.getNombre());
			modelo.addAttribute("control2", "entrevista");
			return "index";
		} else {
			return "redirect:/";
		}

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
