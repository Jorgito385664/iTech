package com.jorgegabriel.itech.domain.enums;

public enum TipoPrestador {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2 , "Pessoa Jurídica");
	
	private int cod;
	private String descricao;
	
	private TipoPrestador(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoPrestador toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for(TipoPrestador x : TipoPrestador.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
