package com.manubp.ecommerce.service;

import java.util.List;

import com.manubp.ecommerce.model.Orden;

public interface IOrdenService {
	
	List<Orden> findAll();

	//Guardar una orden 
	Orden save(Orden orden);
}
