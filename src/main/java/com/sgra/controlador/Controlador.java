package com.sgra.controlador;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sgra.interfaceservice.ICultivoService;
import com.sgra.interfaceservice.IEnfermedadService;
import com.sgra.interfaceservice.IRecetaAsignadaService;
import com.sgra.interfaceservice.IRecetaProductoService;
import com.sgra.interfaceservice.IProductoService;
import com.sgra.interfaceservice.IRecetaService;
import com.sgra.modelo.Cultivo;
import com.sgra.modelo.Enfermedad;
import com.sgra.modelo.Producto;
import com.sgra.modelo.Receta_Asignada;
import com.sgra.modelo.Usuario;
import com.sgra.service.UsuarioServicio;
import com.sgra.modelo.Receta;
import com.sgra.modelo.RecetaProducto;

@Controller
@RequestMapping
public class Controlador {

	@Autowired
	private IProductoService productoservice;

	@Autowired
	private ICultivoService cultivoservice;

	@Autowired
	private IRecetaAsignadaService recetaasignadaservice;

	@Autowired
	private IRecetaService recetaservice;

	@Autowired
	private IEnfermedadService enfermedadservice;

	@Autowired
	private IRecetaProductoService Recetaproductoservice;
	@Autowired
	private UsuarioServicio usuarioservice;

	///Reporte Clientes
	
	
		@GetMapping("/ReporteCultivos")
		public String ReporteCultivos(Model model, RedirectAttributes atributo) {
			List<Cultivo> Cultivos = cultivoservice.listar();
			model.addAttribute("Cultivos", Cultivos);
			return "/ReporteCultivos";
		
		}
		
	///reporte Productos
	
	@GetMapping("/ReporteProductos")
	public String ReporteProductos(Model model, RedirectAttributes atributo) {
		List<Producto> Productos = productoservice.listar();
		model.addAttribute("Productos", Productos);
		return "/ReporteProductos";
	
	}
	///reporte Enfermedades
	
			@GetMapping("/ReporteEnfermedades")
			public String ReporteEnfermedades(Model model, RedirectAttributes atributo) {
				List<Enfermedad> Enfermedad = enfermedadservice.listar();
				model.addAttribute("Enfermedad", Enfermedad);
				return "/ReporteEnfermedades";
			
			}
	//reporte Recetas
	
		@GetMapping("/ReporteRecetas")
		public String ReporteRecetas(Model model, RedirectAttributes atributo) {
			List<Receta> Receta = recetaservice.listar();
			model.addAttribute("Receta", Receta);
			return "/ReporteRecetas";
		
		}
	///reportes Solicitudes
	
	@GetMapping("/ReporteSolicitudes")
	public String ReporteSolicitudes(Model model, RedirectAttributes atributo) {
		List<Receta_Asignada> Receta_Asignada = recetaasignadaservice.listar();
		model.addAttribute("Receta_Asignada", Receta_Asignada);
		return "/ReporteSolicitudes";
	
	}
	
	///Reporte Clientes
	
	
	@GetMapping("/ReporteClientes")
	public String ReporteClientes(Model model, RedirectAttributes atributo) {
		List<Usuario> Usuario = usuarioservice.listarUsuarios();
		model.addAttribute("Usuario", Usuario);
		return "/ReporteClientes";
	
	}
	///REPORTES POR FECHAS
	@GetMapping("/Reportes")
	public String Report(@ModelAttribute("fechaInicio")String desde,
			@ModelAttribute("fechaFin")String hasta,Model model, RedirectAttributes atributo) {

		
		List<Receta_Asignada> buscarFecha=recetaasignadaservice.buscarFecha(desde, hasta);
		model.addAttribute("buscarFecha", buscarFecha);
	//reportes de usuario	
		List<Usuario> us = usuarioservice.listarUsuarios();
		model.addAttribute("us", us);
		///reportes de recetas asignadas
		List<Receta_Asignada> Receta_Asignada=recetaasignadaservice.listar();
		model.addAttribute("Receta_Asignada", Receta_Asignada);
		///lista de productos
		List<Producto> prod=productoservice.listar();
		model.addAttribute("prod", prod);
		//Contador de solicitudes
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		List<Cultivo> Cultivos=cultivoservice.listar();
		model.addAttribute("Cultivos", Cultivos);
		List<Enfermedad> Enfermedades=enfermedadservice.listar();
		model.addAttribute("Enfermedades", Enfermedades);
		List<Receta> Recetas=recetaservice.listar();
		model.addAttribute("Recetas", Recetas);
		List<Usuario> usuarios=usuarioservice.listarUsuarios();
		model.addAttribute("usuarios", usuarios);
		List<Receta_Asignada> RA=recetaasignadaservice.listar();
		model.addAttribute("RA", RA);
		return "/Reportes";

	}
	
