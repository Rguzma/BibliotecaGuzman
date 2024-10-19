package com.ricardoguzman.bibliotecafamilia.controllers;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ricardoguzman.bibliotecafamilia.services.LibroService;
import com.ricardoguzman.bibliotecafamilia.services.UsuarioService;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.ricardoguzman.bibliotecafamilia.models.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;

@Controller
public class AppController {
	private final LibroService libroService;
	private final UsuarioService usuarioService;
	
	public AppController(LibroService libroService, UsuarioService usuarioService) {
		this.libroService = libroService;
		this.usuarioService = usuarioService;
	}
	

  @RequestMapping(value ="/" , method=RequestMethod.GET)
  public String prueba(Model model) {
      List<Usuario> usuarios = usuarioService.tablaUsuarios();
      model.addAttribute("usuarios", usuarios);
      return "usuariosTabla.jsp";
  }
  
  
  //*******************************SECCION LIBROS*******************************//

//  @RequestMapping("/libros")
//  public String index(Model model) {
//      List<Libro> libros = libroService.allLibros();
//      model.addAttribute("libros", libros);
//      return "library.jsp";
//  }
	
  @RequestMapping("/libros")
  public String librosTabla(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
	  System.out.println("Aqui viene libros del model "+model.getAttribute("libros"));
	  if( session.getAttribute("elUsuario") == null || session.getAttribute("elEmail")==null) {
		  redirectAttributes.addFlashAttribute("loginErrorMessage", "Por favor inicie sesión.");
		  return "redirect:/index";
	  }
	  Long id= (Long)session.getAttribute("elId");
	  String nombre= (String) session.getAttribute("elNombre");
	  String usuario= (String) session.getAttribute("elUsuario");
      List<Libro> libros = libroService.allLibros();
      model.addAttribute("libros", libros);
      model.addAttribute("nombre", nombre);
      model.addAttribute("usuario", usuario);
      model.addAttribute("id", id);
      return "library.jsp";
	  
  }
  @RequestMapping("/libros/busqueda/")
  public String librosTabla(HttpSession session, Model model, RedirectAttributes redirectAttributes, @RequestParam(value="q", required = false) String searchedQuery) {
	  System.out.println("Aqui viene busqueda en el GET "+ searchedQuery);
	  if( session.getAttribute("elUsuario") == null || session.getAttribute("elEmail")==null) {
		  redirectAttributes.addFlashAttribute("loginErrorMessage", "Por favor inicie sesión.");
		  return "redirect:/index";
	  }
	  Long id= (Long)session.getAttribute("elId");
	  String nombre= (String) session.getAttribute("elNombre");
	  String usuario= (String) session.getAttribute("elUsuario");
	  List<Libro> libros = libroService.tablaLibros(searchedQuery);
	  System.out.println("Aqui viene List en el GET "+ libros);
      model.addAttribute("libros", libros);
      model.addAttribute("nombre", nombre);
      model.addAttribute("usuario", usuario);
      model.addAttribute("id", id);
      return "library.jsp";
	  
  }
  
  @RequestMapping(value = "/search", method=RequestMethod.POST)
  public String buscarLibros(@RequestParam(value="tipo", required=false) String tipo,
		  						@RequestParam(value="genero_o_materia", required=false) String genero_o_materia, 
		  						@RequestParam(value="q", required=false) String q, 
		  						@RequestParam(value="donde_esta_el_libro", required=false) String donde_esta_el_libro, 
		  						@RequestParam(value="dueño", required=false) String dueño,
		  						Model model) {
	  System.out.println("Hola, esta es la busqueda "+q);
	  if(q == null) {
		  return "redirect:/libros";
	  }
	  else {
		  System.out.println("Aqui viene busqueda "+q);
		  return "redirect:/libros/busqueda";
	  }
        }
 
  
  @RequestMapping("/comentarios")
  public String comentariosTabla(Model model) {
      List<Comentario> comentarios = libroService.tablaComentarios();
      
      model.addAttribute("comentarios", comentarios);
      return "comentariosTabla.jsp";
  }
  
  
  
