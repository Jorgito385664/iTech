package com.jorgegabriel.itech.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Pedido implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	// atributos
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date instante;
	
	// associações
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy="pedido")
	private Pagamento pagamento;
	
	
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="endereco_de_entrega_id")
	private Endereco enderecoDeEntrega;
	
	@ManyToOne
	@JoinColumn(name="prestador_de_servico_id")
	private PrestadorDeServico prestadorDeServico;
	
	@ManyToOne
	@JoinColumn(name="endereco_de_prestador_id")
	private Endereco enderecoDePrestador;
	
	@OneToMany(mappedBy="id.pedido")
	private Set<ItemPedido> itens = new HashSet<>();
	
	// construtores
	
	public Pedido() {
		
	}
	
	
	public Pedido(Integer id, Date instante, Cliente cliente, Endereco enderecoDeEntrega) {
		super();
		this.id = id;
		this.instante = instante;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
		
	}

	public Pedido(Integer id, Date instante, Cliente cliente, Endereco enderecoDeEntrega, PrestadorDeServico prestadorDeServico, Endereco enderecoDePrestador) {
		super();
		this.id = id;
		this.instante = instante;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
		this.prestadorDeServico = prestadorDeServico;
		this.enderecoDePrestador = enderecoDePrestador;
	}
	
	
	
	
	public Pedido(Integer id, Date instante, Cliente cliente, Endereco enderecoDeEntrega,
			PrestadorDeServico prestadorDeServico, Endereco enderecoDePrestador, Set<ItemPedido> itens) {
		super();
		this.id = id;
		this.instante = instante;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
		this.prestadorDeServico = prestadorDeServico;
		this.enderecoDePrestador = enderecoDePrestador;
		this.itens = itens;
	}

	// getters e setters
	
	public double getValorTotal() {
		double soma = 0;
		for(ItemPedido ip : itens) {
			soma = soma + ip.getSubTotal();
		}
		
		return soma;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}
	
	
	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}
	
	public PrestadorDeServico getPrestadorDeServico() {
		return prestadorDeServico;
	}

	public void setPrestadorDeServico(PrestadorDeServico prestadorDeServico) {
		this.prestadorDeServico = prestadorDeServico;
	}

	public Endereco getEnderecoDePrestador() {
		return enderecoDePrestador;
	}

	public void setEnderecoDePrestador(Endereco enderecoDePrestador) {
		this.enderecoDePrestador = enderecoDePrestador;
	}
	
	// hashcode e equals

	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}


	
	
}
