package com.ricardoguzman.bibliotecafamilia.models;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.JoinColumn;


@Entity
@Table(name="usuarios")
public class Usuario {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min =1, max = 45)
    private String usuario;

    @NotNull
    @Size(min = 1, max = 45)
    private String nombre;
    
    @NotNull
    @Size(max = 255)
    private String password;
    
	@Transient
	private String confirmPassword;
    
    @NotNull
    @Size(max=45)
    private String email;
    @Size(max=45)
    private String numero;
    @NotNull
    @Size(max=1)
    private int administrador;
    // This will not allow the createdAt column to be updated after creation
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date creado_en;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date actualizado_en;
    
    @JsonManagedReference
    @OneToMany(mappedBy="dueño_id",fetch = FetchType.LAZY)
    private List <Libro> libros;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name="comentarios",
    				joinColumns = @JoinColumn (name= "usuario_id"),
    				inverseJoinColumns = @JoinColumn(name = "libro_id")
    		)
   private List<Libro> libros_comentados;
    
    public Usuario() {
    }
    public Usuario(Long id, String usuario, String nombre, String password, String email, String numero, int administrador) {
    	this.id=id;
    	this.usuario = usuario;
        this.nombre = nombre;
        this.password = password;
        this.email = email;
        this.numero = numero;
        this.administrador = administrador;
    }
    

    // getter id
    public Long getId() {
        return id;
    }
    // getter usuario
    public String getUsuario() {
        return usuario;
    }
    // getter nombre
    public String getNombre() {
        return nombre;
    }
    // getter password
    public String getPassword() {
        return password;
    }
    // getter email
    public String getEmail() {
        return email;
    }
    // getter numero
    public String getNumero() {
        return numero;
    }
    // getter administrador
    public int getAdministrador() {
        return administrador;
    }
    
    
    // setter id
    public void setId(Long id) {
    	this.id = id;
    }
    // setter usuario
    public void setUsuario(String usuario) {
    	this.usuario = usuario;
    }
    // setter nombre
    public void setNombre(String nombre) {
    	this.nombre = nombre;
    }
    // setter password
    public void setPassword(String password) {
    	this.password = password;
    }
    // setter email
    public void setEmail(String email) {
    	this.email = email;
    }
    // setter numero
    public void setNumero(String numero) {
    	this.numero = numero;
    }
    // setter administrador
    public void setAdministrador(int administrador) {
    	this.administrador = administrador;
    }
    
    //CONFIRM PASSWORD
	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setconfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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
