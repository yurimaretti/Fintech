package br.com.fiap.fintech.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UsuarioDAO dao;
	
	public void init() throws ServletException{
		super.init();
		dao = DAOFactory.getUsuarioDAO();
	}
	
    public LoginServlet() {
        super();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "validar":
			validar(request, response);
			break;
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
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");
		
		switch (acao) {
		case "logoff":
			logoff(request, response);
			break;
		case "abrir-form-edicao":
			abrirFormEdicao(request, response);
			break;
		}
		

		
	}

	private void logoff(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	private void validar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		String nome = null;
		
		Usuario usuario = new Usuario(email, senha);
		
		if (dao.validar(usuario)) {
			HttpSession session = request.getSession();
			usuario = dao.buscar(email);
			nome = usuario.getNome();
			session.setAttribute("user", email);
			session.setAttribute("nome", nome);
		} else {
			request.setAttribute("erro", "Usuário e/ou senha inválidos");
		}
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	
	private void cadastrar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String senha = request.getParameter("senha2");			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar data = Calendar.getInstance();
			data.setTime(format.parse(request.getParameter("data")));
			
			Usuario usuario = new Usuario(email, nome, senha, data);
			
			dao.cadastrar(usuario);
			
			request.setAttribute("msg", "Usuário Cadastrado!");
			
		} catch (ParseException e) {
			request.setAttribute("erro", "Erro ao cadastrar.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide se os dados estão corretos.");
		}
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			String nome = request.getParameter("nome");
			String email = request.getParameter("usuario");
			String senha = request.getParameter("senha2");		
			
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar data = Calendar.getInstance();
			data.setTime(format.parse(request.getParameter("data")));
			
			Usuario usuario = new Usuario(email, nome, senha, data);
			
			HttpSession session = request.getSession();
			session.setAttribute("nome", nome);
			
			dao.editar(usuario);
						
			request.setAttribute("msg", "Dados Atualizados!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar.");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide se os dados estão corretos.");
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
	
	private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email;
		HttpSession session = request.getSession();
		email = (String) session.getAttribute("user");
		
		try {
			dao.excluir(email);
			logoff(request, response);
			request.setAttribute("msg", "Usuário excluído!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao remover.");
		} 
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	
	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String user = request.getParameter("usuario"); 
		Usuario usuario = dao.buscar(user);
		request.setAttribute("usuario", usuario);
		request.getRequestDispatcher("edicao_usuario.jsp").forward(request, response);
	}
	
}
