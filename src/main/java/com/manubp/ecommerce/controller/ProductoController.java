package com.manubp.ecommerce.controller;

import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manubp.ecommerce.model.Producto;
import com.manubp.ecommerce.model.Usuario;
import com.manubp.ecommerce.service.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	// Esto es para registrar logs en la consola
	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private ProductoService productoService;
	
	@GetMapping("")
	public String show(Model model) {
		
		model.addAttribute("productos", productoService.findAll());
		
		return "productos/show";
	}
	
	@GetMapping("/create")
	public String create() {
		return "productos/create";
	}
	
	
	
	// Este metodo guarda el producto en la base de datos
	@PostMapping("/save")
	public String save(Producto producto) {
		
		LOGGER.info("Este es el producto guardado: {}", producto);		
		Usuario u = new Usuario(1, "", "", "", "", "", "", "");
		producto.setUsuario(u);
		
		productoService.save(producto);
		
		return "redirect:/productos";
	}
	
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		
		Producto producto = new Producto();
		Optional<Producto> optionalProducto = productoService.get(id);
		producto = optionalProducto.get();
		
		LOGGER.info("Producto buscado para editar: {}", producto);
		
		model.addAttribute("producto", producto);
				
		return "productos/edit";
	}
	
	@PostMapping("/update")
	public String update(Producto producto) {
		
		productoService.update(producto);
		
		return "redirect:/productos";
	}
	

}
