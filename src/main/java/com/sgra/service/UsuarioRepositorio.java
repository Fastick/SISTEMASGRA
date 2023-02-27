package com.sgra.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sgra.modelo.Usuario;


@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario, Long>{
 
		public Usuario findByEmail(String email);
	
		public Usuario findByIdentificacion(String identificacion); 
		public Optional<Usuario> findById(Long id);
		@Query("select u from Usuario u where u.email =:email")
		public Usuario encontrarUsuario(@Param("email")String email);
		@Query("select u from Usuario u where u.id =:id")
		public List<Usuario> buscarId(@Param("id")Long  id);

		
		
		

		
	
}