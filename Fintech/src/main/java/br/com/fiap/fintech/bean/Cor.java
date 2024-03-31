package br.com.fiap.fintech.bean;

import java.io.Serializable;

public class Cor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	private String css;

	public Cor() {
		super();
	}

	public Cor(String descricao) {
		super();
		this.descricao = descricao;
	}
	
	public Cor(String descricao, String css) {
		super();
		this.descricao = descricao;
		this.css = css;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}
	
}
