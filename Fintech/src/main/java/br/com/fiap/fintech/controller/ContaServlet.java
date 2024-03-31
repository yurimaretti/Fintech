package br.com.fiap.fintech.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.fiap.fintech.bean.Conta;
import br.com.fiap.fintech.bean.Cor;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.ContaDAO;
import br.com.fiap.fintech.dao.CorDAO;
import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;

/**
 * Servlet implementation class ContaServlet
 */
@WebServlet("/conta")
public class ContaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private ContaDAO dao;
	private CorDAO corDao;
	private UsuarioDAO uDao;
	
	public void init() throws ServletException{
		super.init();
		dao = DAOFactory.getContaDAO();
		corDao = DAOFactory.getCorDAO();
		uDao = DAOFactory.getUsuarioDAO();
	}
	
    public ContaServlet() {
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

	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String nome = request.getParameter("nome");
			String descricao = request.getParameter("descricao");
			String descCor = request.getParameter("cor");

			String email;
			HttpSession session = request.getSession();
			email = (String) session.getAttribute("user");
			
			Usuario usuario = uDao.buscar(email);
			Cor cor = corDao.buscar(descCor);
			
			Conta conta = new Conta(0, nome, 0.00, descricao, usuario, cor);
			
			dao.cadastrar(conta);
			
			request.setAttribute("msg", "Conta Cadastrada!");
			
		} catch (DBException e) {
			request.setAttribute("erro", "Erro ao cadastrar.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide se os dados estão corretos.");
		}
		abrirFormCadastro(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		carregarCores(request);
		
		String email;
		
		HttpSession session = request.getSession();
		email = (String) session.getAttribute("user");
		
		List<Conta> lista = dao.listar(email);
		request.setAttribute("contas", lista);
		request.getRequestDispatcher("contas.jsp").forward(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			String nome = request.getParameter("nome");
			String descricao = request.getParameter("descricao");
			String cr = request.getParameter("cor");
			
			String email;
			HttpSession session = request.getSession();
			email = (String) session.getAttribute("user");
			
			Usuario usuario = uDao.buscar(email);
			
			Cor cor = corDao.buscar(cr);
			
			Conta conta = new Conta(codigo, nome, descricao, usuario, cor);
			
			dao.editar(conta);
			
			request.setAttribute("msg", "Conta Atualizada!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar.");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide se os dados estão corretos.");
			request.getRequestDispatcher("contas.jsp").forward(request, response);
		}
		listar(request, response);
	}
	
	private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int codigo = Integer.parseInt(request.getParameter("codigo"));
		
		try {
			dao.excluir(codigo);;
			request.setAttribute("msg", "Conta Removida!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao remover.");
		} 
		listar(request, response);
	}
	
	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int cod = Integer.parseInt(request.getParameter("codigo"));
		Conta conta = dao.buscar(cod);
		carregarCores(request);
		request.setAttribute("conta", conta);
		request.getRequestDispatcher("edicao_conta.jsp").forward(request, response);
	}
	
	private void abrirFormCadastro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		carregarCores(request);
		request.getRequestDispatcher("cadastro_conta.jsp").forward(request, response);
	}

	private void carregarCores(HttpServletRequest request) {
		List<Cor> lista = corDao.listar();
		request.setAttribute("cores", lista);
	}
	
}
