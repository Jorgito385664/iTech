package com.jorgegabriel.itech.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.jorgegabriel.itech.domain.PrestadorDeServico;
import com.jorgegabriel.itech.domain.enums.TipoPrestador;
import com.jorgegabriel.itech.dto.PrestadorDeServicoNewDTO;
import com.jorgegabriel.itech.repositories.PrestadorDeServicoRepository;
import com.jorgegabriel.itech.resources.exceptions.FieldMessage;
import com.jorgegabriel.itech.services.validations.utils.BR;

@PrestadorDeServicoInsert
public class PrestadorDeServicoInsertValidator implements ConstraintValidator<PrestadorDeServicoInsert, PrestadorDeServicoNewDTO> {
	
	@Autowired
	private PrestadorDeServicoRepository repo;
	
	@Override
	public void initialize(PrestadorDeServicoInsert ann) {
	}

	@Override
	public boolean isValid(PrestadorDeServicoNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>(); 
		
		if(objDto.getTipo().equals(TipoPrestador.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","CPF inválido"));
		}
		
		if(objDto.getTipo().equals(TipoPrestador.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","CNPJ inválido"));
		}
		
		PrestadorDeServico aux = repo.findByEmail(objDto.getEmail());
		
		if(aux != null) {
			list.add(new FieldMessage("email","Email já existente"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}