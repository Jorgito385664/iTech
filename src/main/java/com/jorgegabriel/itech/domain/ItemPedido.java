package com.jorgegabriel.itech.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class ItemPedido implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// atributos
	
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
	
	private Double desconto;
	private Double preco;
	
	
	// construtores
	
	public ItemPedido() {
		
	}


	public ItemPedido(Pedido pedido, Servico servico, Double desconto, Double preco) {
		super();
		id.setPedido(pedido);
		id.setServico(servico);
		this.desconto = desconto;
		this.preco = preco;
	}
	
	// getters e setters


	public Pedido getPedido() {
		return id.getPedido();
	}
	
	public Servico getServico() {
		return id.getServico();
	}
	
	
	public ItemPedidoPK getId() {
		return id;
	}


	public void setId(ItemPedidoPK id) {
		this.id = id;
	}


	public Double getDesconto() {
		return desconto;
	}


	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}


	public Double getPreco() {
		return preco;
	}


	public void setPreco(Double preco) {
		this.preco = preco;
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
		ItemPedido other = (ItemPedido) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	

}
