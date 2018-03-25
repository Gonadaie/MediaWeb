package persistantdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.DocNonPossedeException;
import mediatheque.Utilisateur;

public class Livre extends BaseDocument {
	
	private int nbPages;
	private String genre;
	
	public Livre(int id, String titre, String auteur) {
		super(id, titre, auteur);
	}
	
	public Livre(int id, String titre, String auteur, int nbPages, String genre) {
		this(id, titre, auteur);
		this.nbPages = nbPages;
		this.genre = genre;
	}

	@Override
	public Object[] affiche() {
		Object[] o = {	
						id,
						titre,
						auteur,
						nbPages != 0 ? new String(nbPages + " pages") : null,
						genre
					 };
		return o;
	}
}
