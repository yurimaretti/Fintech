package br.com.fiap.fintech.bean;

import java.io.Serializable;

public class Conta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int codigo;
	private String nome;
	private double saldo;
	private String descricao;
	private Usuario usuario;
	private Cor cor;
	
	public Conta() {
		super();
	}
	
	public Conta(int codigo, String nome, double saldo, String descricao, Usuario usuario, Cor cor) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.saldo = saldo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.cor = cor;
	}

	public Conta(int codigo, String nome, double saldo, Usuario usuario, Cor cor) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.saldo = saldo;
		this.usuario = usuario;
		this.cor = cor;
	}
	
	public Conta(int codigo, String nome, String descricao, Usuario usuario, Cor cor) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.usuario = usuario;
		this.cor = cor;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
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

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}
	
}
