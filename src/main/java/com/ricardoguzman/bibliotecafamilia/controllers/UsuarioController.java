package com.ricardoguzman.bibliotecafamilia.controllers;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.ricardoguzman.bibliotecafamilia.services.LibroService;
import com.ricardoguzman.bibliotecafamilia.services.UsuarioService;
import java.util.List;

import javax.validation.Valid;

import com.ricardoguzman.bibliotecafamilia.models.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;

public class UsuarioController {
	private final UsuarioService usuarioService;
	
	public UsuarioController(UsuarioService usuarioService) {

		this.usuarioService = usuarioService;
	}
	
	  @RequestMapping("/usuarios")
	  public String usuariosTabla(Model model) {
	      List<Usuario> usuarios = usuarioService.tablaUsuarios();
	      
	      model.addAttribute("usuarios", usuarios);
	      return "usuariosTabla.jsp";
	  }
	  
	  @RequestMapping(value= "/user/create", method=RequestMethod.GET)
	  public String userCreateForm() {
		  return "userCreateForm.jsp";
	  }
	  @RequestMapping(value = "/user/create", method=RequestMethod.POST)
	  public String userCreate(@Valid @RequestParam (value="usuario")String usuario, @RequestParam(value="nombre")String nombre,@RequestParam(value="password") String password, @RequestParam(value="email") String email,@RequestParam(value="numero") String numero, @RequestParam(value="administrador") int administrador, BindingResult result) {
	        if (result.hasErrors()) {
	            return "redirect:/user/create";
	        } else {
	        	usuarioService.crearUsuario(usuario,nombre,password,email,numero,administrador);
	            return "redirect:/usuarios";
	        }
	    }
}
