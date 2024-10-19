package com.ricardoguzman.bibliotecafamilia.repositories;
import java.util.List;
import com.ricardoguzman.bibliotecafamilia.models.*;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LibroRepository extends CrudRepository <Libro, Long>{
    // this method retrieves all the books from the database
    List<Libro> findAll();
    // this method finds books with descriptions containing the search string
    List<Libro> findByAutorContaining(String search);
    // this method counts how many titles contain a certain string
    Long countByTituloContaining(String search);
    // this method deletes a book that starts with a specific title
    Long deleteByTituloStartingWith(String search);
    
   void deleteById(Long id);
    
    Libro save(Libro libro);
    @Transactional
    @Query (value= "SELECT * from libros Where autor LIKE (%?1%) OR titulo LIKE (%?1%) OR editorial LIKE (%?1%) OR año LIKE (%?1%) OR pais LIKE (%?1%);", nativeQuery=true)
    List <Libro> mostrarTablaLibros(String searchedQuery);
    

    
    @Transactional
    @Query (value= "SELECT * from comentarios", nativeQuery=true)
    List <Comentario> mostrarTablaComentarios();
    
    @Modifying
    @Transactional
    @Query (value= "UPDATE libros SET titulo=?2,autor=?3,editorial=?4, año=?5,tipo=?6,genero_o_materia=?7, pais=?8, donde_esta_el_libro=?9 WHERE id=?1", nativeQuery=true)
    Libro updateLibro(Long id,String titulo,String autor,String editorial,String año,String tipo,String genero_o_materia,String pais,String donde_esta_el_libro);
    
}
