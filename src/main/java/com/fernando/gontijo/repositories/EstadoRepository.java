package com.fernando.gontijo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fernando.gontijo.domain.Estado;


@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

	
}


