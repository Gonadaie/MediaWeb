package persistantdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.DocNonPossedeException;
import mediatheque.Utilisateur;

public class DocumentData implements Document {
	
	private int id;
	private String titre;
	private String auteur;
	private int type;
	
	public DocumentData(int id, String titre, String auteur) {
		this.id = id;
		this.titre = titre;
		this.auteur = auteur;
	}
	
	public DocumentData(int id, String titre, String auteur, int type) {
		this(id, titre, auteur);
		this.type = type;
	}

	@Override
	public void emprunter(Utilisateur a) throws EmpruntException {
		if(this.estEmprunte() != 0) throw new EmpruntException();
		
		Connection co = ConnectionDB.getConnection();
		String empruntQuery = "INSERT INTO EMPRUNT (idDoc, idUser) VALUE (?, ?)";
		
		try {
			PreparedStatement empruntStatement = co.prepareStatement(empruntQuery);
			empruntStatement.setInt(1,  this.id);
			empruntStatement.setInt(1,  a.getId());
			empruntStatement.executeQuery();
		} catch (SQLException e) { e.printStackTrace(); }
	}

	@Override
	public void retour(Utilisateur a) throws DocNonPossedeException {
		Connection co = ConnectionDB.getConnection();
		
		if(estEmprunte() != this.id) throw new DocNonPossedeException();
		
		try {
			String deleteQuery = "DELETE FROM EMPRUNT WHERE idUser = ? AND idDoc = ?";
			PreparedStatement deleteStatement = co.prepareStatement(deleteQuery);
			deleteStatement.setInt(1, a.getId());
			deleteStatement.setInt(2, this.id);
			deleteStatement.executeQuery();
		} catch (SQLException e) { e.printStackTrace() ;
		}
	}

	@Override
	public Object[] affiche() {
		
		return null;
	}
	
	/**
	 * @return l'id de l'utilisateur ayant emprunte le document, ou 0 si le document n'est pas emprunte
	 */
	public int estEmprunte() {
		Connection co = ConnectionDB.getConnection();
		String estEmprunteQuery = "SELECT idUser FROM EMPRUNT WHERE idDoc = ?";
		PreparedStatement estEmprunteStatement;
		try {
			estEmprunteStatement = co.prepareStatement(estEmprunteQuery);
			estEmprunteStatement.setInt(1, this.id);
			ResultSet result;
				result = estEmprunteStatement.executeQuery();
			return result.next() ? result.getInt("id") : 0;
		} catch (SQLException e) { e.printStackTrace(); }
		return 0;
	}
}
