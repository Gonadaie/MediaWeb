package persistantdata;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DBConnection {

	private static Connection co;
	private final static String URL = "jdbc:mysql://localhost:3306/mediatheque";
	private final static String USER = "mediaweb";
	private final static String PASSWORD = "mediaweb";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) { e.printStackTrace(); }
	}
	
	private static void initConnection() {
		try {
			co = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) { e.printStackTrace(); }
		
		if(co != null) System.out.println("Connexion initialisee !");
		else System.out.println("La connexion a la base de donnée a echoue");
	}
	
	public static Connection getConnection() {
		if(co == null) initConnection();
		try {
			if(co.isClosed()) initConnection();
		} catch (SQLException e) { e.printStackTrace(); }
		return co;
	}
}
