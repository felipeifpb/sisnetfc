package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao{
	
	private String driver;
	private String url;
	private String user;
	private String senha;

	private static Conexao instance = null;

	private Conexao() {
		driver = "org.postgresql.Driver";
		url = "jdbc:postgresql://localhost:5432/sisnetDB";
		user = "postgres";
		senha = "123456";
	}
	
	public static Conexao getInstance() {
		if(instance == null) {
			return instance = new Conexao();
		}
		return instance;
	}

	public Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName( driver );
		return DriverManager.getConnection( url, user, senha );
	}
	
}
