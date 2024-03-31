package br.com.fiap.fintech.teste;

import java.util.Calendar;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.factory.DAOFactory;

public class TesteUsuarioDAO {

	public static void main(String[] args) {

		UsuarioDAO dao = DAOFactory.getUsuarioDAO();
			
		Usuario usuario = new Usuario("bubu@hotmail.com", "Yuri Maretti Cornacioni", "1234567", Calendar.getInstance());
		
		//Cadastrar
		
		try {
			dao.cadastrar(usuario);
			System.out.println("Usuário cadastrado.");
		} catch (DBException e) {
			e.printStackTrace();
		}

		//Buscar e editar
			
		  usuario = dao.buscar("bubu@hotmail.com"); 
		  usuario.setNome("Lulao");
		  usuario.setSenha("lolalola"); 
		  try { 
			  dao.editar(usuario);
			  System.out.println("Usuário editado."); 
		  } catch (DBException e) {
			  e.printStackTrace(); 
		  }

		//Validar		
		
		  if (dao.validar(usuario)) { 
			  System.out.println("Usuário validado."); 
		  }
		 
		//Excluir		
		
		  try { 
			  dao.excluir("bubu@hotmail.com");
			  System.out.println("Usuário excluído."); 
		  } catch (DBException e) {
			  e.printStackTrace(); 
		  }
		 
	}

}
