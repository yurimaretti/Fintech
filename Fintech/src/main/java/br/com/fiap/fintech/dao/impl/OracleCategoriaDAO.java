package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.fintech.bean.Categoria;
import br.com.fiap.fintech.dao.CategoriaDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleCategoriaDAO implements CategoriaDAO {

	private Connection conexao = null;
	
	@Override
	public void cadastrar(Categoria categoria) throws DBException {

		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_CATEGORIA_TRANSACAO (CD_CATEGORIA, NM_CATEGORIA) VALUES (SQ_CATEG_TRANSACAO.NEXTVAL, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, categoria.getNome());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastrar categoria.");
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
	public void editar(Categoria categoria) throws DBException {

		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_CATEGORIA_TRANSACAO SET NM_CATEGORIA = ? WHERE CD_CATEGORIA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, categoria.getNome());
			stmt.setInt(2, categoria.getCodigo());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao atualizar categoria");
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
	public Categoria buscar(int codigo) {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Categoria categoria = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_CATEGORIA_TRANSACAO WHERE CD_CATEGORIA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, codigo);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				int cd = rs.getInt("CD_CATEGORIA");
				String nome = rs.getString("NM_CATEGORIA");
				categoria = new Categoria(cd, nome);
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
		return categoria;
	}

	@Override
	public List<Categoria> listar() {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Categoria> lista = new ArrayList<>();
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_CATEGORIA_TRANSACAO ORDER BY NM_CATEGORIA";
			stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				int cd = rs.getInt("CD_CATEGORIA");
				String nome = rs.getString("NM_CATEGORIA");
				Categoria categoria = new Categoria(cd, nome);
				lista.add(categoria);
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

	@Override
	public void excluir(int codigo) throws DBException {

		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "DELETE FROM T_CATEGORIA_TRANSACAO WHERE CD_CATEGORIA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, codigo);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao excluir categoria.");
		} finally {
			try {
				stmt.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
