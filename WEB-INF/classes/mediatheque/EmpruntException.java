package mediatheque;

public class EmpruntException extends Exception {
	
	public EmpruntException() {
		super("Le document n'a pas pu etre emprunte");
	}

}
