package com.jorgegabriel.itech.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jorgegabriel.itech.domain.Categoria;
import com.jorgegabriel.itech.domain.Servico;
import com.jorgegabriel.itech.repositories.CategoriaRepository;
import com.jorgegabriel.itech.repositories.ServicoRepository;
import com.jorgegabriel.itech.services.exceptions.ObjectNotFoundException;

@Service
public class ServicoService {
	
	@Autowired
	private ServicoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Servico find(Integer id) {
		Optional<Servico> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Servico.class.getName()));
		
	}
	
	public Page<Servico> search(String nome, List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String direction){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome,categorias, pageRequest);
		
	}
	
}
