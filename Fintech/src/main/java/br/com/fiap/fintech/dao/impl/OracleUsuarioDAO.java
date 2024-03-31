package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleUsuarioDAO implements UsuarioDAO {

	private Connection conexao = null;
	
	@Override
	public void cadastrar(Usuario usuario) throws DBException {

		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_USUARIO (DS_EMAIL, NM_USUARIO, DS_SENHA, DT_NASCIMENTO) VALUES (?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getSenha());
			java.sql.Date data = new java.sql.Date(usuario.getDataNascimento().getTimeInMillis());
			stmt.setDate(4, data);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastrar, por favor verifique se os dados estão corretos.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void editar(Usuario usuario) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_USUARIO SET NM_USUARIO = ?, DS_SENHA = ?, DT_NASCIMENTO = ? WHERE DS_EMAIL = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getSenha());
			java.sql.Date data = new java.sql.Date(usuario.getDataNascimento().getTimeInMillis());
			stmt.setDate(3, data);
			stmt.setString(4, usuario.getEmail());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar o cadastro.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void excluir(String email) throws DBException {
		
		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_USUARIO WHERE DS_EMAIL = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, email);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao excluir o cadastro.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public boolean validar(Usuario usuario) {
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			stmt = conexao.prepareStatement("SELECT * FROM T_USUARIO WHERE DS_EMAIL = ? AND DS_SENHA = ?");
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getSenha());
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public Usuario buscar(String email) {

		PreparedStatement stmt = null;	
		ResultSet rs = null;
		Usuario usuario = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_USUARIO WHERE DS_EMAIL = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				String dsEmail = rs.getString("DS_EMAIL");
				String nome = rs.getString("NM_USUARIO");
				String senha = rs.getString("DS_SENHA");
				java.sql.Date data = rs.getDate("DT_NASCIMENTO");
				Calendar dataNascimento = Calendar.getInstance();
				dataNascimento.setTimeInMillis(data.getTime());
				
				usuario = new Usuario(dsEmail, nome, senha, dataNascimento);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return usuario;
	}
	
}
