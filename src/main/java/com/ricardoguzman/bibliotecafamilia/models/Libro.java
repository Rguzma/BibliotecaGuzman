package com.ricardoguzman.bibliotecafamilia.models;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 1, max = 255)
    private String titulo;
    
    @Size(max = 255)
    private String autor;
    
    @Size(max = 45)
    private String editorial;
    
    
    private String año;
    
    @Size(max = 45)
    private String tipo;
    
    @Size(max = 45)
    private String genero_o_materia;
    
    @Size(max = 45)
    private String pais;
    
    @Size(max = 45)
    private String donde_esta_el_libro;
    
    // This will not allow the createdAt column to be updated after creation
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date creado_en;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date actualizado_en;
    
    @JsonBackReference
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="registrado_por_id",updatable=false)
    private Usuario registrado_por_id;
    @JsonBackReference
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="dueño_id")
    private Usuario dueño_id;
    
    
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name="comentarios",
    		joinColumns = @JoinColumn(name= "libro_id"),
    		inverseJoinColumns = @JoinColumn(name = "usuario_id")
    		)
   private List<Usuario> usuarios_comentan;
    
    
    public Libro() {
    }
    public Libro(Long id, String titulo, String autor, String editorial, String año, String tipo, String genero_o_materia, String pais, String donde_esta_el_libro, Usuario dueño_id, Usuario registrado_por_id) {
        this.id=id;
    	this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.año = año;
        this.tipo = tipo;
        this.genero_o_materia = genero_o_materia;
        this.pais = pais;
        this.donde_esta_el_libro = donde_esta_el_libro;
        this.registrado_por_id=registrado_por_id;
        this.dueño_id=dueño_id;
    }
    // getter id
    public Long getId() {
        return id;
    }
    // getter titulo
    public String getTitulo() {
        return titulo;
    } // getter autor
    public String getAutor() {
        return autor;
    } // getter editorial
    public String getEditorial() {
        return editorial;
    } // getter año
    public String getAño() {
        return año;
    } // getter tipo
    public String getTipo() {
        return tipo;
    } // getter genero_o_materia
    public String getGenero_o_materia() {
        return genero_o_materia;
    } // getter pais
    public String getPais() {
        return pais;
    } // getter donde_esta_el_libro
    public String getDonde_esta_el_libro() {
        return donde_esta_el_libro;
    }
    public Usuario getRegistrado_por_id() {
        return registrado_por_id;
    }
    public Usuario getDueño_id() {
        return dueño_id;
    }
    
    
    // setter id
    public void setId(Long id) {
    	this.id = id;
    }
    
    // setter Titulo
    public void setTitulo(String titulo) {
    	this.titulo = titulo;
    }
    // setter Autor
    public void setAutor(String autor) {
    	this.autor = autor;
    }
    // setter editorial
    public void setEditorial(String editorial) {
    	this.editorial = editorial;
    }
    // setter año
    public void setAño(String año) {
    	this.año = año;
    }
    // setter tipo
    public void setTipo(String tipo) {
    	this.tipo = tipo;
    }
    // setter genero_o_materia
    public void setGenero_o_materia(String genero_o_materia) {
    	this.genero_o_materia = genero_o_materia;
    }
    // setter pais
    public void setPais(String pais) {
    	this.pais = pais;
    }
    // setter donde_esta_el_libro
    public void setDonde_esta_el_libro(String donde_esta_el_libro) {
    	this.donde_esta_el_libro = donde_esta_el_libro;
    }
    // other getters and setters removed for brevity
    @PrePersist
    protected void onCreate(){
        this.creado_en = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.actualizado_en = new Date();
    }
}