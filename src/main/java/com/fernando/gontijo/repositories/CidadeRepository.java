package com.fernando.gontijo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fernando.gontijo.domain.Cidade;


@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

	
}


