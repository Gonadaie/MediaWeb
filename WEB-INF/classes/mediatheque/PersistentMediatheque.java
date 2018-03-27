package mediatheque;

import java.util.List;

public interface PersistentMediatheque {
// Jean-Francois Brette 01/01/2018
	List<Document> documents();

	Document getDocument(int numDocument);

	Utilisateur getUser(String login, String password);
	
	void nouveauDocument(int type, Object... args ) throws CreationDocumentException;

	public List<Document> getDocumentsEmpruntesPar(Utilisateur a);
	
	public List<Document> getDocumentsEmpruntes();

	public List<Document> getDocumentsDisponibles();
}
