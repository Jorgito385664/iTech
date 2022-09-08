package com.jorgegabriel.itech.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jorgegabriel.itech.domain.Cidade;
import com.jorgegabriel.itech.domain.Endereco;
import com.jorgegabriel.itech.domain.PrestadorDeServico;
import com.jorgegabriel.itech.domain.enums.TipoPrestador;
import com.jorgegabriel.itech.dto.PrestadorDeServicoDTO;
import com.jorgegabriel.itech.dto.PrestadorDeServicoNewDTO;
import com.jorgegabriel.itech.repositories.EnderecoRepository;
import com.jorgegabriel.itech.repositories.PrestadorDeServicoRepository;
import com.jorgegabriel.itech.services.exceptions.DataIntegrityException;
import com.jorgegabriel.itech.services.exceptions.ObjectNotFoundException;

@Service
public class PrestadorDeServicoService {
	
	@Autowired
	private PrestadorDeServicoRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public PrestadorDeServico find(Integer id) {
		Optional<PrestadorDeServico> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + PrestadorDeServico.class.getName()));
		
	}
	
	@Transactional
	public PrestadorDeServico insert(PrestadorDeServico obj) {
		obj.setId(null);
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecosPrestador());
		return obj;
	}
	
	public PrestadorDeServico update(PrestadorDeServico obj) {
		PrestadorDeServico newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}
	}
	
	public List<PrestadorDeServico> findAll(){
		return repo.findAll();
	}
	
	
	public Page<PrestadorDeServico> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public PrestadorDeServico fromDTO(PrestadorDeServicoDTO objDto) {
		return new PrestadorDeServico(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	
	public PrestadorDeServico fromDTO(PrestadorDeServicoNewDTO objDto) {
		
		PrestadorDeServico pds = new PrestadorDeServico(null,objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoPrestador.toEnum(objDto.getTipo()));
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(),objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(),objDto.getCep(), pds, cid);
		
		pds.getEnderecosPrestador().add(end);
		pds.getTelefones().add(objDto.getTelefone1());
		
		if(objDto.getTelefone2() !=  null) {
			pds.getTelefones().add(objDto.getTelefone2());
		}
		
		if(objDto.getTelefone3() !=  null) {
			pds.getTelefones().add(objDto.getTelefone3());
		}
		
		
		return pds;
	}
	
	private void updateData(PrestadorDeServico newObj, PrestadorDeServico obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
}
