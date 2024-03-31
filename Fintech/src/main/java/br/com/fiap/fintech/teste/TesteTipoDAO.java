package br.com.fiap.fintech.teste;

import java.util.List;

import br.com.fiap.fintech.bean.Tipo;
import br.com.fiap.fintech.dao.TipoDAO;
import br.com.fiap.fintech.factory.DAOFactory;

public class TesteTipoDAO {

	public static void main(String[] args) {

		TipoDAO dao = DAOFactory.getTipoDAO();

		//Listar
		
		List<Tipo> lista = dao.listar();
		
		for (Tipo tipo : lista) {
			System.out.println(tipo.getDescricao());
		}
		
		//Buscar
		
		Tipo tipo = dao.buscar("Receita");
		System.out.println(tipo.getDescricao());
		
	}

}
