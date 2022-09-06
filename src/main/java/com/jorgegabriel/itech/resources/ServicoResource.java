package com.jorgegabriel.itech.resources;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jorgegabriel.itech.domain.Servico;
import com.jorgegabriel.itech.dto.ServicoDTO;
import com.jorgegabriel.itech.resources.utils.URL;
import com.jorgegabriel.itech.services.ServicoService;

@RestController
@RequestMapping(value="/servicos")
public class ServicoResource {
	
	@Autowired
	private ServicoService service;
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Servico> find(@PathVariable Integer id) {
		
		Servico obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
		
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ServicoDTO>> findPage(
			@RequestParam(value="nome",defaultValue="")String nome,
			@RequestParam(value="categorias",defaultValue="")String categorias,
			@RequestParam(value="page",defaultValue="0")Integer page, 
			@RequestParam(value="linesPerPage",defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy",defaultValue="nome")String orderBy, 
			@RequestParam(value="direction",defaultValue="ASC")String direction) {
		
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		
		Page<Servico> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ServicoDTO> listDto =list.map(obj -> new ServicoDTO(obj));
		
		return ResponseEntity.ok().body(listDto);
		
	}
}
