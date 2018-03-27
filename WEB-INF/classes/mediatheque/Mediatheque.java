package mediatheque;

import java.util.List;
import services.ActionNonAutoriseeException;

/**
 cette classe represente la mediatheque du point de vue du domaine
 cette classe est un singleton
 elle a un attribut qui fait le lien avec les donnees persistentes
 
 LES SERVLETS DOIVENT S'ADRESSER A CETTE CLASSE EXCLUSIVEMENT
 POUR INTERROGER LES DONNEES

 beaucoup des methodes de Mediatheque sont deleguees a l'attribut de persistance
 qui devra repercuter ces operations sur les donnees persistantes

*/
public class Mediatheque {
// Jean-Francois Brette 01/01/2018

// singleton standard ======================== 
	static {
		instance = new Mediatheque();
	}
	
	private static Mediatheque instance;
	public static Mediatheque getInstance() {
		return instance;
	}
	
	private Mediatheque () {}
// fin - singleton standard ==================

// lien avec les donnees persistantes +++++++++++++++
	private PersistentMediatheque data;

	public void setData(PersistentMediatheque data) {
		if (this.data == null) this.data = data;
	}
// fin - lien avec les donnees persistantes +++++++++
	

// ********** action sur le document ***********************

	// enregistre l'emprunt par l'abonne a du document d)

	public void emprunt(Document d, Utilisateur a) throws EmpruntException {
		d.emprunter(a);
	}

	//enregistre le retour du document d)

	public void retour (Document d, Utilisateur a) throws DocNonPossedeException {
		d.retour(a);
	}

// *********************** delegation **********************

	// renvoie la liste de tous les documents de la bibliotheque

	public List<Document> documents() {
		return data.documents();
	}

	// renvoie le user de login et passwd 
	// si pas trouve, renvoie null
	
	public Utilisateur getUser (String login, String password) {
		return data.getUser(login, password);
	}

	// renvoie le document de numero numDocument
	// si pas trouve, renvoie null

	public Document getDocument(int numDocument) {
		return data.getDocument(numDocument);
	}
	
	// ajoute un nouveau document

	public void nouveauDocument(int type, Object... args ) throws CreationDocumentException, ActionNonAutoriseeException {
		data.nouveauDocument(type, args);
	};
	
	public List<Document> getDocumentsEmpruntesPar(Utilisateur a) {
		return data.getDocumentsEmpruntesPar(a);
	}
	
	public List<Document> getDocumentsEmpruntes() {
		return data.getDocumentsEmpruntes();
	}

	public List<Document> getDocumentsDisponibles() {
		return data.getDocumentsDisponibles();
	}

}
