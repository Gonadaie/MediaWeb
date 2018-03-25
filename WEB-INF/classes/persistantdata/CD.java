package persistantdata;

import mediatheque.DocNonPossedeException;
import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Utilisateur;

public class CD extends BaseDocument {
	
	private int duree;
	private String genre;

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
				id,
				titre,
				auteur,
				duree,
				genre
			 };
		return o;
	}

}
