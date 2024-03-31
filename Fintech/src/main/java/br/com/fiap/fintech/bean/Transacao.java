package br.com.fiap.fintech.bean;

import java.io.Serializable;
import java.util.Calendar;

public class Transacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int codigo;
	private double valor;
	private Calendar data;
	private String observacao;
	private Categoria categoria;
	private Tipo tipo;
	private Conta conta;
	
	public Transacao() {
		super();
	}

	public Transacao(int codigo, double valor, Calendar data, String observacao, Categoria categoria, Tipo tipo,
			Conta conta) {
		super();
		this.codigo = codigo;
		this.valor = valor;
		this.data = data;
		this.observacao = observacao;
		this.categoria = categoria;
		this.tipo = tipo;
		this.conta = conta;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
}