  //************************************SECCION USUARIOS********************************************//
  @RequestMapping("/informacion/{id}")
  public String informacionUsuario(@PathVariable("id") Long id, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
	  if( session.getAttribute("elUsuario") == null || session.getAttribute("elEmail")==null) {
		  redirectAttributes.addFlashAttribute("loginErrorMessage", "Por favor inicie sesión.");
		  return "redirect:/index";
	  }
	  String nombre= (String) session.getAttribute("elNombre");
	  String usuario= (String) session.getAttribute("elUsuario");
	  String email= (String) session.getAttribute("elEmail");
	  String numero= (String) session.getAttribute("elNumero");
	  int esAdministrador= (int) session.getAttribute("esAdministrador");
	  boolean si_es_administrador;
	  String administrador;
	  if (esAdministrador == 1) {
		  si_es_administrador = true;
		 administrador = "Si";
	  }else {
		  si_es_administrador = false;
		  administrador = "No";
	  }
	  model.addAttribute("id", id);
      model.addAttribute("usuario", usuario);
      model.addAttribute("nombre", nombre);
      model.addAttribute("email", email);
      model.addAttribute("numero", numero);
      model.addAttribute("administrador", administrador);
      model.addAttribute("si_es_administrador", si_es_administrador);
      return "miInfo.jsp";
	  
  }
  
  @RequestMapping("/usuarios")
  public String usuariosTabla(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
	  if( session.getAttribute("elUsuario") == null || session.getAttribute("elEmail")==null) {
		  redirectAttributes.addFlashAttribute("loginErrorMessage", "Por favor inicie sesión.");
		  return "redirect:/index";
	  }
	  Long id = (Long) session.getAttribute("elId");
      List<Usuario> usuarios = usuarioService.listaUsuarioExceptoPropio(id);
		System.out.println("usuario  "+session.getAttribute("elUsuario"));
		System.out.println("nombre  " +session.getAttribute("elNombre"));
		System.out.println("elEmail  " +session.getAttribute("elEmail"));
		System.out.println("elNumero  " +session.getAttribute("elNumero"));
		System.out.println("esAdministrador  " +session.getAttribute("esAdministrador"));
	  String nombre= (String) session.getAttribute("elNombre");
	  String usuario= (String) session.getAttribute("elUsuario");
	  String email= (String) session.getAttribute("elEmail");
	  String numero= (String) session.getAttribute("elNumero");
	  int esAdministrador= (int) session.getAttribute("esAdministrador");
	  boolean si_es_administrador;
	  String administrador;
	  if (esAdministrador == 1) {
		  si_es_administrador = true;
		 administrador = "Si";
	  }else {
		  si_es_administrador = false;
		  administrador = "No";
	  }
	  model.addAttribute("id", id);
      model.addAttribute("usuarios", usuarios);
      model.addAttribute("usuario", usuario);
      model.addAttribute("nombre", nombre);
      model.addAttribute("email", email);
      model.addAttribute("numero", numero);
      model.addAttribute("administrador", administrador);
      model.addAttribute("si_es_administrador", si_es_administrador);
      return "indexUsuarios.jsp";
	  
  }
  
  @RequestMapping(value= "/index", method=RequestMethod.GET)
  public String userCreateForm() {
	  return "index.jsp";
  }
  
