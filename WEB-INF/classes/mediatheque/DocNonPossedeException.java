package mediatheque;

public class DocNonPossedeException extends Exception {
	
	public DocNonPossedeException() {
		super("Le document n'a pas pu etre retourne car il n'est pas possede par l'utilisateur");
	}
}
