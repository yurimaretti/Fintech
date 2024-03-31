package br.com.fiap.fintech.dao;

import java.util.List;
import br.com.fiap.fintech.bean.Conta;
import br.com.fiap.fintech.exception.DBException;

public interface ContaDAO {

	void cadastrar(Conta conta) throws DBException;
	void editar(Conta conta) throws DBException;
	void excluir(int codigo) throws DBException;
	Conta buscar(int codigo);
	List<Conta> listar(String email);
	
}
