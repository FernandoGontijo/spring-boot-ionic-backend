package com.fernando.gontijo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fernando.gontijo.domain.Categoria;


@Repository
public interface CategoriaRepositoy extends JpaRepository<Categoria, Integer> {

	
}


