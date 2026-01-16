package com.manubp.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.manubp.ecommerce.model.Producto;

public interface ProductoService {
	
	public Producto save(Producto producto);
	
	public Optional<Producto> get(Integer id);
	
	public void update(Producto producto);
	
	public void delete(Integer id);
	
	// Listar todos los productos
	public List<Producto> findAll();

}
