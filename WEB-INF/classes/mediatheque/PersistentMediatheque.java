package mediatheque;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import persistantdata.Livre;

public interface PersistentMediatheque {
// Jean-Francois Brette 01/01/2018
	List<Document> documents();

	Document getDocument(int numDocument);

	Utilisateur getUser(String login, String password);
	
	void nouveauDocument(Utilisateur user, int type, Object... args ) throws CreationDocumentException, ActionNonAutoriseeException;

	public List<Document> getDocumentsEmpruntesPar(Utilisateur a);
	
	public List<Document> getDocumentsEmpruntes();

	public List<Document> getDocumentsDisponibles();
}
