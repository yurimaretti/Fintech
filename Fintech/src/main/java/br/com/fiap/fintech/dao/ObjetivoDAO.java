package br.com.fiap.fintech.dao;

import java.util.List;
import br.com.fiap.fintech.bean.Objetivo;
import br.com.fiap.fintech.exception.DBException;

public interface ObjetivoDAO {

	void cadastrar (Objetivo objetivo) throws DBException;
	void editar (Objetivo objetivo) throws DBException;
	void excluir (int codigo) throws DBException;
	Objetivo buscar (int codigo);
	List<Objetivo> listar(String email);
	
}
