package br.com.fiap.fintech.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import br.com.fiap.fintech.bean.Conta;
import br.com.fiap.fintech.bean.Cor;
import br.com.fiap.fintech.bean.Usuario;
import br.com.fiap.fintech.dao.ContaDAO;
import br.com.fiap.fintech.exception.DBException;
import br.com.fiap.fintech.singleton.ConnectionManager;

public class OracleContaDAO implements ContaDAO {

	private Connection conexao = null;
	
	@Override
	public void cadastrar(Conta conta) throws DBException {

		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "INSERT INTO T_CONTA (CD_CONTA, NM_CONTA, SD_CONTA, DS_CONTA, DS_EMAIL, DS_COR) VALUES (SQ_CONTA.NEXTVAL, ?, ?, ?, ?, ?)";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, conta.getNome());
			stmt.setDouble(2, conta.getSaldo());
			stmt.setString(3, conta.getDescricao());
			stmt.setString(4, conta.getUsuario().getEmail());
			stmt.setString(5, conta.getCor().getDescricao());
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao cadastrar conta.");
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
	public void editar(Conta conta) throws DBException {

		PreparedStatement stmt = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "UPDATE T_CONTA SET NM_CONTA = ?, DS_CONTA = ?, DS_COR = ? WHERE CD_CONTA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, conta.getNome());
			stmt.setString(2, conta.getDescricao());
			stmt.setString(3, conta.getCor().getDescricao());
			stmt.setInt(4, conta.getCodigo());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao editar conta.");
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
			String sql = "DELETE FROM T_CONTA WHERE CD_CONTA = ?";
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, codigo);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Erro ao excluir conta.");
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
	public Conta buscar(int codigo) {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Conta conta = null;
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_CONTA "
					+ "INNER JOIN T_USUARIO ON T_CONTA.DS_EMAIL = T_USUARIO.DS_EMAIL "
					+ "INNER JOIN T_COR ON T_CONTA.DS_COR = T_COR.DS_COR "
					+ "WHERE T_CONTA.CD_CONTA = ?";
						
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, codigo);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				int cdConta = rs.getInt("CD_CONTA");
				String nomeConta = rs.getString("NM_CONTA");
				double saldo = rs.getDouble("SD_CONTA");
				String descConta = rs.getString("DS_CONTA");
				String email = rs.getString("DS_EMAIL");
				String nomeUsuario = rs.getString("NM_USUARIO");
				java.sql.Date data = rs.getDate("DT_NASCIMENTO");
				Calendar dataNascimento = Calendar.getInstance();
				dataNascimento.setTimeInMillis(data.getTime());
				String descCor = rs.getString("DS_COR");
				
				conta = new Conta(cdConta, nomeConta, saldo, descConta, null, null);	
				Usuario usuario = new Usuario(email, nomeUsuario, dataNascimento);
				Cor cor = new Cor(descCor);
				conta.setUsuario(usuario);
				conta.setCor(cor);
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
		return conta;
	}

	@Override
	public List<Conta> listar(String email) {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Conta> lista = new ArrayList<Conta>();
		Conta conta = new Conta();
		
		try {
			conexao = ConnectionManager.getInstance().getConnection();
			String sql = "SELECT * FROM T_CONTA "
					+ "INNER JOIN T_USUARIO ON T_CONTA.DS_EMAIL = T_USUARIO.DS_EMAIL "
					+ "INNER JOIN T_COR ON T_CONTA.DS_COR = T_COR.DS_COR "
					+ "WHERE T_CONTA.DS_EMAIL = ?";
			
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, email);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				int cdConta = rs.getInt("CD_CONTA");
				String nomeConta = rs.getString("NM_CONTA");
				double saldo = rs.getDouble("SD_CONTA");
				String descConta = rs.getString("DS_CONTA");
				String mail = rs.getString("DS_EMAIL");
				String nomeUsuario = rs.getString("NM_USUARIO");
				java.sql.Date data = rs.getDate("DT_NASCIMENTO");
				Calendar dataNascimento = Calendar.getInstance();
				dataNascimento.setTimeInMillis(data.getTime());
				String descCor = rs.getString("DS_COR");
				
				conta = new Conta(cdConta, nomeConta, saldo, descConta, null, null);	
				Usuario usuario = new Usuario(mail, nomeUsuario, dataNascimento);
				Cor cor = new Cor(descCor);
				conta.setUsuario(usuario);
				conta.setCor(cor);
				
				lista.add(conta);
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
