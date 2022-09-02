package com.jorgegabriel.itech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jorgegabriel.itech.domain.PrestadorDeServico;

@Repository
public interface PrestadorDeServicoRepository extends JpaRepository<PrestadorDeServico, Integer>{
	
}
