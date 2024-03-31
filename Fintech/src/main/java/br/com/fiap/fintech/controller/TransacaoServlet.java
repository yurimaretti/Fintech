package br.com.fiap.fintech.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

/**
 * Servlet implementation class ContaServlet
 */
@WebServlet("/transacao")
public class TransacaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private TransacaoDAO dao;
	private CategoriaDAO catDao;
	private ContaDAO contaDao;
	private TipoDAO tipoDao;
	
	public void init() throws ServletException{
		super.init();
		dao = DAOFactory.getTransacaoDAO();
		catDao = DAOFactory.getCategoriaDAO();
		contaDao = DAOFactory.getContaDAO();
		tipoDao = DAOFactory.getTipoDAO();
	}
	
    public TransacaoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "listar":
			listar(request, response);
			break;
		case "abrir-form-cadastro": 
			abrirFormCadastro(request, response);
			break;
		case "abrir-form-edicao": 
			abrirFormEdicao(request, response);
			break;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "cadastrar":
			cadastrar(request, response);
			break;
		case "editar":
			editar(request, response);
			break;
		case "excluir":
			excluir(request, response);
			break;
		}
		
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		
		List<Transacao> lista = dao.listar(codigo);
		request.setAttribute("transacoes", lista);
		request.getRequestDispatcher("transacoes.jsp").forward(request, response);
	}
	
	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			double valor = Double.parseDouble(request.getParameter("valor"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar data = Calendar.getInstance();
			data.setTime(format.parse(request.getParameter("data")));
			String obs = request.getParameter("observacoes");
			int cat = Integer.parseInt(request.getParameter("categoria"));
			String tp = request.getParameter("tipo");
			int cod = Integer.parseInt(request.getParameter("conta"));

			Categoria categoria = catDao.buscar(cat);
			Tipo tipo = tipoDao.buscar(tp);
			Conta conta = contaDao.buscar(cod);
			
			Transacao transacao = new Transacao(0, valor, data, obs, categoria, tipo, conta);

			dao.cadastrarTransacao(transacao);

			request.setAttribute("msg", "Transação Cadastrada!");
			
		} catch (DBException e) {
			request.setAttribute("erro", "Erro ao cadastrar.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide se os dados estão corretos.");
		}
		abrirFormCadastro(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			double valor = Double.parseDouble(request.getParameter("valor"));
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar data = Calendar.getInstance();
			data.setTime(format.parse(request.getParameter("data")));
			String obs = request.getParameter("observacoes");
			int cat = Integer.parseInt(request.getParameter("categoria"));
			String tp = request.getParameter("tipo");
			int cod = Integer.parseInt(request.getParameter("conta"));

			Categoria categoria = catDao.buscar(cat);
			Tipo tipo = tipoDao.buscar(tp);
			Conta conta = contaDao.buscar(cod);
									
			Transacao transacao = new Transacao(codigo, valor, data, obs, categoria, tipo, conta);
			
			dao.editar(transacao);
			
			request.setAttribute("msg", "Transação Atualizada!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar.");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide se os dados estão corretos.");
			request.getRequestDispatcher("edicao_transacao.jsp").forward(request, response);
		}
		request.getRequestDispatcher("edicao_transacao.jsp").forward(request, response);
	}
		
	private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int cd = Integer.parseInt(request.getParameter("codigo"));
		int cdConta = Integer.parseInt(request.getParameter("codigoConta"));
		
		try {
			dao.excluir(cd, cdConta);
			request.setAttribute("msg", "Transação Removida!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao remover.");
		}
		String email;
		
		HttpSession session = request.getSession();
		email = (String) session.getAttribute("user");
		
		List<Conta> lista = contaDao.listar(email);
		request.setAttribute("contas", lista);
		request.getRequestDispatcher("contas.jsp").forward(request, response);
	}
	
	private void abrirFormCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		carregarCategorias(request);
		carregarContas(request);
		request.getRequestDispatcher("cadastro_transacao.jsp").forward(request, response);
	}
	
	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int cod = Integer.parseInt(request.getParameter("codigo"));
		Transacao transacao = dao.buscar(cod);
		carregarCategorias(request);
		carregarContas(request);
		request.setAttribute("transacao", transacao);
		request.getRequestDispatcher("edicao_transacao.jsp").forward(request, response);
	}

	private void carregarCategorias(HttpServletRequest request) {
		List<Categoria> lista = catDao.listar();
		request.setAttribute("categorias", lista);
	}
	
	private void carregarContas(HttpServletRequest request) {
		String email;
		
		HttpSession session = request.getSession();
		email = (String) session.getAttribute("user");
		
		List<Conta> lista = contaDao.listar(email);
		request.setAttribute("contas", lista);
	}
	
}
