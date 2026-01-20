package com.manubp.ecommerce.service;

import java.util.Optional;

import com.manubp.ecommerce.model.Usuario;

public interface IUsuarioService {
	
	Optional<Usuario> findById(Integer id);

}