  @RequestMapping(value= "/crearUsuario", method=RequestMethod.GET)
  public String formCrearUsuario( HttpSession session, RedirectAttributes redirectAttributes) {
	  if( session.getAttribute("elUsuario") == null || session.getAttribute("elEmail")==null) {
		  redirectAttributes.addFlashAttribute("loginErrorMessage", "Por favor inicie sesión.");
		  return "redirect:/index";
	  }

	  return "userCreateForm.jsp";
	  
  }
  
  
  @RequestMapping(value = "/user/create", method=RequestMethod.POST)
  public String userCreate(@RequestParam(value="usuario")String usuario, @RequestParam(value="nombre")String nombre,@RequestParam(value="password") String password, @RequestParam(value="confirmPassword") String confirmPassword, @RequestParam(value="email") String email,@RequestParam(value="numero") String numero, @RequestParam(value="administrador", required=false) Integer administrador, RedirectAttributes redirectAttributes) {
		System.out.println("este es usuario "+usuario);
		System.out.println("este es nombre "+nombre);
		System.out.println("este es confirmPassword "+confirmPassword);
		System.out.println("este es password "+password);
		System.out.println("este es email "+email);
		System.out.println("este es administrador "+administrador);
		
		Usuario emailExistente = usuarioService.getUserByEmail(email);
		Usuario usuarioExistente = usuarioService.getUserByUsuario(usuario);
		Usuario nombreExistente = usuarioService.getUserByNombre(nombre);
		
		int largoDeAdministrador = String.valueOf(administrador).length();
	  if(usuario.length()<1 || nombre.length()<1 || password.length()<1|| confirmPassword.length()<1 || email.length()<1 || largoDeAdministrador<1 ) {
		  redirectAttributes.addFlashAttribute("ErrorMessage", "Espacios con * son obligatorios");
		  return "redirect:/crearUsuario";
	  }
	  
	  else if(usuario.length()<1|| usuario.length()>45) {
		  redirectAttributes.addFlashAttribute("ErrorMessage", "Usuario debe contener entre 1 a 45 caracteres.");
		  return "redirect:/crearUsuario";
      }
      else if( usuarioExistente != null ) {
			redirectAttributes.addFlashAttribute("ErrorMessage", "Usuario ya existe");
			return "redirect:/crearUsuario";
		}
	  else if(nombre.length()<1|| nombre.length()>45) {
		  redirectAttributes.addFlashAttribute("ErrorMessage", "Nombre debe contener entre 1 a 45 caracteres.");
		  return "redirect:/crearUsuario";
      }
      else if( nombreExistente != null ) {
			redirectAttributes.addFlashAttribute("ErrorMessage", "Nombre ya existe");
			return "redirect:/crearUsuario";
		}
	  else if(password.length()<8|| password.length()>12) {
		  redirectAttributes.addFlashAttribute("ErrorMessage", "Su password debe contener entre 8 a 12 caracteres.");
		  return "redirect:/crearUsuario";
      }
	  else if(! password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]+$")) {
		  redirectAttributes.addFlashAttribute("ErrorMessage", "Su password debe contener caracteres alfanuméricas incluyendo mayúsculas y minúsculas.");
		  return "redirect:/crearUsuario";
      }
	  else if( ! password.equals( confirmPassword ) ) {
			redirectAttributes.addFlashAttribute("ErrorMessage", "Password y la confirmación del password no coinciden");
			return "redirect:/crearUsuario";
		}
      else if(email.length()<6|| email.length()>45) {
		  redirectAttributes.addFlashAttribute("ErrorMessage", "Su email no es válido.");
		  return "redirect:/crearUsuario";
      }
		
