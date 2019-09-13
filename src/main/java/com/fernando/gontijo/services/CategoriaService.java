package com.fernando.gontijo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.gontijo.domain.Categoria;
import com.fernando.gontijo.repositories.CategoriaRepository;



@Service
public class CategoriaService {

	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) { // Método para buscar um obj no banco pelo ID 
		
		Optional<Categoria> obj = repo.findById(id); //sempre usar esse trechos para buscar obj por ID no Spring 2
		return obj.orElse(null);
		
	}
	
}
