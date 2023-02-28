package com.sgra.service;

import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.sgra.controlador.UsuarioRegistroDTO;
import com.sgra.modelo.Usuario;


public interface UsuarioServicio extends UserDetailsService{

	public long save(Usuario p);
	public Usuario guardar(UsuarioRegistroDTO registroDTO);
	public Usuario guardarEditar(Usuario registroDTO);
	public List<Usuario> listarUsuarios();
	public Usuario findByIdentificacion(String identificacion);
	public Optional<Usuario> findById(Long id);
	public void delete(Long id);
	public List<Usuario> buscarId(Long  id);
	public Usuario encontrarUsuario(String email);
	
}
