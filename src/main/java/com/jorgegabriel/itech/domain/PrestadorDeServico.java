package com.jorgegabriel.itech.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jorgegabriel.itech.domain.enums.TipoCliente;

@Entity
public class PrestadorDeServico implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//atributos
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String email;
	private String cpfOuCnpj;
	private Integer tipo;
	
	// associações
	
	@OneToMany(mappedBy="prestadorDeServico")
	private List<Endereco> enderecosPrestador = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable(name="TELEFONEPRESTADOR")
	private Set<String> telefones = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="prestadorDeServico")
	private List<Pedido> pedidos = new ArrayList<>();
	
	@ManyToMany(mappedBy = "prestadoresDeServico")
	private List<Servico> servicos = new ArrayList<>();
	
	@OneToMany(mappedBy="id.prestadorDeServico")
	private Set<ItemPedido> itens = new HashSet<>();
	
	//construtores
	
	public PrestadorDeServico() {
		
	}

	public PrestadorDeServico(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo.getCod();
	}
	
	//getters e setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}

	
	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public List<Servico> getServicos() {
		return servicos;
	}

	public void setServicos(List<Servico> servicos) {
		this.servicos = servicos;
	}
	
	public List<Endereco> getEnderecosPrestador() {
		return enderecosPrestador;
	}

	public void setEnderecosPrestador(List<Endereco> enderecosPrestador) {
		this.enderecosPrestador = enderecosPrestador;
	}
	
	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
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
		PrestadorDeServico other = (PrestadorDeServico) obj;
		return Objects.equals(id, other.id);
	}

	
	
	
	
	
}
