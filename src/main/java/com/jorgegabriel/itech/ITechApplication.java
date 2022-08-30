package com.jorgegabriel.itech;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jorgegabriel.itech.domain.Categoria;
import com.jorgegabriel.itech.domain.Cidade;
import com.jorgegabriel.itech.domain.Cliente;
import com.jorgegabriel.itech.domain.Endereco;
import com.jorgegabriel.itech.domain.Estado;
import com.jorgegabriel.itech.domain.Pagamento;
import com.jorgegabriel.itech.domain.PagamentoComBoleto;
import com.jorgegabriel.itech.domain.PagamentoComCartao;
import com.jorgegabriel.itech.domain.Pedido;
import com.jorgegabriel.itech.domain.Servico;
import com.jorgegabriel.itech.domain.enums.EstadoPagamento;
import com.jorgegabriel.itech.domain.enums.TipoCliente;
import com.jorgegabriel.itech.repositories.CategoriaRepository;
import com.jorgegabriel.itech.repositories.CidadeRepository;
import com.jorgegabriel.itech.repositories.ClienteRepository;
import com.jorgegabriel.itech.repositories.EnderecoRepository;
import com.jorgegabriel.itech.repositories.EstadoRepository;
import com.jorgegabriel.itech.repositories.PagamentoRepository;
import com.jorgegabriel.itech.repositories.PedidoRepository;
import com.jorgegabriel.itech.repositories.ServicoRepository;

@SpringBootApplication
public class ITechApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ServicoRepository servicoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;

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
		
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		
		Cliente cli1 =new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"),cli1,e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"),cli1,e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
		
		
	}

}