	@GetMapping("/SeleccionarCliente")
	public String SeleccionarCliente( Model model,	RedirectAttributes atributo) {

		List<Usuario> usuario = usuarioservice.listarUsuarios();

		model.addAttribute("usuario", usuario);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "SeleccionaCliente";
		

	}

	@GetMapping("/SeleccionarEtapa/{idenfermedad}")
	public String SeleccionarEtapa(@PathVariable("idenfermedad") Long idenfermedad, Model model, RedirectAttributes atributo) {

		List<Enfermedad> enfer = enfermedadservice.listarIdEnfermedad(idenfermedad);
		model.addAttribute("enfer", enfer);
		List<Receta> Res=recetaservice.listar();
		model.addAttribute("Res", Res);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "SeleccionaEtapaCultivo";

	}
	@GetMapping("/SeleccionarRecetaCliente/{id}")
	public String SeleccionarReceta(@PathVariable("id")Long id,
			Model model, RedirectAttributes atributo) {

		// se debe hacer el control en la interfaz de ingreso para que la enfermedad se
		// elija con todo el cultivo y ese ID corrija la redundancia y errores
		// colaterales
		List<Usuario>  usuario= usuarioservice.buscarId(id);
		model.addAttribute("usuario", usuario);
		List<Receta> Res=recetaservice.listar();
		model.addAttribute("Res", Res);
		List<RecetaProducto> Receta= Recetaproductoservice.listar();
		model.addAttribute("Receta", Receta);
		 model.addAttribute("localDateTime", LocalDateTime.now());
		model.addAttribute("Receta_Asignada", new Receta_Asignada());
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));

     
		return "SeleccionaRecetaCliente";

	}

	///VerRecetaProductoAsignada
	@GetMapping("/VerRecetaProductoAsignada/{idrecetaasignada}/{idreceta}/{id}")
	public String SeleccionarRecetaProductoAsignada(@PathVariable("idrecetaasignada")Long idrecetaasignada,
			@PathVariable("idreceta")Long idreceta,
			@PathVariable("id")Long id,
			Model model, RedirectAttributes atributo) {

		// se debe hacer el control en la interfaz de ingreso para que la enfermedad se
		// elija con todo el cultivo y ese ID corrija la redundancia y errores
		// colaterales
		
		List<RecetaProducto> Receta = Recetaproductoservice.findByRecetaProducto(idreceta);
		model.addAttribute("Receta", Receta);
		
		List<Receta> Res = recetaservice.findByRecetas(idreceta);
		model.addAttribute("Res", Res);

		List<Receta_Asignada> RAC=recetaasignadaservice.buscarReceta(idrecetaasignada);
		model.addAttribute("RAC", RAC);
		
		List<Usuario> us=usuarioservice.buscarId(id);
		 model.addAttribute("localDateTime", LocalDateTime.now());
		model.addAttribute("us", us);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "/SeleccionaRecetaProductoAsignada";

	}
	@GetMapping("/SeleccionarHerbicida")
	public String SeleccionarHerbicida(Model model, RedirectAttributes atributo) {

//Lista de Herbicidas
		String etapa="HERBICIDA";
		List<Receta> Res=recetaservice.buscarHerbicida(etapa);
		model.addAttribute("Res", Res);
		List<RecetaProducto> Receta = Recetaproductoservice.listar();
		model.addAttribute("Receta", Receta);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "SeleccionaHerbicida";

	}
	@GetMapping("/SeleccionarReceta/{idenfermedad}/{etapa}")
	public String SeleccionarReceta(@PathVariable("idenfermedad") Long enfer, @PathVariable("etapa") String etapa,
			Model model, RedirectAttributes atributo) {

		// se debe hacer el control en la interfaz de ingreso para que la enfermedad se
		// elija con todo el cultivo y ese ID corrija la redundancia y errores
		// colaterales
		List<RecetaProducto> Receta = Recetaproductoservice.listar();
		model.addAttribute("Receta", Receta);
		List<Receta> Res = recetaservice.buscarEnfermedadEtapa(enfer, etapa);

		model.addAttribute("Res", Res);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));

		return "SeleccionaReceta";

	}


	// Interfaz para seleccionar la enfermedad del cultivo anteriormente
	// seleccionado

	@GetMapping("/SeleccionarEnfermedad/{idcultivo}")
	public String SeleccionarEnfermedad(@PathVariable("idcultivo") Long idcultivo, Model model,
			RedirectAttributes atributo) {

		List<Enfermedad> enf = enfermedadservice.findByIdcultivo(idcultivo);

		model.addAttribute("enf", enf);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "SeleccionaEnfermedad";

	}

	// Interfaz para seleccionar el cultivo
	@GetMapping("/SeleccionarCultivo")
	public String SeleccionarCultivo(Model model, RedirectAttributes atributo) {

		List<Cultivo> Cultivos = cultivoservice.listar();
		model.addAttribute("Cultivos", Cultivos);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "SeleccionaCultivo";

	}

	// Receta Agricola
	@GetMapping("/RecetaCultivo/{idreceta}")
	public String RecetaCultivo(@PathVariable("idreceta") Long idreceta, Model model, RedirectAttributes atributo) {

		List<Cultivo> Cultivos = cultivoservice.listar();
		model.addAttribute("Cultivos", Cultivos);
		model.addAttribute("Recetas", recetaservice.findByRecetas(idreceta));
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "RecetaCultivo";

	}

	

