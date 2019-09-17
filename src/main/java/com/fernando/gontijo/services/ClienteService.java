package com.fernando.gontijo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernando.gontijo.domain.Cliente;
import com.fernando.gontijo.repositories.ClienteRepository;
import com.fernando.gontijo.services.exceptions.ObjectNotFoundException;



@Service
public class ClienteService {

	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) { // Método para buscar um obj no banco pelo ID 
		
		Optional<Cliente> obj = repo.findById(id); //sempre usar esse trechos para buscar obj por ID no Spring 2
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())); // lança uma excessão caso o id não exista
		
	}
	
}
