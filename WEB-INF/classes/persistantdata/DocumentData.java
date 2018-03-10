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
	
	public DocumentData(int id, String titre, String auteur) {
		this.id = id;
		this.titre = titre;
		this.auteur = auteur;
	}

	@Override
	public void emprunter(Utilisateur a) throws EmpruntException {
		Connection co = ConnectionDB.getConnection();
		
		
	}

	@Override
	public void retour(Utilisateur a) throws DocNonPossedeException {
		Connection co = ConnectionDB.getConnection();
		try {
			String retourQuery = "SELECT idUser FROM EMPRUNT WHERE idDoc = ?";
			PreparedStatement retourStatement = co.prepareStatement(retourQuery);
			retourStatement.setInt(1, this.id);
			ResultSet result = retourStatement.executeQuery();
			
			result.first();
			if(a.getId() != result.getInt("id"))
				throw new DocNonPossedeException();
			
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

}
