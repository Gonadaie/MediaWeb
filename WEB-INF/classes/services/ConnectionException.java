package services;

public class ConnectionException extends Exception {
	public ConnectionException() {
		super("Mauvais nom d'utilisateur ou mauvais mot de passe");
	}
}
