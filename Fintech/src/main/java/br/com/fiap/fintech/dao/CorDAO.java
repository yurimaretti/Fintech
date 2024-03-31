package br.com.fiap.fintech.dao;

import java.util.List;
import br.com.fiap.fintech.bean.Cor;

public interface CorDAO {

	List<Cor> listar();
	Cor buscar(String descricao);
	
}
