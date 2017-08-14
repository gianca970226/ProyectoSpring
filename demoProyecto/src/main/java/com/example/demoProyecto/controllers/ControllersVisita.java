package com.example.demoProyecto.controllers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demoProyecto.database.RepositorioCeldas;
import com.example.demoProyecto.database.RepositorioVisitante;
import com.example.demoProyecto.modells.Bloque;
import com.example.demoProyecto.modells.Entrevista;
import com.example.demoProyecto.modells.Preso;
import com.example.demoProyecto.modells.Usuario;
import com.example.demoProyecto.modells.Visitante;

@Controller
public class ControllersVisita implements Sessiones {
	@Autowired
	RepositorioVisitante repositorioVisitantes;

	@GetMapping(value = "/BuscarVisitante")
	public String validar(Model modelo, HttpServletRequest request) {

		if (validarSession(request)) {
			modelo.addAttribute("control", "buscarVisitante");

			return "index";
		} else {
			return "redirect:/";
		}

	}

	@PostMapping(value = "/buscarVisitante")
	public String irRegistrar(Model modelo, @ModelAttribute(value = "cedulaVisitante") Integer cedula,
			HttpServletRequest request) {
		if (validarSession(request)) {
			modelo.addAttribute("cedula", cedula);
			modelo.addAttribute("control", "buscarVisitante");
			Visitante visitanteAux = buscarVisitante(cedula);
			if (visitanteAux != null) {
				List<Entrevista> lstEntrevistas = visitanteAux.getEntrevistas();
				List<Preso> lstPresos = new ArrayList<>();
				for (int i = 0; i < lstEntrevistas.size(); i++) {
					lstPresos.add(lstEntrevistas.get(i).getPreso());
				}
				Preso auxPreso = new Preso();
				modelo.addAttribute("presos_visitante", lstPresos);
				modelo.addAttribute("control3","ok");
			} else {
				modelo.addAttribute("visitante", new Visitante());
				modelo.addAttribute("control2", "registrar");
			}

			return "index";
		} else {
			return "redirect:/";
		}

	}

	@PostMapping(value = "/RegistrarVisitante")
	public String irRegistrarVisitante(Model modelo, @ModelAttribute(value = "cedula") Integer cedula,
			@ModelAttribute Visitante visitante, HttpServletRequest request) {
		if (validarSession(request)) {
			visitante.setCedula(cedula);
			repositorioVisitantes.save(visitante);
			modelo.addAttribute("control", "buscarVisitante");
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

	public Visitante buscarVisitante(int cedula) {
		Visitante aux = null;
		ArrayList<Visitante> lista = (ArrayList<Visitante>) repositorioVisitantes.findAll();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getCedula() == cedula) {
				aux = lista.get(i);

			}
		}
		return aux;
	}
}
