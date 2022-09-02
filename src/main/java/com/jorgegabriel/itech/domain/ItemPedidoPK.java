package com.jorgegabriel.itech.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ItemPedidoPK implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="pedido_id")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name="servico_id")
	private Servico servico;
	
	@ManyToOne
	@JoinColumn(name="prestador_de_servico_id")
	private PrestadorDeServico prestadorDeServico;
	
	
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Servico getServico() {
		return servico;
	}
	public void setServico(Servico servico) {
		this.servico = servico;
	}
	
	public PrestadorDeServico getPrestadorDeServico() {
		return prestadorDeServico;
	}
	public void setPrestadorDeServico(PrestadorDeServico prestadorDeServico) {
		this.prestadorDeServico = prestadorDeServico;
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(pedido, servico);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedidoPK other = (ItemPedidoPK) obj;
		return Objects.equals(pedido, other.pedido) && Objects.equals(servico, other.servico);
	}
	
	
	
	

}
