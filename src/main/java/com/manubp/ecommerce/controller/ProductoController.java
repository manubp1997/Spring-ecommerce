package com.manubp.ecommerce.controller;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.manubp.ecommerce.model.Producto;
import com.manubp.ecommerce.model.Usuario;
import com.manubp.ecommerce.service.IUsuarioService;
import com.manubp.ecommerce.service.ProductoService;
import com.manubp.ecommerce.service.UploadFileService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	// Esto es para registrar logs en la consola
	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private UploadFileService uploadFileService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
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
	public String save(Producto producto, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
		
		LOGGER.info("Este es el producto guardado: {}", producto);		
		Usuario u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
		producto.setUsuario(u);
		
		// Imagen
		if(producto.getId() == null) { // Cuenado se crea un producto
			String nombreImagen = uploadFileService.saveImage(file);
			producto.setImagen(nombreImagen);
		} else {
			
		}
		
		
		productoService.save(producto);
		
		return "redirect:/productos";
	}
	
	
	// Este metodo selecciona un producto por su id y lo muestra en un formulario para editarlo
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Integer id, Model model) {
		
		Producto producto = new Producto();
		Optional<Producto> optionalProducto = productoService.get(id);
		producto = optionalProducto.get();
		
		LOGGER.info("Producto buscado para editar: {}", producto);
		
		model.addAttribute("producto", producto);
				
		return "productos/edit";
	}
	
	// Este metodo actualiza el producto en la base de datos
	@PostMapping("/update")
	public String update(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
		
		Producto p = new Producto();
		p = productoService.get(producto.getId()).get();
		
		if(file.isEmpty()) { // Cuando no se cambia la imagen al editar

			producto.setImagen(p.getImagen());
		}else {
			// Cuando se cambia la imagen al editar

			// Eliminar imagen cuando no es la default				
			if(!p.getImagen().equals("default.jpg")) {
				uploadFileService.deleteImage(p.getImagen());
			}
			
			String nombreImagen = uploadFileService.saveImage(file);
			producto.setImagen(nombreImagen);
		}
		
		producto.setUsuario(p.getUsuario());
		
		productoService.update(producto);
		
		return "redirect:/productos";
	}
	
	// Este metodo elimina un producto por su id
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		
		Producto p = new Producto();
		p = productoService.get(id).get();
		// Eliminar imagen cuando no es la default				
		if(!p.getImagen().equals("default.jpg")) {
			uploadFileService.deleteImage(p.getImagen());
		}
		
		productoService.delete(id);
		
		return "redirect:/productos";
	}
	

}
