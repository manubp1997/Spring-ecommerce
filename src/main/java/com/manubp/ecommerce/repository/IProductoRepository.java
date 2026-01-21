package com.manubp.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manubp.ecommerce.model.Producto;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {
	
	
	
}
