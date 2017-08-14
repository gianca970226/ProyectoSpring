package com.example.demoProyecto.controllers;

import java.util.ArrayList;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demoProyecto.database.RepositorioUsuarios;

import com.example.demoProyecto.modells.Usuario;


@Controller
public class ControllersHome implements Sessiones {
	@Autowired
	RepositorioUsuarios repositorioUsuario;

	@GetMapping(value = "/login")
	public String validar(Model modelo,HttpServletRequest request) {
	
		if(validarSession(request))
		{
			modelo.addAttribute("control","");
			return "index";
		}else 
		{
			return "redirect:/";
		}

	}

	@GetMapping(value = "/cerrarSession")
	public String cerrarSession(HttpServletRequest request) {

		request.getSession().invalidate();

		return "redirect:/";

	}

	@PostMapping(value = "/login")
	public String irRegistrar(Model modelo, @ModelAttribute (value = "cedula") Integer cedula, @ModelAttribute (value = "password") String password, HttpServletRequest request) {
		String conversion =MD5(password);
		Usuario usuerAux = buscarUsuario(cedula);
		if (conversion!= null && conversion.equals(usuerAux.getPassword())) {
			request.getSession().setAttribute("usuario", usuerAux);
			System.out.println("ingrese a que si existe pasworld");
			return "index";
		} else {
			modelo.addAttribute("error", "error");
			return "login";
		}
	}

	public Usuario buscarUsuario(int cedula) {
		Usuario aux = new Usuario();
		ArrayList<Usuario> lista = (ArrayList<Usuario>) repositorioUsuario.findAll();
		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getCedula() == cedula) {
				aux = lista.get(i);

			}
		}
		return aux;
	}
	
	public String MD5(String md5) {
		   try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return null;
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
