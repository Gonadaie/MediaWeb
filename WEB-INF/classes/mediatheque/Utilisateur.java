package mediatheque;

public class Utilisateur {
	
	private int id;
	private String name;
	private int type;
	
	public Utilisateur(int id, String name, int type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getType() {
		return type;
	}
}
