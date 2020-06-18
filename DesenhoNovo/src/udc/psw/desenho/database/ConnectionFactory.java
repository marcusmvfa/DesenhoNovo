package udc.psw.desenho.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getDatabaseConnection() throws SQLException {
		return DriverManager
				.getConnection("jdbc:postgresql://localhost/AplicacaoDesenho?useTimeout=true&serverTimezone=UTC", "postgres", "xt@1040");
	}

}
