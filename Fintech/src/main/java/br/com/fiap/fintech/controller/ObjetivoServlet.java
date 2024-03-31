package br.com.fiap.fintech.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.fiap.fintech.bean.Objetivo;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.ObjetivoDAO;
import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;

/**
 * Servlet implementation class ObjetivoServlet
 */
@WebServlet("/objetivo")
public class ObjetivoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ObjetivoDAO dao;
	private UsuarioDAO uDao;
	
	public void init() throws ServletException{
		super.init();
		dao = DAOFactory.getObjetivoDAO();
		uDao = DAOFactory.getUsuarioDAO();
	}
	
    public ObjetivoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "listar":
			listar(request, response);
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
			String obj = request.getParameter("objetivo");

			String email;
			HttpSession session = request.getSession();
			email = (String) session.getAttribute("user");
			
			Usuario usuario = uDao.buscar(email);
			
			Objetivo objetivo = new Objetivo(0, obj, usuario);
			
			dao.cadastrar(objetivo);
			
			request.setAttribute("msg", "Objetivo Cadastrado!");
			
		} catch (DBException e) {
			request.setAttribute("erro", "Erro ao cadastrar.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide se os dados estão corretos.");
		}
		request.getRequestDispatcher("cadastro_objetivo.jsp").forward(request, response);
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email;
		
		HttpSession session = request.getSession();
		email = (String) session.getAttribute("user");
		
		List<Objetivo> lista = dao.listar(email);
		request.setAttribute("objetivos", lista);
		request.getRequestDispatcher("objetivos.jsp").forward(request, response);

	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			String obj = request.getParameter("objetivo");
			
			String email;
			HttpSession session = request.getSession();
			email = (String) session.getAttribute("user");
			
			Usuario usuario = uDao.buscar(email);
			
			Objetivo objetivo = new Objetivo(codigo, obj, usuario);
			
			dao.editar(objetivo);
			
			request.setAttribute("msg", "Objetivo Atualizado!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar.");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide se os dados estão corretos.");
			request.getRequestDispatcher("edicao_objetivo.jsp").forward(request, response);
		}
		listar(request, response);
	}
	
	private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int codigo = Integer.parseInt(request.getParameter("codigo"));
		
		try {
			dao.excluir(codigo);;
			request.setAttribute("msg", "Objetivo Removido!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao remover.");
		} 
		listar(request, response);
	}
	
	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int cod = Integer.parseInt(request.getParameter("codigo"));
		Objetivo objetivo = dao.buscar(cod);
		request.setAttribute("objetivo", objetivo);
		request.getRequestDispatcher("edicao_objetivo.jsp").forward(request, response);
	}

}