//CRUD de Usuario

	@GetMapping("/ListarUsuarios")
	public String listarUsuarios(Model modelo) {
	
		List<Usuario> usuarios = usuarioservice.listarUsuarios();
		modelo.addAttribute("usuarios", usuarios);
		String estado="PENDIENTE";
		modelo.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "/ListaUsuarios";
	}

	@GetMapping("/NuevoUsuario")
	public String agregarUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "RNUsuario";
	}

	@PostMapping("/saveNuevoUsuario")
	public String saveNuevoUsuario(@Valid UsuarioRegistroDTO p, Model model, RedirectAttributes atributo) {
		if(usuarioservice.findByIdentificacion(p.getIdentificacion())!=null) {
				return "redirect:/NuevoUsuario?error";
			
		}
		if(usuarioservice.encontrarUsuario(p.getEmail())!=null) {
			return "redirect:/NuevoUsuario?error";
		}
		
		usuarioservice.guardar(p);
		atributo.addFlashAttribute("success", "Usuario guardada con éxito");
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/ListarUsuarios?exito";
 
	}
	@PostMapping("/saveEditarUsuario")
	public String saveEditaUsuario(@Valid Usuario p, Model model, RedirectAttributes atributo) {

		usuarioservice.guardarEditar(p);
		atributo.addFlashAttribute("success", "Usuario guardado con éxito");
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/ListarUsuarios";
 
	}

	@GetMapping("/editarUsuario/{id}")
	public String editarUsuario(@PathVariable("id") Long id, Model model, RedirectAttributes atributo) {
		Optional<Usuario> usuario = usuarioservice.findById(id);
		model.addAttribute("usuario", usuario);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "RUsuario";

	}

	@GetMapping("/eliminarUsuario/{id}")
	public String eliminarUsuario(@PathVariable Long id, Model model, RedirectAttributes atributo) {
		usuarioservice.delete(id);
		atributo.addFlashAttribute("error", "Eliminado con éxito");
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/ListarUsuarios";

	}

	// CRUD de la Receta Asignada

	@GetMapping("/ListarRecetasAsignadas")
	public String listarRecetasA(Model model, UsuarioRegistroDTO us) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}
		@SuppressWarnings("null")
		String userName = userDetails.getUsername();
		List<Receta_Asignada> Receta_Asignada = recetaasignadaservice.BuscarRA(userName);
		model.addAttribute("Receta_Asignada", Receta_Asignada);
		List<Receta_Asignada> RA=recetaasignadaservice.listar();
		model.addAttribute("RA", RA);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		/*
		 * List<Usuario> usuarios=usuarioservice.listarUsuarios();
		 * model.addAttribute("usuarios", usuarios);
		 */

		return "/ListaRecetaAsignada";
	}

	@GetMapping("/NuevaRecetaAsignada")
	public String NuevoPA(Model model) {
		List<Receta> Recetas = recetaservice.listar();
		model.addAttribute("Recetas", Recetas);
		List<RecetaProducto> RecetaAsignada = Recetaproductoservice.listar();
		model.addAttribute("RecetaAsignada", RecetaAsignada);
		List<Usuario> us = usuarioservice.listarUsuarios();
		model.addAttribute("us", us);
		model.addAttribute("Receta_Asignada", new Receta_Asignada());
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "fRecetaAsignada";
	}

	@PostMapping("/saveRecetaAsignada")
	public String savePA(@Validated Receta_Asignada p, Receta r, Model model, RedirectAttributes atributo) {
		/*
		 * if (recetaasignadaservice.BuscarRA(p.getIdcliente(), p.getIdreceta(),
		 * p.getFecha()) != null) { atributo.addFlashAttribute("error",
		 * "La receta ya está asignada, por favor realice otra asignación"); return
		 * "redirect:/NuevaRecetaAsignada"; }
		 */
		recetaasignadaservice.save(p);
		atributo.addFlashAttribute("success", "Solicitud guardada con éxito");
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/ListarRecetasAsignadas";

	}

	@PostMapping("/saveEditarRecetaAsignada")
	public String saveEditarPA(@Validated Receta_Asignada p, Receta r, Model model, RedirectAttributes atributo) {

		recetaasignadaservice.save(p);
		atributo.addFlashAttribute("success", "Solicitud guardada con éxito");
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/ListarRecetasAsignadas";

	}

	@GetMapping("/editarRecetaAsignada/{idrecetaasignada}")
	public String editarPA(@PathVariable("idrecetaasignada") Long idrecetaasignada, Model model,
			RedirectAttributes atributo) {
		List<Receta> Recetas = recetaservice.listar();
		model.addAttribute("Recetas", Recetas);
		List<Usuario> us = usuarioservice.listarUsuarios();
		model.addAttribute("us", us);
		Optional<Receta_Asignada> Receta_Asignada = recetaasignadaservice.listarId(idrecetaasignada);
		 model.addAttribute("localDateTime", LocalDateTime.now());
		model.addAttribute("Receta_Asignada", Receta_Asignada);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "eRecetaAsignada";

	}

	@GetMapping("/eliminarRecetaAsignada/{idrecetaasignada}")
	public String eliminarRA(@PathVariable Long idrecetaasignada,Model model,RedirectAttributes atributo) {
		recetaasignadaservice.delete(idrecetaasignada);
		atributo.addFlashAttribute("error", "Eliminado con éxito");
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/ListarRecetasAsignadas";

	}

