package br.com.fiap.fintech.bean;

import java.io.Serializable;
import java.util.Calendar;

import br.com.fiap.fintech.util.CriptografiaUtils;

public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String nome;
	private String senha;
	private Calendar dataNascimento;
	
	public Usuario() {
		super();
	}

	public Usuario(String email, String nome, String senha, Calendar dataNascimento) {
		super();
		this.email = email;
		this.nome = nome;
		setSenha(senha);
		this.dataNascimento = dataNascimento;
	}
	
	public Usuario(String email, String nome, Calendar dataNascimento) {
		super();
		this.email = email;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}

	public Usuario(String email, String nome, String senha) {
		super();
		this.email = email;
		this.nome = nome;
		setSenha(senha);
	}
	
	public Usuario(String email, String senha) {
		super();
		this.email = email;
		setSenha(senha);
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		try {
			this.senha = CriptografiaUtils.criptografar(senha);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
}
