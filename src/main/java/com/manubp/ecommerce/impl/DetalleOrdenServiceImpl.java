package com.manubp.ecommerce.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manubp.ecommerce.model.DetalleOrden;
import com.manubp.ecommerce.repository.IDetalleOrdenRepository;
import com.manubp.ecommerce.service.IDetalleOrdenService;

@Service
public class DetalleOrdenServiceImpl implements IDetalleOrdenService {
	
	@Autowired
	private IDetalleOrdenRepository detalleOrdenRepository;

	@Override
	public DetalleOrden save(DetalleOrden detalleOrden) {
		return detalleOrdenRepository.save(detalleOrden);
	}

}
