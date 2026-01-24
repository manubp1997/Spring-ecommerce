package com.manubp.ecommerce.service;

import java.util.List;

import com.manubp.ecommerce.model.Orden;
import com.manubp.ecommerce.model.Usuario;

public interface IOrdenService {
	
	// Listar todas las ordenes
	List<Orden> findAll();

	//Guardar una orden 
	Orden save(Orden orden);
	
	String generarNumeroOrden();
	
	List<Orden> findByUsuarioId(Usuario usuario);
}
