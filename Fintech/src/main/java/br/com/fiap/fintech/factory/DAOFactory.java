package br.com.fiap.fintech.factory;

import br.com.fiap.fintech.dao.CategoriaDAO;
import br.com.fiap.fintech.dao.ContaDAO;
import br.com.fiap.fintech.dao.CorDAO;
import br.com.fiap.fintech.dao.ObjetivoDAO;
import br.com.fiap.fintech.dao.TipoDAO;
import br.com.fiap.fintech.dao.TransacaoDAO;
import br.com.fiap.fintech.dao.UsuarioDAO;
import br.com.fiap.fintech.dao.impl.OracleCategoriaDAO;
import br.com.fiap.fintech.dao.impl.OracleContaDAO;
import br.com.fiap.fintech.dao.impl.OracleCorDAO;
import br.com.fiap.fintech.dao.impl.OracleObjetivoDAO;
import br.com.fiap.fintech.dao.impl.OracleTipoDAO;
import br.com.fiap.fintech.dao.impl.OracleTransacaoDAO;
import br.com.fiap.fintech.dao.impl.OracleUsuarioDAO;

public class DAOFactory {

	public static UsuarioDAO getUsuarioDAO() {
		return new OracleUsuarioDAO();
	}

	public static CategoriaDAO getCategoriaDAO() {
		return new OracleCategoriaDAO();
	}
	
	public static ObjetivoDAO getObjetivoDAO() {
		return new OracleObjetivoDAO();
	}
	
	public static TipoDAO getTipoDAO() {
		return new OracleTipoDAO();
	}
	
	public static CorDAO getCorDAO() {
		return new OracleCorDAO();
	}
	
	public static ContaDAO getContaDAO() {
		return new OracleContaDAO();
	}
	
	public static TransacaoDAO getTransacaoDAO() {
		return new OracleTransacaoDAO();
	}
	
}