//CRUD de Receta Producto

	@GetMapping("/ListarRecetaProducto/{idreceta}")
	public String listarRecetaProducto(@PathVariable("idreceta") Long idreceta, Receta r, Model model) {
		Receta rs = Recetaproductoservice.BuscarDosificacion(idreceta);
		int con = 0, max = 0;

		/// clasificador de productos
		if (rs.getDosificacion() == 20) {
			con = 20;
			max = 300;
			List<Producto> P = Recetaproductoservice.Clasificador(con, max);
			model.addAttribute("P", P);
		}
		if (rs.getDosificacion() == 50) {
			con = 20;
			max = 600;
			List<Producto> P = Recetaproductoservice.Clasificador(con, max);
			model.addAttribute("P", P);
		}
		if (rs.getDosificacion() == 100) {
			con = 0;
			max = 650;
			List<Producto> P = Recetaproductoservice.Clasificador(con, max);
			model.addAttribute("P", P);
		}
		if (rs.getDosificacion() == 150) {
			con = 0;
			max = 800;
			List<Producto> P = Recetaproductoservice.Clasificador(con, max);
			model.addAttribute("P", P);
		}
		if (rs.getDosificacion() == 200) {
			con = 0;
			max = 1001;
			List<Producto> P = Recetaproductoservice.Clasificador(con, max);
			model.addAttribute("P", P);
		}
		if (rs.getDosificacion() == 400) {
			con = 0;
			max = 1001;
			List<Producto> P = Recetaproductoservice.Clasificador(con, max);
			model.addAttribute("P", P);
		}
		List<Cultivo> Cultivos = cultivoservice.listar();
		model.addAttribute("Cultivos", Cultivos);

		List<Producto> Productos = productoservice.listar();
		model.addAttribute("Productos", Productos);

		List<RecetaProducto> Res = Recetaproductoservice.findByRecetaProducto(r.getIdreceta());
		model.addAttribute("Res", Res);
		List<RecetaProducto> RecetaProductos = Recetaproductoservice.listar();
		model.addAttribute("RecetaProductos", RecetaProductos);

		List<Enfermedad> Enfermedades = enfermedadservice.listar();
		model.addAttribute("Enfermedades", Enfermedades);
		Receta Recetas = recetaservice.BuscarId(idreceta);
		model.addAttribute("Recetas", Recetas);
		model.addAttribute("RecetaProducto", new RecetaProducto());
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "fRecetaProducto";
	}

	@GetMapping("/SeleccionarHerbicidaProducto/{idreceta}")
	public String SeleccionarHerbicidaProducto(@PathVariable("idreceta") Long idreceta, Receta r, Authentication auth,
			Model model) {

		List<RecetaProducto> Receta = Recetaproductoservice.findByRecetaProducto(idreceta);
		model.addAttribute("Receta", Receta);
		
		List<Receta> Res = recetaservice.findByRecetas(idreceta);

		model.addAttribute("Res", Res);
		
		List<Receta_Asignada> RAC=recetaasignadaservice.buscarReceta(idreceta);
		model.addAttribute("RAC", RAC);
		
		List<Receta_Asignada> Receta_Asignada = recetaasignadaservice.listar();
		model.addAttribute("Receta_Asignada", Receta_Asignada);
		
		
		Receta Recetas = recetaservice.BuscarId(idreceta);
		model.addAttribute("Recetas", Recetas);
		// obtencion del ID del usuario logueado
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}
		@SuppressWarnings("null")
		String userName = userDetails.getUsername();
		Usuario us = usuarioservice.encontrarUsuario(userName);
		model.addAttribute("us", us);

		List<Enfermedad> Enfermedades = enfermedadservice.listar();
		model.addAttribute("Enfermedades", Enfermedades);
		 model.addAttribute("localDateTime", LocalDateTime.now());
		model.addAttribute("Receta_Asignada", new Receta_Asignada());
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "/SeleccionaHerbicidaProducto";
	}
	
	@GetMapping("/SeleccionarRecetaProducto/{idreceta}")
	public String SeleccionarRecetaProducto(@PathVariable("idreceta") Long idreceta, Receta r, Authentication auth,
			Model model) {

		List<RecetaProducto> Receta = Recetaproductoservice.findByRecetaProducto(idreceta);
		model.addAttribute("Receta", Receta);
		
		List<Receta> Res = recetaservice.findByRecetas(idreceta);

		model.addAttribute("Res", Res);
		
		List<Receta_Asignada> RAC=recetaasignadaservice.buscarReceta(idreceta);
		model.addAttribute("RAC", RAC);
		
		List<Receta_Asignada> Receta_Asignada = recetaasignadaservice.listar();
		model.addAttribute("Receta_Asignada", Receta_Asignada);
		
		
		Receta Recetas = recetaservice.BuscarId(idreceta);
		model.addAttribute("Recetas", Recetas);
		// obtencion del ID del usuario logueado
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}
		@SuppressWarnings("null")
		String userName = userDetails.getUsername();
		Usuario us = usuarioservice.encontrarUsuario(userName);
		model.addAttribute("us", us);

		List<Enfermedad> Enfermedades = enfermedadservice.listar();
		model.addAttribute("Enfermedades", Enfermedades);
		 model.addAttribute("localDateTime", LocalDateTime.now());
		model.addAttribute("Receta_Asignada", new Receta_Asignada());
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "/SeleccionaRecetaProducto";
	}

	@GetMapping("/VerRecetaProducto/{idreceta}")
	public String VerRecetaProducto(@PathVariable("idreceta") Long idreceta, Receta r, Model model) {

		List<Receta> Receta = recetaservice.findByRecetas(idreceta);
		model.addAttribute("Receta", Receta);
		List<RecetaProducto> Res=Recetaproductoservice.findByRecetaProducto(idreceta);
		model.addAttribute("Res", Res);
		// obtencion del ID del usuario logueado
				Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				UserDetails userDetails = null;
				if (principal instanceof UserDetails) {
					userDetails = (UserDetails) principal;
				}
				@SuppressWarnings("null")
				String userName = userDetails.getUsername();
				Usuario us = usuarioservice.encontrarUsuario(userName);
				model.addAttribute("us", us);
				 model.addAttribute("localDateTime", LocalDateTime.now());
				 model.addAttribute("Receta_Asignada", new Receta_Asignada());
		model.addAttribute("RecetaProducto", new RecetaProducto());
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "/VerRecetaProduct";
	}

	@PostMapping("/saveRecetaProducto")
	public String saveRecetaProducto(@Valid RecetaProducto p, Receta r, Producto s, Model model,
			RedirectAttributes atributo) {
		if (Recetaproductoservice.BuscarRepetido(r.getIdreceta(), s.getIdproducto()) != null) {

			atributo.addFlashAttribute("error", "El producto ya fue agregado, elija otro por favor");
			return "redirect:/ListarRecetaProducto/" + r.getIdreceta();
		}

		Recetaproductoservice.save(p);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/ListarRecetaProducto/" + r.getIdreceta();

	}

	@PostMapping("/saveEditarRecetaProducto")
	public String saveEditarecetaProducto(@Valid RecetaProducto p, Receta r, Producto s, Model model,
			RedirectAttributes atributo) {
		if (Recetaproductoservice.BuscarRepetido(r.getIdreceta(), s.getIdproducto()) != null) {

			atributo.addFlashAttribute("error", "El producto ya fue agregado, elija otro por favor");
			return "redirect:/editarRecetaProducto/" + r.getIdreceta();
		}

		Recetaproductoservice.save(p);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/editarRecetaProducto/" + r.getIdreceta();

	}

	@GetMapping("/editarRecetaProducto/{idreceta}")
	public String editarRecetaProducto(@PathVariable("idreceta") Long idreceta, Receta r, Model model,
			RedirectAttributes atributo) {

		Receta rs = Recetaproductoservice.BuscarDosificacion(idreceta);
		int con = 0, max = 0;

		/// clasificador de productos
		if (rs.getDosificacion() == 20) {
			con = 20;
			max = 300;
			List<Producto> P = Recetaproductoservice.Clasificador(con, max);
			model.addAttribute("P", P);
		}
		if (rs.getDosificacion() == 50) {
			con = 20;
			max = 600;
			List<Producto> P = Recetaproductoservice.Clasificador(con, max);
			model.addAttribute("P", P);
		}
		if (rs.getDosificacion() == 100) {
			con = 0;
			max = 650;
			List<Producto> P = Recetaproductoservice.Clasificador(con, max);
			model.addAttribute("P", P);
		}
		if (rs.getDosificacion() == 150) {
			con = 0;
			max = 800;
			List<Producto> P = Recetaproductoservice.Clasificador(con, max);
			model.addAttribute("P", P);
		}
		if (rs.getDosificacion() == 200) {
			con = 0;
			max = 1001;
			List<Producto> P = Recetaproductoservice.Clasificador(con, max);
			model.addAttribute("P", P);
		}
		if (rs.getDosificacion() == 400) {
			con = 0;
			max = 1001;
			List<Producto> P = Recetaproductoservice.Clasificador(con, max);
			model.addAttribute("P", P);
		}
		List<Cultivo> Cultivos = cultivoservice.listar();
		model.addAttribute("Cultivos", Cultivos);

		List<Producto> Productos = productoservice.listar();
		model.addAttribute("Productos", Productos);

		List<RecetaProducto> Res = Recetaproductoservice.findByRecetaProducto(r.getIdreceta());
		model.addAttribute("Res", Res);
		List<RecetaProducto> RecetaProductos = Recetaproductoservice.listar();
		model.addAttribute("RecetaProductos", RecetaProductos);

		List<Enfermedad> Enfermedades = enfermedadservice.listar();
		model.addAttribute("Enfermedades", Enfermedades);
		Receta Recetas = recetaservice.BuscarId(idreceta);
		model.addAttribute("Recetas", Recetas);
		model.addAttribute("RecetaProducto", new RecetaProducto());
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "eRecetaProducto";

	}

	@GetMapping("/eliminarRecetaProducto/{idrecetaproducto}/{idreceta}")
	public String eliminarRecetaProducto(@PathVariable Long idrecetaproducto, Receta r, Model model) {
		Recetaproductoservice.delete(idrecetaproducto);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/ListarRecetaProducto/" + r.getIdreceta();

	}

	@GetMapping("/eliminarEditarRecetaProducto/{idrecetaproducto}/{idreceta}")
	public String eliminarEditarRecetaProducto(@PathVariable Long idrecetaproducto, Receta r, Model model) {
		Recetaproductoservice.delete(idrecetaproducto);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/editarRecetaProducto/" + r.getIdreceta();

	}

