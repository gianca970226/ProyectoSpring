package com.example.demoProyecto.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demoProyecto.modells.Usuario;



@Controller
@RequestMapping
public class ControllersIndex {

	@RequestMapping(value="/")
	public String login(Model modelo,HttpServletRequest request)
	{
		try {
			Usuario user = (Usuario) request.getSession().getAttribute("usuario");
			System.out.println(user.getCedula());//es para saber si el usuario ya existe
			return "redirect:/login";
		} catch (Exception e) {

			return "login";
		}
	}

	
}
