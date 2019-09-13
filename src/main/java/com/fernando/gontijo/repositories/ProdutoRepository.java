package com.fernando.gontijo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fernando.gontijo.domain.Produto;


@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	
}


