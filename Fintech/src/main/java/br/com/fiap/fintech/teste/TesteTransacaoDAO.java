package br.com.fiap.fintech.teste;

import java.util.Calendar;
import java.util.List;
import br.com.fiap.fintech.bean.Categoria;
import br.com.fiap.fintech.bean.Conta;
import br.com.fiap.fintech.bean.Tipo;
import br.com.fiap.fintech.bean.Transacao;
import br.com.fiap.fintech.dao.CategoriaDAO;
import br.com.fiap.fintech.dao.ContaDAO;
import br.com.fiap.fintech.dao.TipoDAO;
import br.com.fiap.fintech.dao.TransacaoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;

public class TesteTransacaoDAO {

	public static void main(String[] args) {

		TransacaoDAO dao = DAOFactory.getTransacaoDAO();
		CategoriaDAO catDao = DAOFactory.getCategoriaDAO();
		TipoDAO tpDao = DAOFactory.getTipoDAO();
		ContaDAO contaDAO = DAOFactory.getContaDAO();
		
		Categoria categoria = catDao.buscar(7);
		Tipo tipo = tpDao.buscar("Despesa");
		Conta conta = contaDAO.buscar(3);
				
		Transacao transacao = new Transacao(0, 203.8, Calendar.getInstance(), "Bora arredondar", categoria, tipo, conta);
		
		//Cadastrar
		
		try {
			dao.cadastrarTransacao(transacao);
			System.out.println("Transação cadastrada.");
		} catch (DBException e) {
			e.printStackTrace();
		}

		//Buscar e Editar
		
		transacao = dao.buscar(31);
		transacao.setObservacao("Macarrão");
		
		try {
			dao.editar(transacao);
			System.out.println("Transação editada.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		//Listar
		
		List<Transacao> lista = dao.listar(3);
		
		for (Transacao trs : lista) {
			System.out.println(trs.getCodigo() + " ; " + trs.getValor() + " ; " + trs.getObservacao() + " ; " + trs.getCategoria().getNome() + " ; " + trs.getTipo().getDescricao()+ " ; " + trs.getConta().getNome());
		}
		
		//Excluir
		
		try {
			dao.excluir(31, 3);
			System.out.println("Transação excluída.");
		} catch (DBException e) {
			e.printStackTrace();
		}
		
	}

}
