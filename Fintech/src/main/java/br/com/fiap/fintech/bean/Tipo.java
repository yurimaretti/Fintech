package br.com.fiap.fintech.bean;

import java.io.Serializable;

public class Tipo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String descricao;

	public Tipo() {
		super();
	}

	public Tipo(String descricao) {
		super();
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
