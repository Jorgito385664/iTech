package com.jorgegabriel.itech.resources;



import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jorgegabriel.itech.domain.PrestadorDeServico;
import com.jorgegabriel.itech.dto.PrestadorDeServicoDTO;
import com.jorgegabriel.itech.dto.PrestadorDeServicoNewDTO;
import com.jorgegabriel.itech.services.PrestadorDeServicoService;

@RestController
@RequestMapping(value="/prestadores")
public class PrestadorDeServicoResource {
	
	@Autowired
	private PrestadorDeServicoService service;
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<PrestadorDeServico> find(@PathVariable Integer id) {
		
		PrestadorDeServico obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody PrestadorDeServicoNewDTO objDto){
		PrestadorDeServico obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody PrestadorDeServicoDTO objDto, @PathVariable Integer id){
		PrestadorDeServico obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PrestadorDeServicoDTO>> findAll() {
		
		List<PrestadorDeServico> list = service.findAll();
		List<PrestadorDeServicoDTO> listDto =list.stream().map(obj -> new PrestadorDeServicoDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
		
	}
	
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<PrestadorDeServicoDTO>> findPage(
			@RequestParam(value="page",defaultValue="0")Integer page, 
			@RequestParam(value="linesPerPage",defaultValue="24")Integer linesPerPage, 
			@RequestParam(value="orderBy",defaultValue="nome")String orderBy, 
			@RequestParam(value="direction",defaultValue="ASC")String direction) {
		
		Page<PrestadorDeServico> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<PrestadorDeServicoDTO> listDto =list.map(obj -> new PrestadorDeServicoDTO(obj));
		
		return ResponseEntity.ok().body(listDto);
		
	}
}
