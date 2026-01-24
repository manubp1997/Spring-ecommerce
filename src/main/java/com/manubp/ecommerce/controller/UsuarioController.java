package com.manubp.ecommerce.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manubp.ecommerce.model.Usuario;
import com.manubp.ecommerce.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
	
	private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private IUsuarioService usuarioService;
	
	// Aquí irían los métodos para manejar las solicitudes relacionadas con usuarios
	
	// Método para mostrar el formulario de registro de usuario
	@GetMapping("/registro")
	public String create() {
		
		return "usuario/registro";
	}
	
	// Método para guardar un nuevo usuario
	@PostMapping("/save")
	public String save(Usuario usuario) {
		
		logger.info("Usuario registro: {}" + usuario);
		
		usuario.setTipo("USER");
		usuarioService.save(usuario);		
		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "usuario/login";
	}
	
	@PostMapping("/acceder")
	public String acceder(Usuario usuario, HttpSession session) {
		
		logger.info("Acceso: {}" + usuario);
		
		Optional<Usuario> user = usuarioService.findByEmail(usuario.getEmail());
		//logger.info("Usuario obtenido de la db: {}" + user.get());
		
		if(user.isPresent()) {
			session.setAttribute("idusuario", user.get().getId());
			
			if (user.get().getTipo().equals("ADMIN")) {
				return "redirect:/administrador";
			}else {
				return "redirect:/";
			}
		}else {
			logger.info("El usuario no existe");
		}
		
		return "redirect:/";
	}
	
	// Obtener compras del usuario logueado
	@GetMapping("/compras")
	public String obtenerCompras(Model model,HttpSession session) {
		
		model.addAttribute("sesion", session.getAttribute("idusuario"));
		
		return "usuario/compras";
	}

}
