package infrastructure.persistence.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionBD {
	public static Connection getConnection() {
		Connection connection = null;
		String host ="localhost";
		String port = "3306";
		String username = "root";
		String password = "root";
		String bdname = "primer_servlet";
		String driveClassName = "com.mysql.cj.jdbc.Driver";
		
		try {
			Class.forName(driveClassName);
			//url de conexion( url de donde quiero que se conecte
			String url = "jdbc:mysql://" + host + ":" + port + "/" + bdname;
			connection = DriverManager.getConnection(url, username, password);
			  // Desactivar el autocommit
            connection.setAutoCommit(false);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

}
