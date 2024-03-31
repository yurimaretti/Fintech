package br.com.fiap.fintech.dao;

import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.exception.DBException;

public interface UsuarioDAO {

	void cadastrar(Usuario usuario) throws DBException;
	void editar(Usuario usuario) throws DBException;
	void excluir(String email) throws DBException;
	Usuario buscar(String email);
	boolean validar(Usuario usuario);
	
}