      else if( emailExistente != null ) {
			redirectAttributes.addFlashAttribute("ErrorMessage", "Este email pertenece a otro usuario");
			return "redirect:/crearUsuario";
		}
      else if(! email.matches("(.*)@(.*)") ||! email.matches("(.*).com") ) {
		  redirectAttributes.addFlashAttribute("ErrorMessage", "Su email no es válido.");
		  return "redirect:/crearUsuario";
      }
      else if(numero.length()>12) {
		  redirectAttributes.addFlashAttribute("ErrorMessage", "Su número no puede superar mas de 12 digitos.");
		  return "redirect:/crearUsuario";
      }
      else if( numero.matches("[A-Za-z]+")) {
		  redirectAttributes.addFlashAttribute("ErrorMessage", "El espacio para número sólo acepta dígitos");
		  return "redirect:/crearUsuario";
      }
      else if( administrador < 0 && administrador > 1) {
    	  boolean es0 = administrador==0;
    	  System.out.println("este es administrador "+es0);
    	  boolean es1 = administrador==1;
    	  System.out.println("este es administrador "+es1);
    	  System.out.println("este es administrador "+administrador);
		  redirectAttributes.addFlashAttribute("ErrorMessage", "Información para administrador no es válido12321");
		  return "redirect:/crearUsuario";
      	}
      else {
	  usuarioService.crearUsuario(usuario,nombre,password,email,numero,administrador);
            return "redirect:/usuarios";
        }
  }
  
  @RequestMapping(value= "/edit/usuarios/{id}", method=RequestMethod.GET)
  public String userUpdateAdmin(@PathVariable("id") Long id, HttpSession session, Model model, RedirectAttributes redirectAttributes) {
	  if( session.getAttribute("elUsuario") == null || session.getAttribute("elEmail")==null) {
		  redirectAttributes.addFlashAttribute("loginErrorMessage", "Por favor inicie sesión.");
		  return "redirect:/index";
	  }
	  Usuario usuario=  usuarioService.getUsuarioById(id);
	  String usuarioSession = (String)session.getAttribute("elUsuario");
	  System.out.println("la id : "+usuario.getId());
	  model.addAttribute("usuario", usuario);
	  model.addAttribute("usuarioSession", usuarioSession);
	  return "actualizarUsuario.jsp";
	  
  }
  
  @RequestMapping(value = "/user/actualizar/{id}", method=RequestMethod.POST)
  public String userUpdate(@PathVariable("id") Long id, @RequestParam(value="usuario")String usuario, @RequestParam(value="nombre")String nombre, @RequestParam(value="password") String password, @RequestParam(value="confirmPassword") String confirmPassword, @RequestParam(value="email") String email, @RequestParam(value="numero") String numero, @RequestParam(value="administrador", required=false) Integer administrador, RedirectAttributes redirectAttributes) {
	  System.out.println("Hola");
	  List<Usuario> emailExistente = usuarioService.otroUserByEmail(email,id);
	  List<Usuario> usuarioExistente = usuarioService.otroUserByUsuario(usuario,id);
	  List<Usuario> nombreExistente = usuarioService.otroUserByNombre(nombre,id);
		
		int largoDeAdministrador = String.valueOf(administrador).length();
	  if(usuario.length()<1 || nombre.length()<1 || password.length()<1|| confirmPassword.length()<1 || email.length()<1 || largoDeAdministrador<1 ) {
		  redirectAttributes.addFlashAttribute("ErrorMessage", "Espacios con * son obligatorios");
		  return "redirect:/edit/usuarios/{id}";
	  }
	  
	  else if(usuario.length()<1|| usuario.length()>45) {
		  redirectAttributes.addFlashAttribute("ErrorMessage", "Usuario debe contener entre 1 a 45 caracteres.");
		  return "redirect:/edit/usuarios/{id}";
    }
    else if( usuarioExistente.size()>0) {
			redirectAttributes.addFlashAttribute("ErrorMessage", "Usuario ya existe");
			return "redirect:/edit/usuarios/{id}";
		}
	  else if(nombre.length()<1|| nombre.length()>45) {
		  redirectAttributes.addFlashAttribute("ErrorMessage", "Nombre debe contener entre 1 a 45 caracteres.");
		  return "redirect:/edit/usuarios/{id}";
    }
    else if( nombreExistente.size()>0 ) {
			redirectAttributes.addFlashAttribute("ErrorMessage", "Nombre ya existe");
			return "redirect:/edit/usuarios/{id}";
		}
	  else if(password.length()<8|| password.length()>12) {
		  redirectAttributes.addFlashAttribute("ErrorMessage", "Su password debe contener entre 8 a 12 caracteres.");
		  return "redirect:/edit/usuarios/{id}";
    }
	  else if(! password.matches("^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]+$")) {
		  redirectAttributes.addFlashAttribute("ErrorMessage", "Su password debe contener caracteres alfanuméricas incluyendo mayúsculas y minúsculas.");
		  return "redirect:/edit/usuarios/{id}";
    }
	  else if( ! password.equals( confirmPassword ) ) {
			redirectAttributes.addFlashAttribute("ErrorMessage", "Password y la confirmación del password no coinciden");
			return "redirect:/edit/usuarios/{id}";
		}
    else if(email.length()<6|| email.length()>45) {
		  redirectAttributes.addFlashAttribute("ErrorMessage", "Su email no es válido.");
		  return "redirect:/edit/usuarios/{id}";
    }
		
    else if( emailExistente.size()>0) {
			redirectAttributes.addFlashAttribute("ErrorMessage", "Este email pertenece a otro usuario");
			return "redirect:/edit/usuarios/{id}";
		}
    else if(! email.matches("(.*)@(.*)") ||! email.matches("(.*).com") ) {
		  redirectAttributes.addFlashAttribute("ErrorMessage", "Su email no es válido.");
		  return "redirect:/edit/usuarios/{id}";
    }
    else if(numero.length()>12) {
		  redirectAttributes.addFlashAttribute("ErrorMessage", "Su número no puede superar mas de 12 digitos.");
		  return "redirect:/edit/usuarios/{id}";
    }
    else if( numero.matches("[A-Za-z]+")) {
		  redirectAttributes.addFlashAttribute("ErrorMessage", "El espacio para número sólo acepta dígitos");
		  return "redirect:/edit/usuarios/{id}";
    }
    else if( administrador < 0 && administrador > 1) {
		  redirectAttributes.addFlashAttribute("ErrorMessage", "Información para administrador no es válido12321");
		  return "redirect:/edit/usuarios/{id}";
    	}
	  usuarioService.actualizarUsuario(usuario,nombre,password,email,numero,administrador,id);
	  System.out.println("Has vuelto del repositorio");
	  return "redirect:/usuarios";
        }
  
  @RequestMapping(value = "/delete/usuarios/{id}", method=RequestMethod.POST)
  public String userDelete(@PathVariable("id") Long id) {
	  System.out.println("Hola, vamos a borrar");
	  usuarioService.borrarUsuario(id);
	  System.out.println("Has vuelto del repositorio de borrar");
	  return "redirect:/usuarios";
        }
  
  @RequestMapping(value = "/validateUser", method=RequestMethod.POST)
  public String userValidate(@RequestParam(value="user_o_email")String user_o_email, 
		  									@RequestParam(value="userPassword") String userPassword, HttpSession session, 
		  									RedirectAttributes redirectAttributes) {
      Usuario usuario= usuarioService.getUserByEmail(user_o_email); 
		System.out.println("hola");

	if (usuario ==null) {
		usuario = usuarioService.getUserByUsuario(user_o_email);
		if(usuario == null) {
			redirectAttributes.addFlashAttribute("loginErrorMessage", "Este usuario o email no existe. Por favor contacte un administrador.");
			return "redirect:/index";
		}else {
			if(BCrypt.checkpw(userPassword, usuario.getPassword())) {
				System.out.println("usuario1 "+usuario.getUsuario());
				System.out.println("nombre1 " +usuario.getNombre());
				session.setAttribute("elId", usuario.getId());
				session.setAttribute("elUsuario", usuario.getUsuario());
				session.setAttribute("elNombre", usuario.getNombre());
				session.setAttribute("elEmail", usuario.getEmail());
				session.setAttribute("elNumero", usuario.getNumero());
				session.setAttribute("esAdministrador", usuario.getAdministrador());
                return "redirect:/libros";
			}else {
				redirectAttributes.addFlashAttribute("loginErrorMessage", "Contraseña incorrecta. Por favor contacte un administrador.");
				return "redirect:/index";
				}
			}
		}else {
			if(BCrypt.checkpw(userPassword, usuario.getPassword())) {
				System.out.println("usuario 2 "+usuario.getUsuario());
				System.out.println("nombre 2 " +usuario.getNombre());
				session.setAttribute("elId", usuario.getId());
				session.setAttribute("elUsuario", usuario.getUsuario());
				session.setAttribute("elNombre", usuario.getNombre());
				session.setAttribute("elEmail", usuario.getEmail());
				session.setAttribute("elNumero", usuario.getNumero());
				session.setAttribute("esAdministrador", usuario.getAdministrador());
                return "redirect:/libros";
			}else {
				redirectAttributes.addFlashAttribute("loginErrorMessage", "Contraseña incorrecta. Por favor contacte un administrador.");
				return "redirect:/index";
				}
	  }
  }
  
  @RequestMapping(value= "/logout", method=RequestMethod.POST)
  public String cerrarSesion(HttpSession session) {
	  session.invalidate();
	  return "redirect:/index";
	  
  }

}
