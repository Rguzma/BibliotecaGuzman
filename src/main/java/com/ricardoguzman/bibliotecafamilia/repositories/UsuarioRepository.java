package com.ricardoguzman.bibliotecafamilia.repositories;
import com.ricardoguzman.bibliotecafamilia.models.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsuarioRepository extends CrudRepository <Usuario, Long>{
	
	Usuario findByEmail (String user_o_email);
	Usuario findByUsuario (String  user_o_email);
	Usuario findByNombre (String  nombre);
	
    @Transactional
    @Query (value= "SELECT * from usuarios Where usuarios.usuario=(?1) and usuarios.id != (?2);", nativeQuery=true)
    List <Usuario> seCopiaUsuario(String usuario, Long id);
	
    @Transactional
    @Query (value= "SELECT * from usuarios Where usuarios.nombre=(?1) and usuarios.id != (?2);", nativeQuery=true)
    List <Usuario> seCopiaNombre(String nombre, Long id);
    
    @Transactional
    @Query (value= "SELECT * from usuarios Where usuarios.email= ?1 and usuarios.id != (?2);", nativeQuery=true)
    List <Usuario> seCopiaEmail(String email, Long id);
	
    @Transactional
    @Query (value= "SELECT * from usuarios", nativeQuery=true)
    List <Usuario> mostrarTablaUsuarios();
    
    @Transactional
    @Query (value= "SELECT * from usuarios WHERE id !=?1", nativeQuery=true)
    List <Usuario> tablaOtrosUsuarios(Long id);
    
    @Transactional
    @Query (value= "SELECT * from usuarios WHERE id =?1", nativeQuery=true)
    Usuario findUsuarioId(Long id);
    
    @Modifying
    @Transactional
    @Query (value= "INSERT INTO usuarios (usuario,nombre,password,email,numero,administrador, creado_en, actualizado_en) VALUES (?1,?2,?3,?4,?5,?6,NOW(),NOW());", nativeQuery=true)
    void insertUsuario (String usuario, String nombre, String hashed_password, String email, String numero, int administrador);
    
    @Modifying
    @Transactional
    @Query (value= "UPDATE biblioteca_guzman_schema.usuarios SET usuario = ?1, nombre =?2, password = ?3, email =?4, numero =?5, administrador =?6, actualizado_en= now() WHERE id = (?7);", nativeQuery=true)
    void updateUsuario (String usuario, String nombre, String hashed_password, String email, String numero, int administrador, Long id);
    
    @Modifying
    @Transactional
    @Query (value= "DELETE FROM usuarios Where id = (?1);", nativeQuery=true)
    void deleteById (Long id);
    
}
