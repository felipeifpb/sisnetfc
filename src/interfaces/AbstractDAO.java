package interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexao.Conexao;

public abstract class AbstractDAO {

	public Connection connection;
	public PreparedStatement prepararSql;
	
	public void abrirConexao() throws ClassNotFoundException, SQLException {
		this.connection = Conexao.getInstance().getConnection();
	}

	public void fecharConexao() throws SQLException {
		if (this.connection != null && this.prepararSql != null) {
			this.connection.close();
			this.prepararSql.close();
		}
	}
	
	public void initTransaction() throws SQLException {
		connection.setAutoCommit(false);
	}
	
	public void commitTransaction() throws SQLException {
		connection.setAutoCommit(true);
	}

}
