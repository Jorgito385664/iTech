package com.jorgegabriel.itech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jorgegabriel.itech.domain.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer>{
	
}
