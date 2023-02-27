package com.sgra.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sgra.interfaceservice.IRecetaAsignadaService;
import com.sgra.service.UsuarioServicio;

@Controller
@RequestMapping("/registro")
public class RegistroUsuarioControlador {

	@Autowired
	private UsuarioServicio usuarioServicio;
	@Autowired
	private IRecetaAsignadaService recetaasignadaservice;

	public RegistroUsuarioControlador(UsuarioServicio usuarioServicio) {
		super();
		this.usuarioServicio = usuarioServicio;
	}
	
	@ModelAttribute("usuario")
	public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO(Model model) {
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return new UsuarioRegistroDTO();
	}

	@GetMapping
	public String mostrarFormularioDeRegistro(Model model) {
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "registro";
	}
	

	
	@PostMapping
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO, Model model) {
		if(usuarioServicio.findByIdentificacion(registroDTO.getIdentificacion())!=null) {
			
				return "redirect:/registro?error";
			
		}
		if(usuarioServicio.encontrarUsuario(registroDTO.getEmail())!=null) {
			return "redirect:/registro?error";
		}
		usuarioServicio.guardar(registroDTO);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/registro?exito";
	}
}