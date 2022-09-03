package com.jorgegabriel.itech.services;

import java.util.Optional;

import com.jorgegabriel.itech.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jorgegabriel.itech.domain.PrestadorDeServico;
import com.jorgegabriel.itech.repositories.PrestadorDeServicoRepository;

@Service
public class PrestadorDeServicoService {
	
	@Autowired
	private PrestadorDeServicoRepository repo;
	
	public PrestadorDeServico find(Integer id) {
		Optional<PrestadorDeServico> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + PrestadorDeServico.class.getName()));
		
	}
	
}
