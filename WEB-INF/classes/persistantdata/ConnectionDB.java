package persistantdata;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConnectionDB {

	private static Connection co;
	private final static String URL = "jdbc:mysql://localhost:3306/mediatheque";
	private final static String USER = "mediaweb";
	private final static String PASSWORD = "mediaweb";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			co = DriverManager.getConnection(URL, USER, PASSWORD);
			
			if(co != null) System.out.println("Connexion initialisee !");
			else System.out.println("La connexion a la base de donnée a echoue");
			
		} catch (ClassNotFoundException | SQLException e) { e.printStackTrace(); }
	}
	
	public static Connection getConnection() {
		return co;
	}
}
