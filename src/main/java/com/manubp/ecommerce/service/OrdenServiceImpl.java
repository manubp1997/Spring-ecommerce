package com.manubp.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manubp.ecommerce.model.Orden;
import com.manubp.ecommerce.repository.IOrdenRepository;

@Service
public class OrdenServiceImpl implements IOrdenService {

	
	@Autowired
	private IOrdenRepository ordenRepository;
	
	@Override
	public Orden save(Orden orden) {

		return ordenRepository.save(orden);
	}

}
