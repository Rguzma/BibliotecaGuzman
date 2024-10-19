package com.ricardoguzman.bibliotecafamilia.services;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.ricardoguzman.bibliotecafamilia.repositories.*;
import com.ricardoguzman.bibliotecafamilia.models.*;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public List<Usuario> tablaUsuarios(){
    	return usuarioRepository.mostrarTablaUsuarios();
    }
    public List<Usuario> listaUsuarioExceptoPropio(Long id){
    	return usuarioRepository.tablaOtrosUsuarios(id);
    	
    }
    public void crearUsuario(String usuario,String nombre, String password, String email, String numero, int administrador){
        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());
    	usuarioRepository.insertUsuario(usuario, nombre, hashed_password, email, numero, administrador);
    }
    
    public void actualizarUsuario (String usuario,String nombre, String password, String email, String numero, int administrador, Long id){
    	String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());
    	System.out.println("Has llegado al service");
    	usuarioRepository.updateUsuario (usuario, nombre, hashed_password, email, numero, administrador, id);
    }
    public void borrarUsuario(Long id ) {
    	System.out.println("Este es el service de de borrar");
    	usuarioRepository.deleteById(id);
    }
    
    public Usuario getUserByEmail(String user_o_email ) {
    	return usuarioRepository.findByEmail(user_o_email);
    }
    public Usuario getUserByUsuario(String user_o_email ) {
    	return usuarioRepository.findByUsuario(user_o_email);
    }
    public Usuario getUserByNombre(String nombre ) {
    	return usuarioRepository.findByNombre(nombre);
    }
    
    public List<Usuario> otroUserByEmail(String email, Long id ) {
    	return usuarioRepository.seCopiaEmail(email, id);
    }
    public List<Usuario> otroUserByUsuario(String usuario, Long id ) {
    	return usuarioRepository.seCopiaUsuario(usuario, id);
    }
    public List<Usuario> otroUserByNombre(String nombre, Long id ) {
    	return usuarioRepository.seCopiaNombre(nombre, id);
    }
    
    public Usuario getUsuarioById (Long id) {
    	return usuarioRepository.findUsuarioId (id);
    }

}
