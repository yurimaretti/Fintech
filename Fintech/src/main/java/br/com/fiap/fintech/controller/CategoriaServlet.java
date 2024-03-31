package br.com.fiap.fintech.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.fiap.fintech.bean.Categoria;
import br.com.fiap.fintech.dao.CategoriaDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;

/**
 * Servlet implementation class CategoriaServlet
 */
@WebServlet("/categoria")
public class CategoriaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CategoriaDAO dao;
	
	public void init() throws ServletException{
		super.init();
		dao = DAOFactory.getCategoriaDAO();
	}
	
    public CategoriaServlet() {
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
			String cat = request.getParameter("categoria");
			
			Categoria categoria = new Categoria(0, cat);
			
			dao.cadastrar(categoria);
			
			request.setAttribute("msg", "Categoria Cadastrada!");
			
		} catch (DBException e) {
			request.setAttribute("erro", "Erro ao cadastrar.");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide se os dados estão corretos.");
		}
		request.getRequestDispatcher("cadastro_categoria.jsp").forward(request, response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
						
		List<Categoria> lista = dao.listar();
		request.setAttribute("categorias", lista);
		request.getRequestDispatcher("categorias.jsp").forward(request, response);
		
	}
	
	private void editar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			int codigo = Integer.parseInt(request.getParameter("codigo"));
			String nome = request.getParameter("categoria");
						
			Categoria categoria = new Categoria(codigo, nome);
			
			dao.editar(categoria);
			
			request.setAttribute("msg", "Categoria Atualizada!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao atualizar.");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("erro", "Por favor, valide se os dados estão corretos.");
			request.getRequestDispatcher("edicao_categoria.jsp").forward(request, response);
		}
		listar(request, response);
	}
	
	private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int codigo = Integer.parseInt(request.getParameter("codigo"));
		
		try {
			dao.excluir(codigo);;
			request.setAttribute("msg", "Categoria Removida!");
		} catch (DBException db) {
			db.printStackTrace();
			request.setAttribute("erro", "Erro ao remover.");
		} 
		listar(request, response);
	}
	
	private void abrirFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int cod = Integer.parseInt(request.getParameter("codigo"));
		Categoria categoria = dao.buscar(cod);
		request.setAttribute("categoria", categoria);
		request.getRequestDispatcher("edicao_categoria.jsp").forward(request, response);
	}
	
}
