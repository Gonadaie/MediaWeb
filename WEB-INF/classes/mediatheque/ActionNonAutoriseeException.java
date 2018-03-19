package mediatheque;

public class ActionNonAutoriseeException extends Exception {
	public ActionNonAutoriseeException() {
		super("Action non autorise pour ce type d'utilisateur");
	}
}
