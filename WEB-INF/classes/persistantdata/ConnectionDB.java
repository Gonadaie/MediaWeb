package persistantdata;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionDB {

	private static Connection co;
	private final static String URL = "localhost:3306";
	private final static String USER = "mediaweb";
	private final static String PASSWORD = "mediaweb";
	
	static {
		try {
			Class.forName("com.mysql.jdbc");
			co = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) { e.printStackTrace(); }
	}
	
	public static Connection getConnection() {
		return co;
	}
}
