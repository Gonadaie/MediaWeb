package persistantdata;

import java.util.ArrayList;
import java.util.List;

import mediatheque.*;

import java.sql.*;

// classe mono-instance  dont l'unique instance n'est connue que de la bibliotheque
// via une auto-declaration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
// Jean-Francois Brette 01/01/2018
	
	private static Connection co;
	
	static {
		Mediatheque.getInstance().setData(new MediathequeData());
		co = ConnectionDB.getConnection();
	}

	private MediathequeData() {
		
	}

	// renvoie la liste de tous les documents de la bibliotheque
	@Override
	public List<Document> documents() {
		String query = "SELECT * FROM DOCUMENT";
		PreparedStatement documentsStatement;
		
		try {
			documentsStatement = co.prepareStatement(query);
			
			ResultSet result =  documentsStatement.executeQuery();
			
			List<Document> list = new ArrayList<>();
			while(result.next())
				list.add(new DocumentData(result.getInt("id"), result.getString("titre"), result.getString("nomAuteur")));
			
			return list;
		} catch (SQLException e) { e.printStackTrace(); }
		return null;
	}	

	// va recuperer le User dans la BD et le renvoie
	// si pas trouve, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		String query = "SELECT * FROM USER WHERE name = ? AND PASSWORD = ?";
		PreparedStatement userStatement;
		
		try {
			userStatement = co.prepareStatement(query);
			userStatement.setString(1, login);
			userStatement.setString(2, password);
			
			ResultSet result =  userStatement.executeQuery();
			result.first();
			return new Utilisateur(result.getInt("id"), result.getString("name"));
			
		} catch (SQLException e) { e.printStackTrace(); }
		
		return null;
	}

	// va recuperer le document de numero numDocument dans la BD
	// et le renvoie
	// si pas trouve, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		String query = "SELECT * FROM DOCUMENT WHERE id = ?";
		PreparedStatement documentsStatement;
		
		try {
			documentsStatement = co.prepareStatement(query);
			documentsStatement.setInt(1, numDocument);
			
			ResultSet result =  documentsStatement.executeQuery();
			result.first();
			return new DocumentData(result.getInt("id"), result.getString("titre"), result.getString("nomAuteur"));
			
		} catch (SQLException e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public void nouveauDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc...
	}

}
