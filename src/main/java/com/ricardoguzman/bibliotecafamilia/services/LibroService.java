package com.ricardoguzman.bibliotecafamilia.services;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.ricardoguzman.bibliotecafamilia.repositories.*;
import com.ricardoguzman.bibliotecafamilia.models.*;

@Service
public class LibroService {

private final LibroRepository libroRepository;
    
    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }
    // returns all the books
    public List<Libro> allLibros() {
        return libroRepository.findAll();
    }
    //***************** TABLA DE LIBROS***************** //
    public List<Libro> tablaLibros(String searchedQuery){
    	return libroRepository.mostrarTablaLibros(searchedQuery);
    }

//    public List<Libro> tablaLibros(String titulo, String autor){
//    	return libroRepository.mostrarTablaLibros(titulo, autor);
//    }
    //***************** FIN DE TABLAS DE LIBROS*****************//
    
    public List<Comentario> tablaComentarios(){
    	return libroRepository.mostrarTablaComentarios();
    }
    
    // creates a book
    public Libro createLibro(Libro libro) {
        return libroRepository.save(libro);
    }
    // retrieves a book
    public Libro findLibro(Long id) {
        Optional<Libro> optionalLibro = libroRepository.findById(id);
        if(optionalLibro.isPresent()) {
            return optionalLibro.get();
        } else {
            return null;
        }
    }
    
    public void deleteLibro(Long id) {
    	libroRepository.deleteById(id);
    }
	
    public Libro updateLibro(Long id,String titulo,String autor,String editorial,String año,String tipo,String genero_o_materia,String pais,String donde_esta_el_libro) {
    	return libroRepository.updateLibro(id,titulo,autor,editorial,año, tipo, genero_o_materia,pais, donde_esta_el_libro );
    	
    	
    }
    

}
