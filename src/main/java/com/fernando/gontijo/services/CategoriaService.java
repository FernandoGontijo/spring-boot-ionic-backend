package com.fernando.gontijo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fernando.gontijo.domain.Categoria;
import com.fernando.gontijo.repositories.CategoriaRepository;
import com.fernando.gontijo.services.exceptions.DataIntegrityException;
import com.fernando.gontijo.services.exceptions.ObjectNotFoundException;



@Service
public class CategoriaService {

	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) { // Método para buscar um obj no banco pelo ID 
		
		Optional<Categoria> obj = repo.findById(id); //sempre usar esse trechos para buscar obj por ID no Spring 2
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())); // lança uma excessão caso o id não exista
		
	}
	
	
	public Categoria insert(Categoria obj) {        // Método para ADICIONAR novo elemento ao Banco de Dados 
		obj.setId(null);
		return repo.save(obj);
	}
	
	
	public Categoria update(Categoria obj) {		 // Método para ATUALIZAR novo elemento ao Banco de Dados 
		find(obj.getId());							// Metodo "Find" verifica se o id existe
		return repo.save(obj);
	}
	
	public void delete(Integer id) {				// Método para DELETAR novo elemento ao Banco de Dados 
		find(id);
		try {
			
			repo.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
			
		}
		
	}
	
	
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
}
