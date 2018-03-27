package persistantdata;

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
						nbPages != 0 ? new String(nbPages + " pages") : "nombre de pages inconnu",
						genre
					 };
		return o;
	}
}
