package com.ricardoguzman.bibliotecafamilia.controllers;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ricardoguzman.bibliotecafamilia.services.LibroService;
import com.ricardoguzman.bibliotecafamilia.repositories.*;
import java.util.List;
import com.ricardoguzman.bibliotecafamilia.models.*;

@RestController
public class LibroApi {
	
//    private final LibroService libroService;
//    public LibroApi(LibroService libroService){
//        this.libroService = libroService;
//    }
//    @RequestMapping(value ="/" , method=RequestMethod.GET)
//    public String prueba() {
//        return "hola";
//    }
//    
//    
//    @RequestMapping("/api/libros")
//    public List<Libro> index() {
//        return libroService.allLibros();
//    }
//    
//    @RequestMapping(value="/api/libros", method=RequestMethod.POST)
//    public Libro create(@RequestParam(value="titulo") String titulo, @RequestParam(value="autor") String autor, @RequestParam(value="editorial") String editorial, @RequestParam(value="año") String año, 
//    					@RequestParam(value="tipo") String tipo, @RequestParam(value="genero_o_materia") String genero_o_materia, @RequestParam(value="pais") String pais, @RequestParam(value="donde_esta_el_libro") String donde_esta_el_libro){
//    	Libro book = new Libro(titulo, autor, editorial, año, tipo, genero_o_materia, pais, donde_esta_el_libro);
//        return libroService.createLibro(book);
//    }
//    
//    @RequestMapping("/api/libros/{id}")
//    public Libro show(@PathVariable("id") Long id) {
//    	Libro libro = libroService.findLibro(id);
//        return libro;
//    }
//    @RequestMapping(value="/api/libros/{id}", method=RequestMethod.PUT)
//    public Libro update(@PathVariable("id") Long id, @RequestParam(value="titulo") String titulo, @RequestParam(value="autor") String autor, @RequestParam(value="editorial") String editorial, @RequestParam(value="año") String año, 
//			@RequestParam(value="tipo") String tipo, @RequestParam(value="genero_o_materia") String genero_o_materia, @RequestParam(value="pais") String pais, @RequestParam(value="donde_esta_el_libro") String donde_esta_el_libro){
//    	Libro libro = libroService.updateLibro(id, titulo, autor, editorial, año, tipo, genero_o_materia, pais, donde_esta_el_libro);
//        return libro;
//    }
//    
//    @RequestMapping(value="/api/libros/{id}", method=RequestMethod.DELETE)
//    public void destroy(@PathVariable("id") Long id) {
//    	libroService.deleteLibro(id);
//    }
    

}
