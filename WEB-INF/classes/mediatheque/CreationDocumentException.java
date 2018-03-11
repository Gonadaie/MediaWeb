package mediatheque;

public class CreationDocumentException extends Exception {
	public CreationDocumentException() {
		super("Le document n'a pas pu etre cree");
	}
}