//CRUD de Recetas	
	@GetMapping("/ListarRecetas")
	public String Listareceta(Model model, Receta p) {
		/// List<RecetaProducto>
		/// Res=Recetaproductoservice.findByRecetaProducto(p.getIdreceta());
		List<Enfermedad> Enfermedades = enfermedadservice.listar();
		model.addAttribute("Enfermedades", Enfermedades);
		List<Cultivo> Cultivos = cultivoservice.listar();
		model.addAttribute("Cultivos", Cultivos);
		List<RecetaProducto> Res = Recetaproductoservice.listar();
		model.addAttribute("Res", Res);
		List<Receta> Recetas = recetaservice.listar();
		model.addAttribute("Recetas", Recetas);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "/ListaRecetas";
	}

	@GetMapping("/NuevaReceta")
	public String agregarReceta(Model model) {
		List<Cultivo> Cultivos = cultivoservice.listar();
		model.addAttribute("Cultivos", Cultivos);
		List<Enfermedad> Enfermedades = enfermedadservice.listar();
		model.addAttribute("Enfermedades", Enfermedades);
		List<Receta> Recetas = recetaservice.listar();
		model.addAttribute("Recetas", Recetas);
		List<Producto> Productos = productoservice.listar();
		model.addAttribute("Productos", Productos);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		model.addAttribute("Recetas", new Receta());
		return "fRecetaP";
	}

	@PostMapping("/saveReceta")
	public String saveReceta(@Valid Receta p, Enfermedad e, Model model, RedirectAttributes atributo) {
		/*
		 * if (recetaservice.findByReceta(p.getNombre_receta(), p.getEtapa(),
		 * p.getIdcultivo(), p.getDosificacion(), p.getIdenfermedad()) != null) {
		 * atributo.addFlashAttribute("warning", "Existe una receta con esos datos!");
		 * return "redirect:/NuevaReceta"; } else {
		 */
		recetaservice.save(p);
		atributo.addFlashAttribute("sucess", "Registrado con éxito");
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/ListarRecetaProducto/" + p.getIdreceta();
		// }
	}

	@PostMapping("/saveEditarR")
	public String saveEditarR(@Valid Receta p, Cultivo c, Model model, RedirectAttributes atributo) {
		recetaservice.save(p);
		atributo.addFlashAttribute("sucess", "Registrado con éxito");
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/editarRecetaProducto/" + p.getIdreceta();

	}

	@PostMapping("/saveEditarReceta")
	public String saveEditarReceta(@Valid Receta p, Cultivo c, Model model, RedirectAttributes atributo) {
		recetaservice.save(p);
		atributo.addFlashAttribute("sucess", "Registrado con éxito");
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/ListarRecetaProducto/" + p.getIdreceta();

	}

	@GetMapping("/editarReceta/{idreceta}")
	public String editarReceta(@PathVariable("idreceta") Long idreceta, Model model, RedirectAttributes atributo) {
		List<Cultivo> Cultivos = cultivoservice.listar();
		model.addAttribute("Cultivos", Cultivos);
		List<Producto> Productos = productoservice.listar();
		model.addAttribute("Productos", Productos);
		Optional<Receta> Recetas = recetaservice.listarId(idreceta);

		model.addAttribute("Recetas", Recetas);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "eReceta";

	}

	@GetMapping("/eliminarReceta/{idreceta}")
	public String deleteReceta(@PathVariable Long idreceta,Model model ,RedirectAttributes atributo) {
		recetaservice.delete(idreceta);
		atributo.addFlashAttribute("error", "Receta eliminada con éxito");
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/ListarRecetas";

	}

