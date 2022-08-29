package com.jorgegabriel.itech;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jorgegabriel.itech.domain.Categoria;
import com.jorgegabriel.itech.domain.Servico;
import com.jorgegabriel.itech.repositories.CategoriaRepository;
import com.jorgegabriel.itech.repositories.ServicoRepository;

@SpringBootApplication
public class ITechApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ServicoRepository servicoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ITechApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Categoria 1");
		Categoria cat2 = new Categoria(null, "Categoria 2");
		
		Servico s1 = new Servico(null, "Serviço 1", 2000.00);
		Servico s2 = new Servico(null, "Serviço 2", 800.00);
		Servico s3 = new Servico(null, "Serviço 3", 80.00);
		
		//associando o serviço com a categoria  e vice versa
		
		cat1.getServicos().addAll(Arrays.asList(s1,s2,s3));
		cat2.getServicos().addAll(Arrays.asList(s2));
		
		s1.getCategorias().addAll(Arrays.asList(cat1));
		s2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		s3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		servicoRepository.saveAll(Arrays.asList(s1,s2,s3));
		
		
		
	}

}
