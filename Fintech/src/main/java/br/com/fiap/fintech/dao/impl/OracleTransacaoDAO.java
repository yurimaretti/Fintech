package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import br.com.fiap.fintech.bean.Categoria;
import br.com.fiap.fintech.bean.Conta;
import br.com.fiap.fintech.bean.Cor;
import br.com.fiap.fintech.bean.Tipo;
import br.com.fiap.fintech.bean.Transacao;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.TransacaoDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleTransacaoDAO implements TransacaoDAO {

	private Connection conexao = null;
	
	@Override
	public void cadastrarTransacao(Transacao transacao) throws DBException {
		
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		
		try {
			try {
				conexao = ConnectionManager.getInstance().getConnection();
				conexao.setAutoCommit(false);
				
				String sql1 = "INSERT INTO T_TRANSACOES "
						+ "(CD_TRANSACAO, VL_TRANSACAO, DT_TRANSACAO, DS_OBSERVACOES, CD_CATEGORIA, TP_TRANSACAO, CD_CONTA) "
						+ "VALUES (SQ_TRANSACAO.NEXTVAL, ?, ?, ?, ?, ?, ?)";

				stmt1 = conexao.prepareStatement(sql1);
		  		stmt1.setDouble(1, transacao.getValor());
		  		java.sql.Date data = new java.sql.Date(transacao.getData().getTimeInMillis());
		  		stmt1.setDate(2, data);
		  		stmt1.setString(3, transacao.getObservacao());
		  		stmt1.setInt(4, transacao.getCategoria().getCodigo());
		  		stmt1.setString(5, transacao.getTipo().getDescricao());
		  		stmt1.setInt(6, transacao.getConta().getCodigo());
		  		stmt1.executeUpdate();
		  		
		  		String sql2 = "UPDATE T_CONTA SET SD_CONTA = "
		  				+ "(SELECT COALESCE((SELECT SUM(NVL(VL_TRANSACAO, 0)) FROM T_TRANSACOES WHERE CD_CONTA = ? AND TP_TRANSACAO = 'Receita'), 0) "
		  				+ "- COALESCE((SELECT SUM(NVL(VL_TRANSACAO, 0)) FROM T_TRANSACOES WHERE CD_CONTA = ? AND TP_TRANSACAO = 'Despesa'), 0) "
		  				+ "FROM T_CONTA WHERE CD_CONTA = ?) WHERE CD_CONTA = ?";
		  		stmt2 = conexao.prepareStatement(sql2);
		  		stmt2.setInt(1, transacao.getConta().getCodigo());
		  		stmt2.setInt(2, transacao.getConta().getCodigo());
		  		stmt2.setInt(3, transacao.getConta().getCodigo());
		  		stmt2.setInt(4, transacao.getConta().getCodigo());
		  		stmt2.executeUpdate();
		  		
		  		conexao.commit();
			} catch (SQLException e) {
				conexao.rollback();
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Falha ao cadastrar transação.");
		} finally {
			try {
				stmt1.close();
				stmt2.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void editar(Transacao transacao) throws DBException {

		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		
		try {
			try {
				conexao = ConnectionManager.getInstance().getConnection();
				conexao.setAutoCommit(false);

				String sql1 = "UPDATE T_TRANSACOES SET VL_TRANSACAO = ?, DT_TRANSACAO = ?, DS_OBSERVACOES = ?, CD_CATEGORIA = ?, TP_TRANSACAO = ?, CD_CONTA = ? WHERE CD_TRANSACAO = ?";
				stmt1 = conexao.prepareStatement(sql1);
				stmt1.setDouble(1, transacao.getValor());
				java.sql.Date data = new java.sql.Date(transacao.getData().getTimeInMillis());
				stmt1.setDate(2, data);
				stmt1.setString(3, transacao.getObservacao());
				stmt1.setInt(4, transacao.getCategoria().getCodigo());
				stmt1.setString(5, transacao.getTipo().getDescricao());
				stmt1.setInt(6, transacao.getConta().getCodigo());
				stmt1.setInt(7, transacao.getCodigo());
				stmt1.executeUpdate();
				
		  		String sql2 = "UPDATE T_CONTA SET SD_CONTA = "
		  				+ "(SELECT COALESCE((SELECT SUM(NVL(VL_TRANSACAO, 0)) FROM T_TRANSACOES WHERE CD_CONTA = ? AND TP_TRANSACAO = 'Receita'), 0) "
		  				+ "- COALESCE((SELECT SUM(NVL(VL_TRANSACAO, 0)) FROM T_TRANSACOES WHERE CD_CONTA = ? AND TP_TRANSACAO = 'Despesa'), 0) "
		  				+ "FROM T_CONTA WHERE CD_CONTA = ?) WHERE CD_CONTA = ?";
		  		stmt2 = conexao.prepareStatement(sql2);
		  		stmt2.setInt(1, transacao.getConta().getCodigo());
		  		stmt2.setInt(2, transacao.getConta().getCodigo());
		  		stmt2.setInt(3, transacao.getConta().getCodigo());
		  		stmt2.setInt(4, transacao.getConta().getCodigo());
		  		stmt2.executeUpdate();
				
		  		conexao.commit();
			} catch (SQLException e) {
				conexao.rollback();
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Transação editada.");
		} finally {
			try {
				stmt1.close();
				stmt2.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void excluir(int codigo, int cdConta) throws DBException {

		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		
		try {
			
			try {
				conexao = ConnectionManager.getInstance().getConnection();
				conexao.setAutoCommit(false);

				String sql1 = "DELETE FROM T_TRANSACOES WHERE CD_TRANSACAO = ?";
				stmt1 = conexao.prepareStatement(sql1);
				stmt1.setInt(1, codigo);
				stmt1.executeUpdate();
				
		  		String sql2 = "UPDATE T_CONTA SET SD_CONTA = "
		  				+ "(SELECT COALESCE((SELECT SUM(NVL(VL_TRANSACAO, 0)) FROM T_TRANSACOES WHERE CD_CONTA = ? AND TP_TRANSACAO = 'Receita'), 0) "
		  				+ "- COALESCE((SELECT SUM(NVL(VL_TRANSACAO, 0)) FROM T_TRANSACOES WHERE CD_CONTA = ? AND TP_TRANSACAO = 'Despesa'), 0) "
		  				+ "FROM T_CONTA WHERE CD_CONTA = ?) WHERE CD_CONTA = ?";
		  		stmt2 = conexao.prepareStatement(sql2);
		  		stmt2.setInt(1, cdConta);
		  		stmt2.setInt(2, cdConta);
		  		stmt2.setInt(3, cdConta);
		  		stmt2.setInt(4, cdConta);
		  		stmt2.executeUpdate();
				
		  		conexao.commit();
			} catch (SQLException e) {
				conexao.rollback();
				e.printStackTrace();
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao excluir transação.");
		} finally {
			try {
				stmt1.close();
				stmt2.close();
				conexao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public Transacao buscar(int codigo) {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Transacao transacao = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_TRANSACOES "
					+ "INNER JOIN T_CATEGORIA_TRANSACAO ON T_TRANSACOES.CD_CATEGORIA = T_CATEGORIA_TRANSACAO.CD_CATEGORIA "
					+ "INNER JOIN T_TIPO_TRANSACAO ON T_TRANSACOES.TP_TRANSACAO = T_TIPO_TRANSACAO.TP_TRANSACAO "
					+ "INNER JOIN T_CONTA ON T_TRANSACOES.CD_CONTA = T_CONTA.CD_CONTA "
					+ "INNER JOIN T_USUARIO ON T_CONTA.DS_EMAIL = T_USUARIO.DS_EMAIL "
					+ "WHERE T_TRANSACOES.CD_TRANSACAO = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, codigo);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				int codigoTransacao = rs.getInt("CD_TRANSACAO");
				double valor = rs.getDouble("VL_TRANSACAO");
				java.sql.Date data = rs.getDate("DT_TRANSACAO");
				Calendar dataTransacao = Calendar.getInstance();
				dataTransacao.setTimeInMillis(data.getTime());
				String obs = rs.getString("DS_OBSERVACOES");
				
				int categoria = rs.getInt("CD_CATEGORIA");
				String nomeCategoria = rs.getString("NM_CATEGORIA");
				
				String tipo = rs.getString("TP_TRANSACAO");
				
				int cdConta = rs.getInt("CD_CONTA");
				String nomeConta = rs.getString("NM_CONTA");
				double sldConta = rs.getDouble("SD_CONTA");
				String descConta = rs.getString("DS_CONTA");
				
				String descEmail = rs.getString("DS_EMAIL");
				String nomeUsuario = rs.getString("NM_USUARIO");
				java.sql.Date dt = rs.getDate("DT_NASCIMENTO");
				Calendar dtNasc = Calendar.getInstance();
				dtNasc.setTimeInMillis(dt.getTime());
				
				String cor = rs.getString("DS_COR");
				
				transacao = new Transacao(codigoTransacao, valor, dataTransacao, obs, null, null, null);
				Categoria cat = new Categoria(categoria, nomeCategoria);
				Tipo tp = new Tipo(tipo);
				Usuario usu = new Usuario(descEmail, nomeUsuario, dtNasc);
				Cor cr = new Cor(cor);
				Conta ct = new Conta(cdConta, nomeConta, sldConta, descConta, usu, cr);
								
				transacao.setCategoria(cat);
				transacao.setTipo(tp);
				transacao.setConta(ct);
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
		return transacao;
	}

	@Override
	public List<Transacao> listar(int codigo) {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Transacao transacao = null;
		List<Transacao> lista = new ArrayList<Transacao>();
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_TRANSACOES "
					+ "INNER JOIN T_CATEGORIA_TRANSACAO ON T_TRANSACOES.CD_CATEGORIA = T_CATEGORIA_TRANSACAO.CD_CATEGORIA "
					+ "INNER JOIN T_TIPO_TRANSACAO ON T_TRANSACOES.TP_TRANSACAO = T_TIPO_TRANSACAO.TP_TRANSACAO "
					+ "INNER JOIN T_CONTA ON T_TRANSACOES.CD_CONTA = T_CONTA.CD_CONTA "
					+ "INNER JOIN T_USUARIO ON T_CONTA.DS_EMAIL = T_USUARIO.DS_EMAIL "
					+ "WHERE T_TRANSACOES.CD_CONTA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, codigo);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				int codigoTransacao = rs.getInt("CD_TRANSACAO");
				double valor = rs.getDouble("VL_TRANSACAO");
				java.sql.Date data = rs.getDate("DT_TRANSACAO");
				Calendar dataTransacao = Calendar.getInstance();
				dataTransacao.setTimeInMillis(data.getTime());
				String obs = rs.getString("DS_OBSERVACOES");
				
				int categoria = rs.getInt("CD_CATEGORIA");
				String nomeCategoria = rs.getString("NM_CATEGORIA");
				
				String tipo = rs.getString("TP_TRANSACAO");
				
				int cdConta = rs.getInt("CD_CONTA");
				String nomeConta = rs.getString("NM_CONTA");
				double sldConta = rs.getDouble("SD_CONTA");
				String descConta = rs.getString("DS_CONTA");
				
				String descEmail = rs.getString("DS_EMAIL");
				String nomeUsuario = rs.getString("NM_USUARIO");
				java.sql.Date dt = rs.getDate("DT_NASCIMENTO");
				Calendar dtNasc = Calendar.getInstance();
				dtNasc.setTimeInMillis(dt.getTime());
				
				String cor = rs.getString("DS_COR");
				
				transacao = new Transacao(codigoTransacao, valor, dataTransacao, obs, null, null, null);
				Categoria cat = new Categoria(categoria, nomeCategoria);
				Tipo tp = new Tipo(tipo);
				Usuario usu = new Usuario(descEmail, nomeUsuario, dtNasc);
				Cor cr = new Cor(cor);
				Conta ct = new Conta(cdConta, nomeConta, sldConta, descConta, usu, cr);
								
				transacao.setCategoria(cat);
				transacao.setTipo(tp);
				transacao.setConta(ct);
				
				lista.add(transacao);
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

//	@Override
//	public void cadastrarReceita(Transacao transacao) throws DBException {
//
//		PreparedStatement stmt1 = null;
//		PreparedStatement stmt2 = null;
//		
//		try {
//			try {
//				conexao = ConnectionManager.getInstance().getConnection();
//				conexao.setAutoCommit(false);
//				
//				String sql1 = "INSERT INTO T_TRANSACOES "
//						+ "(CD_TRANSACAO, VL_TRANSACAO, DT_TRANSACAO, DS_OBSERVACOES, CD_CATEGORIA, TP_TRANSACAO, CD_CONTA) "
//						+ "VALUES (SQ_TRANSACAO.NEXTVAL, ?, ?, ?, ?, ?, ?)";
//				stmt1 = conexao.prepareStatement(sql1);
//		  		stmt1.setDouble(1, transacao.getValor());
//		  		java.sql.Date data = new java.sql.Date(transacao.getData().getTimeInMillis());
//		  		stmt1.setDate(2, data);
//		  		stmt1.setString(3, transacao.getObservacao());
//		  		stmt1.setInt(4, transacao.getCategoria().getCodigo());
//		  		stmt1.setString(5, transacao.getTipo().getDescricao());
//		  		stmt1.setInt(6, transacao.getConta().getCodigo());
//		  		stmt1.executeUpdate();
//		  		
//		  		String sql2 = "UPDATE T_CONTA SET SD_CONTA = SD_CONTA + ? WHERE CD_CONTA = ?";
//		  		stmt2 = conexao.prepareStatement(sql2);
//		  		stmt2.setDouble(1, transacao.getValor());
//		  		stmt2.setInt(2, transacao.getConta().getCodigo());
//		  		stmt2.executeUpdate();
//		  		
//		  		conexao.commit();
//			} catch (SQLException e) {
//				conexao.rollback();
//				e.printStackTrace();
//			}
//	  					
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new DBException("Falha ao cadastrar receita.");
//		} finally {
//			try {
//				stmt1.close();
//				stmt2.close();
//				conexao.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//	}
//
//	@Override
//	public void cadastrarDespesa(Transacao transacao) throws DBException {
//
//		PreparedStatement stmt1 = null;
//		PreparedStatement stmt2 = null;
//		
//		try {
//			try {
//				conexao = ConnectionManager.getInstance().getConnection();
//				conexao.setAutoCommit(false);
//				
//				String sql1 = "INSERT INTO T_TRANSACOES "
//						+ "(CD_TRANSACAO, VL_TRANSACAO, DT_TRANSACAO, DS_OBSERVACOES, CD_CATEGORIA, TP_TRANSACAO, CD_CONTA) "
//						+ "VALUES (SQ_TRANSACAO.NEXTVAL, ?, ?, ?, ?, ?, ?)";
//				stmt1 = conexao.prepareStatement(sql1);
//		  		stmt1.setDouble(1, transacao.getValor());
//		  		java.sql.Date data = new java.sql.Date(transacao.getData().getTimeInMillis());
//		  		stmt1.setDate(2, data);
//		  		stmt1.setString(3, transacao.getObservacao());
//		  		stmt1.setInt(4, transacao.getCategoria().getCodigo());
//		  		stmt1.setString(5, transacao.getTipo().getDescricao());
//		  		stmt1.setInt(6, transacao.getConta().getCodigo());
//		  		stmt1.executeUpdate();
//		  		
//		  		String sql2 = "UPDATE T_CONTA SET SD_CONTA = SD_CONTA - ? WHERE CD_CONTA = ?";
//		  		stmt2 = conexao.prepareStatement(sql2);
//		  		stmt2.setDouble(1, transacao.getValor());
//		  		stmt2.setInt(2, transacao.getConta().getCodigo());
//		  		stmt2.executeUpdate();
//		  		
//		  		conexao.commit();
//			} catch (SQLException e) {
//				conexao.rollback();
//				e.printStackTrace();
//			}
//	  					
//		} catch (SQLException e) {
//			e.printStackTrace();
//			throw new DBException("Falha ao cadastrar despesa.");
//		} finally {
//			try {
//				stmt1.close();
//				stmt2.close();
//				conexao.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		
//	}

}