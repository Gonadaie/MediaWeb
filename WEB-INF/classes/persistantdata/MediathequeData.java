package persistantdata;

import java.util.LinkedList;
import java.util.List;

import mediatheque.*;
import services.ActionNonAutoriseeException;

import java.sql.*;

// classe mono-instance  dont l'unique instance n'est connue que de la bibliotheque
// via une auto-declaration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
// Jean-Francois Brette 01/01/2018
	
	private static Connection co;
	private static IDocumentFactory documentFactory;
	
	static {
		Mediatheque.getInstance().setData(new MediathequeData());
		co = ConnectionDB.getConnection();
		documentFactory = new DocumentFactory();
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
			
			List<Document> docs = new LinkedList<>();
			while(result.next())
				docs.add(documentFactory.createDocumentFromResultSet(result));
			
			return docs;
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
			if(result.first())
				return new Utilisateur(result.getInt("id"), result.getString("name"), result.getInt("type"));
			
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
			return documentFactory.createDocumentFromResultSet(result);
		} catch (SQLException e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public void nouveauDocument(int type, Object... args) throws CreationDocumentException {
		// args[0] -> le titre
		// args [1] --> l'nomAuteur
		// etc...
		
		if(args.length < 3) throw new CreationDocumentException();
		
		String nouveauDocQuery = "INSERT INTO DOCUMENT (titre, nomAuteur, type) VALUES (?, ?, ?)";
		try {
			PreparedStatement nouveauDocStatement = co.prepareStatement(nouveauDocQuery);
			nouveauDocStatement.setString(1,(String)args[0]);
			nouveauDocStatement.setString(2,(String)args[1]);
			nouveauDocStatement.setInt(3,  type);
			nouveauDocStatement.executeUpdate();
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	public List<Document> getDocumentsEmpruntesPar(Utilisateur a) {
		String query = "SELECT * FROM DOCUMENT WHERE id IN (SELECT idDoc FROM EMPRUNT WHERE idUser = ?)";
		List<Document> docs = new LinkedList<>();
		
		try {
			PreparedStatement statement = co.prepareStatement(query);
			statement.setInt(1, a.getId());
			ResultSet result = statement.executeQuery();
			
			//TODO Document Factory
			while(result.next())
				docs.add(documentFactory.createDocumentFromResultSet(result));
		}catch(SQLException e) { e.printStackTrace(); }
		
		return docs;
	}
	
	public List<Document> getDocumentsEmpruntes() {
		String query = "SELECT * FROM DOCUMENT WHERE id IN (SELECT idDoc FROM EMPRUNT)";
		List<Document> docs = new LinkedList<>();
		
		try {
			PreparedStatement statement = co.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while(result.next())
				docs.add(documentFactory.createDocumentFromResultSet(result));
		}catch(SQLException e) { e.printStackTrace(); }
		
		return docs;
	}

	public List<Document> getDocumentsDisponibles() {
		String query = "SELECT * FROM DOCUMENT WHERE id NOT IN (SELECT idDoc FROM EMPRUNT)";
		List<Document> docs = new LinkedList<>();
		
		try {
			PreparedStatement statement = co.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next())
				docs.add(documentFactory.createDocumentFromResultSet(result));
		}catch(SQLException e) { e.printStackTrace(); }
		
		return docs;
	}
}
