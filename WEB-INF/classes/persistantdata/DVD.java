package persistantdata;

public class DVD extends BaseDocument {
	
	private int duree;
	private String genre;

	public DVD(int id, String titre, String auteur) {
		super(id, titre, auteur);
	}
	
	public DVD(int id, String titre, String auteur, int duree, String genre) {
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
				duree != 0 ? new String(duree + " min") : "duree inconnue",
				genre
			 };
		return o;
	}
	
}
