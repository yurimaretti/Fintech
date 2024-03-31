package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.fintech.bean.Cor;
import br.com.fiap.fintech.dao.CorDAO;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleCorDAO implements CorDAO {

	private Connection conexao = null;
	
	@Override
	public List<Cor> listar() {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Cor> lista = new ArrayList<Cor>();
		Cor cor = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_COR";
			stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				String descricao = rs.getString("DS_COR");
				String css = rs.getString("DS_CSS");
				cor = new Cor(descricao, css);
				lista.add(cor);
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
	public Cor buscar(String descricao) {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Cor cor = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_COR WHERE DS_COR = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, descricao);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				String desc = rs.getString("DS_COR");
				
				cor = new Cor(desc);
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
		return cor;
	}
	
}
