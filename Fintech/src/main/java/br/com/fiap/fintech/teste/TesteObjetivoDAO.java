package br.com.fiap.fintech.teste;

import java.util.List;
import br.com.fiap.fintech.bean.Objetivo;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.ObjetivoDAO;
import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;

public class TesteObjetivoDAO {

	public static void main(String[] args) {

		ObjetivoDAO dao = DAOFactory.getObjetivoDAO();
		UsuarioDAO uDao = DAOFactory.getUsuarioDAO();
		
		Usuario usuario  = uDao.buscar("yurimaretti@hotmail.com");
		Objetivo objetivo = new Objetivo(0, "Gastar menos", usuario);
		
		//Cadastrar
		
		try {
			dao.cadastrar(objetivo);
			System.out.println("Objetivo cadastrado.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		//Buscar e editar
		
		objetivo = dao.buscar(22);
		objetivo.setDescricao("Comprar meu apartamento.");
		
		try {
			dao.editar(objetivo);
			System.out.println("Objetivo atualizado");
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		//Listar
		
		List<Objetivo> lista = dao.listar("yurimaretti@hotmail.com");
		
		for (Objetivo obj : lista) {
			
			System.out.println("Código: " + obj.getCodigo() + " Descrição: " + obj.getDescricao() + " Usuário: " + obj.getUsuario().getNome());
			
		}
		
		//Excluir
		
		try {
			dao.excluir(22);
			System.out.println("Objetivo excluído.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		
	}

}
