package br.com.fiap.fintech.dao;

import java.util.List;
import br.com.fiap.fintech.bean.Categoria;
import br.com.fiap.fintech.exception.DBException;

public interface CategoriaDAO {

	void cadastrar (Categoria categoria) throws DBException;
	void editar (Categoria categoria) throws DBException;
	Categoria buscar (int codigo);
	List<Categoria> listar();
	void excluir (int codigo) throws DBException;
	
}
