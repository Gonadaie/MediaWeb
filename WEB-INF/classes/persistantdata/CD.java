package persistantdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CD extends BaseDocument {
	
	private int duree;
	private String genre;
	
	private static final int typeNb = 2;

	public CD(int id, String titre, String auteur) {
		super(id, titre, auteur);
	}
	
	public CD(int id, String titre, String auteur, int duree, String genre) {
		this(id, titre, auteur);
		this.duree = duree;
		this.genre = genre;
	}

	@Override
	public Object[] affiche() {
		Object[] o = {
				"CD",
				id,
				titre,
				auteur,
				duree != 0 ? new String(duree + " min") : "duree inconnue",
				genre
			 };
		return o;
	}
}
