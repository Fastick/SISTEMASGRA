package com.sgra.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sgra.controlador.UsuarioRegistroDTO;
import com.sgra.modelo.Rol;
import com.sgra.modelo.Usuario;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public UsuarioServicioImpl(UsuarioRepositorio usuarioRepositorio) {
		super();
		this.usuarioRepositorio = usuarioRepositorio;
	}
 
	@Override
	public Usuario guardar(UsuarioRegistroDTO registroDTO) {
		Usuario usuario = new Usuario(
				registroDTO.getNombre(),
				registroDTO.getApellido(),
				registroDTO.getEmail(),
				passwordEncoder.encode(registroDTO.getPassword()),
				registroDTO.getIdentificacion(),
				registroDTO.getTelefono(),
				Arrays.asList(new Rol("ROLE_USER")));
		
		return usuarioRepositorio.save(usuario);
	}
	
	@Override
	public Usuario guardarEditar(Usuario registroDTO) {
	
		Usuario usuario = new Usuario(
				registroDTO.getId(),
				registroDTO.getNombre(),
				registroDTO.getApellido(),
				registroDTO.getEmail(),
				passwordEncoder.encode(registroDTO.getPassword()),
				registroDTO.getIdentificacion(),
				registroDTO.getTelefono(),
				Arrays.asList(new Rol("ROLE_USER")));
		
		return usuarioRepositorio.save(usuario);
	}
   
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorio.findByEmail(username);
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
	
		return new User(usuario.getEmail(),usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	
	}
	
	

	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}
	
	@Override 
	public List<Usuario> listarUsuarios() {
		return (List<Usuario>)usuarioRepositorio.findAll();
	}

	@Override
	public Usuario findByIdentificacion(String identificacion) {
		
		return usuarioRepositorio.findByIdentificacion(identificacion);
	}

	@Override
	public Optional<Usuario> findById(Long id) {
		
		return usuarioRepositorio.findById(id);
	}

	@Override
	public void delete(Long id) {
		usuarioRepositorio.deleteById(id);
		
	}


	@Override
	public Usuario encontrarUsuario(String email) {
		
		return usuarioRepositorio.encontrarUsuario(email);
	}

	@Override
	public long save(Usuario p) {
	
		int resp=0;

		
		Usuario us = usuarioRepositorio.save(p);
		
		if(!us.equals(null)) {
			resp =1;
		}
		return resp;
	}

	@Override
	public List<Usuario> buscarId(Long id) {
		
		return (List<Usuario>)usuarioRepositorio.buscarId(id);
	}




	

	

}
