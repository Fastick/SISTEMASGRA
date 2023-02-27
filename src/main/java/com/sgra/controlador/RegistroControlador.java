package com.sgra.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sgra.interfaceservice.IRecetaAsignadaService;
import com.sgra.service.UsuarioServicio;


@Controller
public class RegistroControlador {

	@Autowired
	private UsuarioServicio servicio;
	
	@Autowired
	private IRecetaAsignadaService recetaasignadaservice;
	
	@GetMapping("/login")
	public String iniciarSesion(Model model) {
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "login";
	}
	
	@GetMapping("/")
	public String verPaginaDeInicio(Model modelo) {
		modelo.addAttribute("usuarios", servicio.listarUsuarios());
		String estado="PENDIENTE";
		modelo.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "index";
	}
}