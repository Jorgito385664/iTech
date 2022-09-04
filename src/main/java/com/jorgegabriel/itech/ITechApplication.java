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
import com.jorgegabriel.itech.domain.ItemPedido;
import com.jorgegabriel.itech.domain.Pagamento;
import com.jorgegabriel.itech.domain.PagamentoComBoleto;
import com.jorgegabriel.itech.domain.PagamentoComCartao;
import com.jorgegabriel.itech.domain.Pedido;
import com.jorgegabriel.itech.domain.PrestadorDeServico;
import com.jorgegabriel.itech.domain.Servico;
import com.jorgegabriel.itech.domain.enums.EstadoPagamento;
import com.jorgegabriel.itech.domain.enums.TipoCliente;
import com.jorgegabriel.itech.repositories.CategoriaRepository;
import com.jorgegabriel.itech.repositories.CidadeRepository;
import com.jorgegabriel.itech.repositories.ClienteRepository;
import com.jorgegabriel.itech.repositories.EnderecoRepository;
import com.jorgegabriel.itech.repositories.EstadoRepository;
import com.jorgegabriel.itech.repositories.ItemPedidoRepository;
import com.jorgegabriel.itech.repositories.PagamentoRepository;
import com.jorgegabriel.itech.repositories.PedidoRepository;
import com.jorgegabriel.itech.repositories.PrestadorDeServicoRepository;
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
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private PrestadorDeServicoRepository prestadorDeServicoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ITechApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Categoria 1");
		Categoria cat2 = new Categoria(null, "Categoria 2");
		Categoria cat3 = new Categoria(null, "Categoria 3");
		Categoria cat4 = new Categoria(null, "Categoria 4");
		Categoria cat5 = new Categoria(null, "Categoria 5");
		Categoria cat6 = new Categoria(null, "Categoria 6");
		Categoria cat7 = new Categoria(null, "Categoria 7");
		
		Servico s1 = new Servico(null, "Serviço 1", 2000.00);
		Servico s2 = new Servico(null, "Serviço 2", 800.00);
		Servico s3 = new Servico(null, "Serviço 3", 80.00);
		
		//associando o serviço com a categoria  e vice versa
		
		cat1.getServicos().addAll(Arrays.asList(s1,s2,s3));
		cat2.getServicos().addAll(Arrays.asList(s2));
		
		s1.getCategorias().addAll(Arrays.asList(cat1));
		s2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		s3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3,cat4,cat5,cat6,cat7));
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
		
		
PrestadorDeServico pds1 =new PrestadorDeServico(null, "Jorge Silva", "jorge@gmail.com", "12345678901", TipoCliente.PESSOAFISICA);
		
		pds1.getTelefones().addAll(Arrays.asList("23456789", "990123456"));
		
		Endereco e3 = new Endereco(null, "Rua Flores", "200", "Apto 303", "Jardim", "38220834", pds1, c1);
		Endereco e4 = new Endereco(null, "Avenida Matos", "100", "Sala 10", "Centro", "38777012", pds1, c2);
		
		pds1.getEnderecosPrestador().addAll(Arrays.asList(e3,e4));
		
		prestadorDeServicoRepository.saveAll(Arrays.asList(pds1));
		enderecoRepository.saveAll(Arrays.asList(e3,e4));
		
	
		
		Pedido ped3 = new Pedido(null, sdf.parse("30/09/2018 11:42"),cli1,e1,pds1,e3);
		Pedido ped4 = new Pedido(null, sdf.parse("10/10/2019 15:45"),cli1,e2,pds1,e4);
		
		Pagamento pagto3 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped3, 6);
		ped3.setPagamento(pagto3);
		
		Pagamento pagto4 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped4, sdf.parse("20/10/2019 07:20"), null);
		ped4.setPagamento(pagto4);
		
		
		
		cli1.getPedidos().addAll(Arrays.asList(ped3,ped4));
		pds1.getPedidos().addAll(Arrays.asList(ped3,ped4));
		
		pedidoRepository.saveAll(Arrays.asList(ped3,ped4));
		pagamentoRepository.saveAll(Arrays.asList(pagto3,pagto4));
		
		PrestadorDeServico pds2 =new PrestadorDeServico(null, "Elias Silva", "elias@gmail.com", "23456789012", TipoCliente.PESSOAJURIDICA);
		
		pds2.getTelefones().addAll(Arrays.asList("23455678", "991234567"));
		
		Endereco e5 = new Endereco(null, "Rua Flores", "200", "Apto 305", "Jardim", "38220834", pds2, c1);
		Endereco e6 = new Endereco(null, "Avenida Matos", "100", "Sala 15", "Centro", "38777012", pds2, c2);
		
		pds2.getEnderecosPrestador().addAll(Arrays.asList(e5,e6));
		
		
		prestadorDeServicoRepository.saveAll(Arrays.asList(pds1, pds2));
		enderecoRepository.saveAll(Arrays.asList(e5,e6));
		
		
		Pedido ped5 = new Pedido(null, sdf.parse("30/09/2021 10:45"),cli1,e1,pds2,e5);
		Pedido ped6 = new Pedido(null, sdf.parse("10/10/2021 17:36"),cli1,e2,pds2,e6);
		
		Pagamento pagto5 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped5, 6);
		ped5.setPagamento(pagto5);
		
		Pagamento pagto6 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped6, sdf.parse("20/10/2020 20:20"), null);
		ped6.setPagamento(pagto6);
		
		
		
		
		cli1.getPedidos().addAll(Arrays.asList(ped5,ped6));
		pds2.getPedidos().addAll(Arrays.asList(ped5,ped6));
		
		pedidoRepository.saveAll(Arrays.asList(ped5,ped6));
		pagamentoRepository.saveAll(Arrays.asList(pagto5,pagto6));
		
		
		pds1.getServicos().addAll(Arrays.asList(s1,s2,s3));
		pds2.getServicos().addAll(Arrays.asList(s2));
		
		s1.getPrestadoresDeServico().addAll(Arrays.asList(pds1));
		s2.getPrestadoresDeServico().addAll(Arrays.asList(pds1, pds2));
		s3.getPrestadoresDeServico().addAll(Arrays.asList(pds1));
		
		
		
		
		prestadorDeServicoRepository.saveAll(Arrays.asList(pds1, pds2));
		servicoRepository.saveAll(Arrays.asList(s1,s2,s3));
		
		
		ItemPedido ip1 = new ItemPedido(ped1,s1,pds1,0.00, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped2,s3,pds2,0.00, 80.00);
		ItemPedido ip3 = new ItemPedido(ped3,s2,pds1,100.00, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		s1.getItens().addAll(Arrays.asList(ip1));
		s2.getItens().addAll(Arrays.asList(ip3));
		s3.getItens().addAll(Arrays.asList(ip2));
		
		pds1.getItens().addAll(Arrays.asList(ip1,ip2));
		pds2.getItens().addAll(Arrays.asList(ip3));
		
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
