package br.com.fiap.fintech.bean;

import java.io.Serializable;

public class Objetivo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int codigo;
	private String descricao;
	private Usuario usuario;
	
	public Objetivo() {
		super();
	}
	
	public Objetivo(int codigo, String descricao, Usuario usuario) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.usuario = usuario;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}	
	
}
