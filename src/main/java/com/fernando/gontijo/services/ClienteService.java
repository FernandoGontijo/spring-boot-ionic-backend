package com.fernando.gontijo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.fernando.gontijo.domain.Cliente;
import com.fernando.gontijo.dto.ClienteDTO;
import com.fernando.gontijo.repositories.ClienteRepository;
import com.fernando.gontijo.services.exceptions.DataIntegrityException;
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
	
	

	public Cliente update(Cliente obj) {		 // Método para ATUALIZAR novo elemento ao Banco de Dados 
		Cliente newObj = find(obj.getId());							// Metodo "Find" verifica se o id existe
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {				// Método para DELETAR novo elemento ao Banco de Dados 
		find(id);
		try {
			
			repo.deleteById(id);
			
		} catch (DataIntegrityViolationException e) {
			
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
			
		}
		
	}
	
	
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	
	
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){					// Paginação
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repo.findAll(pageRequest);
	}
	
	
	
	public Cliente fromDTO(ClienteDTO objDto) {			
		
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
		
	}
	
	private void updateData(Cliente newObj, Cliente obj) {  // Atualiza somente algums atributos do objeto Cliente
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
}
