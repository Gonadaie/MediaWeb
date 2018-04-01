package persistantdata;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import mediatheque.PersistentMediatheque;
import mediatheque.Utilisateur;
import mediatheque.CreationDocumentException;
import mediatheque.Document;
import mediatheque.Mediatheque;

import java.sql.*;

// classe mono-instance  dont l'unique instance n'est connue que de la bibliotheque
// via une auto-declaration dans son bloc static

public class MediathequeData implements PersistentMediatheque {
// Jean-Francois Brette 01/01/2018
	
	private static IDocumentFactory documentFactory;
	
	static {
		Mediatheque.getInstance().setData(new MediathequeData());
		documentFactory = new DocumentFactory();
	}

	private MediathequeData() {
		
	}

	// renvoie la liste de tous les documents de la bibliotheque
	@Override
	public List<Document> documents() {
		String query = "SELECT * FROM DOCUMENT";
		PreparedStatement documentsStatement;
		Connection co = DBConnection.getConnection();
		
		try {
			documentsStatement = co.prepareStatement(query);
			
			ResultSet result =  documentsStatement.executeQuery();
			
			List<Document> docs = new LinkedList<>();
			while(result.next())
				docs.add(documentFactory.createDocumentFromResultSet(result));
			
			documentsStatement.close();
			result.close();
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
		Connection co = DBConnection.getConnection();
		
		try {
			userStatement = co.prepareStatement(query);
			userStatement.setString(1, login);
			userStatement.setString(2, password);
			
			ResultSet result =  userStatement.executeQuery();
			Utilisateur user;
			if(result.first()) {
				user = new Utilisateur(result.getInt("id"), result.getString("name"), result.getInt("type"));
				userStatement.close();
				result.close();
				return user;
			}
			
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
		Connection co = DBConnection.getConnection();
		
		try {
			documentsStatement = co.prepareStatement(query);
			documentsStatement.setInt(1, numDocument);
			
			ResultSet result =  documentsStatement.executeQuery();
			result.first();
			Document doc = documentFactory.createDocumentFromResultSet(result);
			documentsStatement.close();
			result.close();
			return doc;
		} catch (SQLException e) { e.printStackTrace(); }
		return null;
	}

	@Override
	public void nouveauDocument(int type, Object... args) throws CreationDocumentException {
		// args[0] -> le titre
		// args [1] --> l'nomAuteur
		// etc...
		final int nbParamSQL = 6;
		if(args.length != nbParamSQL-1) throw new CreationDocumentException();
		
		List<Object> argsList = new LinkedList<>(Arrays.asList(args));

		for(int i = args.length; i < nbParamSQL; ++i)
			argsList.add(null);
		
		Connection co = DBConnection.getConnection();
		
		String nouveauDocQuery = "INSERT INTO DOCUMENT (titre, nomAuteur, type, genre, duree, nbPages) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement nouveauDocStatement = co.prepareStatement(nouveauDocQuery);
			nouveauDocStatement.setString(1,(String)argsList.get(0));	//titre
			nouveauDocStatement.setString(2,(String)argsList.get(1));	//nomAuteur
			nouveauDocStatement.setInt(3,  type);
			nouveauDocStatement.setString(4, (String)argsList.get(2)); 	//genre
			nouveauDocStatement.setInt(5, (int)argsList.get(3)); 		//duree
			nouveauDocStatement.setInt(6, (int)argsList.get(4));		//nbPages
			nouveauDocStatement.executeUpdate();
			nouveauDocStatement.close();
		} catch (SQLException e) { e.printStackTrace(); }
	}
	
	public List<Document> getDocumentsEmpruntesPar(Utilisateur a) {
		String query = "SELECT * FROM DOCUMENT WHERE id IN (SELECT idDoc FROM EMPRUNT WHERE idUser = ?)";
		List<Document> docs = new LinkedList<>();
		Connection co = DBConnection.getConnection();
		
		try {
			PreparedStatement statement = co.prepareStatement(query);
			statement.setInt(1, a.getId());
			ResultSet result = statement.executeQuery();
			
			while(result.next())
				docs.add(documentFactory.createDocumentFromResultSet(result));
			statement.close();
			result.close();
		}catch(SQLException e) { e.printStackTrace(); }
		
		return docs;
	}
	
	public List<Document> getDocumentsEmpruntes() {
		String query = "SELECT * FROM DOCUMENT WHERE id IN (SELECT idDoc FROM EMPRUNT)";
		List<Document> docs = new LinkedList<>();
		Connection co = DBConnection.getConnection();
		
		try {
			PreparedStatement statement = co.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while(result.next())
				docs.add(documentFactory.createDocumentFromResultSet(result));
			statement.close();
			result.close();
		}catch(SQLException e) { e.printStackTrace(); }
		
		return docs;
	}

	public List<Document> getDocumentsDisponibles() {
		String query = "SELECT * FROM DOCUMENT WHERE id NOT IN (SELECT idDoc FROM EMPRUNT)";
		List<Document> docs = new LinkedList<>();
		Connection co = DBConnection.getConnection();
		
		try {
			PreparedStatement statement = co.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			
			while(result.next())
				docs.add(documentFactory.createDocumentFromResultSet(result));
			statement.close();
			result.close();
		}catch(SQLException e) { e.printStackTrace(); }
		
		return docs;
	}
}