//CRUD del Producto	

	@GetMapping("/ListarProductos")
	public String listar(Model model) {
		List<Producto> Productos = productoservice.listar();
		model.addAttribute("Productos", Productos);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "/ListaProductos";
	}

	@GetMapping("/NuevoProducto")
	public String agregar(Model model) {
		model.addAttribute("Producto", new Producto());
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "fProducto";
	}

	@PostMapping("/saveProducto")
	public String saveProducto(@Valid Producto p, @RequestParam("file") MultipartFile pimagen, Model model,
			RedirectAttributes atributo) {
		if (productoservice.findByNombre_producto(p.getNombre_producto(), p.getContenido(), p.getUnidad()) != null) {
			atributo.addFlashAttribute("error", "Producto y contenido existente! Ingrese otro");
			return "redirect:/NuevoProducto";
		}
		if (!pimagen.isEmpty()) {
			/// Path CultivoIMG= Paths.get("src//main//resources//static//images//");
			/// String rutaAbsoluta =CultivoIMG.toFile().getAbsolutePath();
			String rutaAbsoluta = "C://Temp//uploads";
			try {
				byte[] bytes = pimagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + pimagen.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				p.setPimagen(pimagen.getOriginalFilename());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		productoservice.save(p);
		atributo.addFlashAttribute("success", "Producto guardado con éxito");
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/ListarProductos";

	}

	@PostMapping("/saveEditarProducto")
	public String saveEditarProducto(@Valid Producto p, @RequestParam("file") MultipartFile pimagen, Model model,
			RedirectAttributes atributo) {
		if (!pimagen.isEmpty()) {
			/// Path CultivoIMG= Paths.get("src//main//resources//static//images//");
			/// String rutaAbsoluta =CultivoIMG.toFile().getAbsolutePath();
			String rutaAbsoluta = "C://Temp//uploads";
			try {
				byte[] bytes = pimagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + pimagen.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				p.setPimagen(pimagen.getOriginalFilename());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		productoservice.save(p);
		atributo.addFlashAttribute("success", "Producto guardado con éxito");
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/ListarProductos";

	}

	@GetMapping("/editarProducto/{idproducto}")
	public String editar(@PathVariable("idproducto") Long idproducto, Model model, RedirectAttributes atributo) {
		Optional<Producto> Producto = productoservice.listarId(idproducto);
		model.addAttribute("Producto", Producto);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "eProducto";

	}

	@GetMapping("/eliminarProducto/{id}")
	public String eliminarProducto(@PathVariable Long id, Model model, RedirectAttributes atributo) {
		productoservice.delete(id);
		atributo.addFlashAttribute("error", "Producto eliminado con éxito");
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/ListarProductos";

	}

//Crud de Cultivo

	@GetMapping("/ListarCultivos")
	public String listarCultivos(Model model) {
		List<Cultivo> Cultivos = cultivoservice.listar();
		model.addAttribute("Cultivos", Cultivos);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "/ListaCultivos";
	}

	@GetMapping("/NuevoCultivo")
	public String agregarcultivo(Model model) {

		model.addAttribute("Cultivo", new Cultivo());
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "fCultivo";
	}

	@PostMapping("/saveCultivo")
	public String saveCultivo(@Validated Cultivo p, Model model,@RequestParam("file") MultipartFile cimagen,
			RedirectAttributes atributo) {
		if (cultivoservice.findByNombreCultivo(p.getNombre_cultivo(), p.getVariedad()) != null) {
			atributo.addFlashAttribute("error", "El cultivo ya está ingresado, por favor ingrese otro");
			return "redirect:/NuevoCultivo";
		}
		if (!cimagen.isEmpty()) {
			/// Path CultivoIMG= Paths.get("src//main//resources//static//images//");
			/// String rutaAbsoluta =CultivoIMG.toFile().getAbsolutePath();
			String rutaAbsoluta = "C://Temp//uploads";
			try {
				byte[] bytes = cimagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + cimagen.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				p.setCimagen(cimagen.getOriginalFilename());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		cultivoservice.save(p);
		atributo.addFlashAttribute("success", "Guardado con éxito");
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/ListarCultivos";
	}

	@PostMapping("/saveEditarCultivo")
	public String saveEditarCultivo(@Valid Cultivo p, @RequestParam("file") MultipartFile cimagen, Model model,
			RedirectAttributes atributo) {
		if (!cimagen.isEmpty()) {
			/// Path CultivoIMG= Paths.get("src//main//resources//static//images//");
			/// String rutaAbsoluta =CultivoIMG.toFile().getAbsolutePath();
			String rutaAbsoluta = "C://Temp//uploads";
			try {
				byte[] bytes = cimagen.getBytes();
				Path rutaCompleta = Paths.get(rutaAbsoluta + "//" + cimagen.getOriginalFilename());
				Files.write(rutaCompleta, bytes);
				p.setCimagen(cimagen.getOriginalFilename());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		cultivoservice.save(p);
		atributo.addFlashAttribute("success", "Guardado con éxito");
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/ListarCultivos";

	}

	@GetMapping("/editarCultivo/{idcultivo}")
	public String editarcultivo(@PathVariable("idcultivo") Long idcultivo, Model model, RedirectAttributes atributo) {
		Optional<Cultivo> Cultivos = cultivoservice.listarId(idcultivo);
		model.addAttribute("Cultivo", Cultivos);
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "eCultivo";

	}

	@GetMapping("/eliminarCultivo/{idcultivo}")
	public String deleteCultivo(@PathVariable Long idcultivo, Model model, RedirectAttributes atributo) {
		cultivoservice.delete(idcultivo);
		atributo.addFlashAttribute("error", "Eliminado con éxito");
		String estado="PENDIENTE";
		model.addAttribute("cont",recetaasignadaservice.contador(estado));
		return "redirect:/ListarCultivos";

	}

}
