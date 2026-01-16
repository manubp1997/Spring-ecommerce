package com.manubp.ecommerce.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


// Servicio para manejar la subida y eliminación de archivos
@Service
public class UploadFileService {
	
	private String folder = "images//";
	
	// Guarda una imagen en el servidor
	public String saveImage(MultipartFile file) throws IOException {
		
		// Lógica para guardar la imagen
		if(!file.isEmpty()) {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(folder + file.getOriginalFilename());
			Files.write(path, bytes);
			return file.getOriginalFilename();
		}
		
		return "default.jpg";
	}
	
	// Elimina una imagen del servidor
	public void deleteImage(String nombreImagen) {
		// Lógica para eliminar la imagen
		String rutaImagen = "images//";
		
		File file = new File(rutaImagen + nombreImagen);
		file.delete();
	}

}
