package com.manubp.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manubp.ecommerce.model.Orden;

@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Integer> {
	
	
	

}
