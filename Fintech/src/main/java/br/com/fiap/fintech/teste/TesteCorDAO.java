package br.com.fiap.fintech.teste;

import java.util.List;
import br.com.fiap.fintech.bean.Cor;
import br.com.fiap.fintech.dao.CorDAO;
import br.com.fiap.fintech.factory.DAOFactory;

public class TesteCorDAO {

	public static void main(String[] args) {

		CorDAO dao = DAOFactory.getCorDAO();

		//Listar
		
		List<Cor> lista = dao.listar();
		for (Cor cor : lista) {
			System.out.println(cor.getDescricao());
		}
		
		//Buscar
		
		Cor cor = dao.buscar("Azul");
		System.out.println(cor.getDescricao());
		
	}

}
