package br.com.fiap.fintech.dao;

import java.util.List;
import br.com.fiap.fintech.bean.Transacao;
import br.com.fiap.fintech.exception.DBException;

public interface TransacaoDAO {

	void cadastrarTransacao(Transacao transacao) throws DBException;
	void editar(Transacao transacao) throws DBException;
	void excluir(int codigo, int cdConta) throws DBException;
	Transacao buscar(int codigo);
	List<Transacao> listar(int codigo);
	
}
