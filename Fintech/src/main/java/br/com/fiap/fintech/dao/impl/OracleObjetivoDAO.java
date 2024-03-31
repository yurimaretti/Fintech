package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import br.com.fiap.fintech.bean.Objetivo;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.ObjetivoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleObjetivoDAO implements ObjetivoDAO {

	private Connection conexao = null;
	
	@Override
	public void cadastrar(Objetivo objetivo) throws DBException {

		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_OBJETIVOS (CD_OBJETIVO, DS_OBJETIVO, DS_EMAIL) VALUES (SQ_OBJETIVO.NEXTVAL, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, objetivo.getDescricao());
			stmt.setString(2, objetivo.getUsuario().getEmail());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastrar objetivo.");
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
	public void editar(Objetivo objetivo) throws DBException {

		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_OBJETIVOS SET DS_OBJETIVO = ? WHERE CD_OBJETIVO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, objetivo.getDescricao());
			stmt.setInt(2, objetivo.getCodigo());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar objetivo.");
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
	public void excluir(int codigo) throws DBException {

		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_OBJETIVOS WHERE CD_OBJETIVO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, codigo);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao excluir objetivo.");
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
	public Objetivo buscar(int codigo) {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Objetivo objetivo = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_OBJETIVOS INNER JOIN T_USUARIO ON T_OBJETIVOS.DS_EMAIL = T_USUARIO.DS_EMAIL WHERE T_OBJETIVOS.CD_OBJETIVO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, codigo);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				int cd = rs.getInt("CD_OBJETIVO");
				String descricao = rs.getString("DS_OBJETIVO");
				String emailUsuario = rs.getString("DS_EMAIL");
				String nome = rs.getString("NM_USUARIO");
				java.sql.Date data = rs.getDate("DT_NASCIMENTO");
				Calendar dataNascimento = Calendar.getInstance();
				dataNascimento.setTimeInMillis(data.getTime());
				
				objetivo = new Objetivo(cd, descricao, null);
				Usuario usuario = new Usuario(emailUsuario, nome, dataNascimento);
				objetivo.setUsuario(usuario);
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
		return objetivo;
	}

	@Override
	public List<Objetivo> listar(String email) {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Objetivo> lista = new ArrayList<Objetivo>();
		Objetivo objetivo = null;

		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_OBJETIVOS INNER JOIN T_USUARIO ON T_OBJETIVOS.DS_EMAIL = T_USUARIO.DS_EMAIL "
					+ "WHERE T_OBJETIVOS.DS_EMAIL = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				int cd = rs.getInt("CD_OBJETIVO");
				String descricao = rs.getString("DS_OBJETIVO");
				String emailUsuario = rs.getString("DS_EMAIL");
				String nome = rs.getString("NM_USUARIO");
				java.sql.Date data = rs.getDate("DT_NASCIMENTO");
				Calendar dataNascimento = Calendar.getInstance();
				dataNascimento.setTimeInMillis(data.getTime());
				
				objetivo = new Objetivo(cd, descricao, null);
				Usuario usuario = new Usuario(emailUsuario, nome, dataNascimento);
				objetivo.setUsuario(usuario);
				lista.add(objetivo);
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
		return lista;
	}

}
