package com.jorgegabriel.itech.domain;

import java.io.Serializable;
import java.util.Objects;

public class Categoria implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// atributos
	private Integer id;
	private String nome;
	
	
	
	// construtores
	public Categoria() {
		
	}

	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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
	
	
	// Hashcode e equals
	
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
		Categoria other = (Categoria) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
	
	
}
