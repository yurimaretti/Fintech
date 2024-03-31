package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.fiap.fintech.bean.Tipo;
import br.com.fiap.fintech.dao.TipoDAO;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleTipoDAO implements TipoDAO {

	private Connection conexao = null;
	
	@Override
	public List<Tipo> listar() {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Tipo tipo = null;
		List<Tipo> lista  = new ArrayList<Tipo>();
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_TIPO_TRANSACAO";
			stmt = conexao.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				String descricao = rs.getString("TP_TRANSACAO");
				tipo = new Tipo(descricao);
				lista.add(tipo);
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
	public Tipo buscar(String descricao) {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Tipo tipo = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_TIPO_TRANSACAO WHERE TP_TRANSACAO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, descricao);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				String tp = rs.getString("TP_TRANSACAO");
				
				tipo = new Tipo(tp);
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
		return tipo;
	}

}
