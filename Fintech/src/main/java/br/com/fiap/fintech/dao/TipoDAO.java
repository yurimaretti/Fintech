package br.com.fiap.fintech.dao;

import java.util.List;
import br.com.fiap.fintech.bean.Tipo;

public interface TipoDAO {

	List<Tipo> listar();
	Tipo buscar(String descricao);
	
}
