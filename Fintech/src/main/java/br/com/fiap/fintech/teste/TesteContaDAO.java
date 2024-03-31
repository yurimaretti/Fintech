package br.com.fiap.fintech.teste;

import java.util.List;

import br.com.fiap.fintech.bean.Conta;
import br.com.fiap.fintech.bean.Cor;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.ContaDAO;
import br.com.fiap.fintech.dao.CorDAO;
import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;

public class TesteContaDAO {

	public static void main(String[] args) {

		ContaDAO dao = DAOFactory.getContaDAO();
		UsuarioDAO uDao = DAOFactory.getUsuarioDAO();
		CorDAO cDao = DAOFactory.getCorDAO();
		
		Usuario usuario = uDao.buscar("yurimaretti@hotmail.com");
		Cor cor = cDao.buscar("Azul");
		Conta conta = new Conta(0, "Conta Poupança", 18503.25, "Reserva financeira", usuario, cor);		
				
		//Cadastrar
		
		try {
			dao.cadastrar(conta);
			System.out.println("Conta cadastrada.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		//Buscar e Editar
		
		conta = dao.buscar(43);
		conta.setNome("Conta Poupança CAIXA");
		conta.setDescricao("Onde guardo o DimDim");
		Cor cr = cDao.buscar("Roxo");
		conta.setCor(cr);
		
		try {
			dao.editar(conta);
			System.out.println("Conta editada.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		//Listar
		
		List<Conta> lista = dao.listar("yurimaretti@hotmail.com");
		
		for (Conta ct : lista) {
			System.out.println(ct.getCodigo() + " ; " + ct.getNome() + " ; " + ct.getSaldo() + " : " + ct.getDescricao() + " ; " + ct.getUsuario().getEmail() + " ; " + ct.getCor().getDescricao());
		}
		
		//Excluir
		
		try {
			dao.excluir(43);
			System.out.println("Conta excluída.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		
	}

}
