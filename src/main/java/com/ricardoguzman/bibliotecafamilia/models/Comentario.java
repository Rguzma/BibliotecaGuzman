package com.ricardoguzman.bibliotecafamilia.models;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name="comentarios")

public class Comentario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min =1, max = 255)
    private String comentario;
   
    // This will not allow the createdAt column to be updated after creation
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date creado_en;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date actualizado_en;
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="usuario_id")
    private Usuario usuario;
    
    
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name="libro_id")
    private Libro libro;
    
    
    public Comentario() {
    }
    public Comentario(String comentario) {
        this.comentario = comentario;

    }
    
//    getters and setters removed for brevity
    // getter comentario
    public String getComentario() {
        return comentario;
    }
   
    
    
    // setter comentario
    public void setComentario(String comentario) {
    	this.comentario = comentario;
    }
   
    
    @PrePersist
    protected void onCreate(){
        this.creado_en = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.actualizado_en = new Date();
    }

}
