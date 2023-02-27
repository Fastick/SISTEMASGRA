package com.sgra.controlador;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sgra.interfaceservice.ICultivoService;
import com.sgra.interfaceservice.IEnfermedadService;
import com.sgra.interfaceservice.IRecetaAsignadaService;
import com.sgra.modelo.Cultivo;
import com.sgra.modelo.Enfermedad; 

@Controller
@RequestMapping
public class EnfermedadControler {
 
 
	@Autowired
	private IEnfermedadService enfermedadservice; 
	@Autowired
	private ICultivoService cultivoservice;
	@Autowired
	private IRecetaAsignadaService recetaasignadaservice;
	
	@GetMapping("/NuevaEnfermedad")
	public String formularioEnfermedad(Model model) {
		List<Cultivo> Cultivos = cultivoservice.listar();
		model.addAttribute("Cultivos", Cultivos);
		model.addAttribute("Enfermedad",new Enfermedad());
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "fEnfermedad";
	}
	@PostMapping("/saveEditarEnfermedad")
	public String saveEditarEnfermedad(@Validated Enfermedad p,Model model, @RequestParam("file")MultipartFile imagen ,RedirectAttributes atributo) {
		
		if(!imagen.isEmpty()) {
			///Path enf= Paths.get("src//main//resources//static//images/");
			String rutaAbsoluta = "C://Temp//uploads";///enf.toFile().getAbsolutePath();
			
			try {
				byte[] bytes=imagen.getBytes();
				Path rutaCompleta =Paths.get(rutaAbsoluta+"//"+imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				p.setImagen(imagen.getOriginalFilename());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		enfermedadservice.save(p);
		atributo.addFlashAttribute("success", "Guardado con éxito");
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/ListarEnfermedades";
	}
	@PostMapping("/saveEnfermedad")
	public String saveEnfermedad(@Validated Enfermedad p, @RequestParam("file")MultipartFile imagen ,Model model,RedirectAttributes atributo) {
		
		/*
		if (enfermedadservice.buscarRepetido(p.getNombre_enfermedad(),p.getIdcultivo().getIdcultivo())!=null) {
			atributo.addFlashAttribute("error", "Ya existe una enfermedad con esos datos, por favor ingrese otro");
			return "redirect:/NuevaEnfermedad";
		}*/
		if(!imagen.isEmpty()) {
			///Path enf= Paths.get("src//main//resources//static//images/");
			String rutaAbsoluta = "C://Temp//uploads";///enf.toFile().getAbsolutePath();
			
			try {
				byte[] bytes=imagen.getBytes();
				Path rutaCompleta =Paths.get(rutaAbsoluta+"//"+imagen.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				p.setImagen(imagen.getOriginalFilename());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		enfermedadservice.save(p);
		atributo.addFlashAttribute("success", "Guardado con éxito");
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/ListarEnfermedades";
	}
	
	@GetMapping("/ListarEnfermedades")
	public String listarEnfermedades(Model model) {
		List<Enfermedad> Enfermedades= enfermedadservice.listar();
		model.addAttribute("Enfermedades",Enfermedades);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "ListaEnfermedades";
	}
	
	@GetMapping("/editarEnfermedad/{idenfermedad}")
	public String editarUsuario(@PathVariable("idenfermedad")Long idenfermedad,  @Validated Enfermedad e,Model model, RedirectAttributes atributo) {
		
		Optional<Enfermedad> enfermedad = enfermedadservice.listarId(idenfermedad);
		List<Cultivo> Cultivos = cultivoservice.listar();
		model.addAttribute("Cultivos", Cultivos); 
		model.addAttribute("Enfermedad", enfermedad);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "eEnfermedad";

	}
	
	@GetMapping("/eliminarEnfermedad/{idenfermedad}")
	public String eliminarEnfermedad(@PathVariable("idenfermedad")Long idenfermedad, Model model, RedirectAttributes atributo) {
		enfermedadservice.delete(idenfermedad);
		atributo.addFlashAttribute("error", "Eliminado con éxito");
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/ListarEnfermedades";

	}
	
	
	
}
