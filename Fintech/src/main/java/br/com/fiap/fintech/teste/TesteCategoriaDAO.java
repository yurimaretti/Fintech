package br.com.fiap.fintech.teste;

import java.util.List;

import br.com.fiap.fintech.bean.Categoria;
import br.com.fiap.fintech.dao.CategoriaDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;

public class TesteCategoriaDAO {

	public static void main(String[] args) {

		CategoriaDAO dao = DAOFactory.getCategoriaDAO();
		
		Categoria categoria = new Categoria(0, "PIX");
		
		//Cadastrar
		
		try {
			dao.cadastrar(categoria);
			System.out.println("Categoria cadastrada.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		//Buscar e editar
		
		categoria = dao.buscar(46);
		categoria.setNome("TEV");
		
		try {
			dao.editar(categoria);
			System.out.println("Categoria editada.");
		} catch (DBException e) {
			e.printStackTrace();
		}
				
		//Excluir
		
		try {
			dao.excluir(46);
			System.out.println("Categoria excluída.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		//Listar
		
		List<Categoria> lista = dao.listar();
		for (Categoria cat : lista) {
			System.out.println("Código: " + cat.getCodigo() + " e Nome: " + cat.getNome());
		}
		
	}

}
