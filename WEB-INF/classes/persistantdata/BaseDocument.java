package persistantdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mediatheque.DocNonPossedeException;
import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Utilisateur;

public abstract class BaseDocument implements Document {
	
	protected int id;
	protected String titre;
	protected String auteur;
	
	public BaseDocument(int id, String titre, String auteur) {
		this.id = id;
		this.titre = titre;
		this.auteur = auteur;
	}

	@Override
	public void emprunter(Utilisateur a) throws EmpruntException {
		if(this.estEmpruntePar() != 0) throw new EmpruntException();
		
		Connection co = DBConnection.getConnection();
		String empruntQuery = "INSERT INTO EMPRUNT (idDoc, idUser) VALUE (?, ?)";
		
		try {
			PreparedStatement empruntStatement = co.prepareStatement(empruntQuery);
			empruntStatement.setInt(1, this.id);
			empruntStatement.setInt(2, a.getId());
			empruntStatement.executeUpdate();
			empruntStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new EmpruntException();
		}
	}

	@Override
	public void retour(Utilisateur a) throws DocNonPossedeException {
		Connection co = DBConnection.getConnection();
		
		if(estEmpruntePar() != a.getId()) throw new DocNonPossedeException();
		
		try {
			String deleteQuery = "DELETE FROM EMPRUNT WHERE idUser = ? AND idDoc = ?";
			PreparedStatement deleteStatement = co.prepareStatement(deleteQuery);
			deleteStatement.setInt(1, a.getId());
			deleteStatement.setInt(2, this.id);
			deleteStatement.executeUpdate();
			deleteStatement.close();
		} catch (SQLException e) {
			e.printStackTrace() ;
			throw new DocNonPossedeException();
		}
	}

	@Override
	public Object[] affiche() {
		Object[] o = {	
						id,
						titre,
						auteur
					 };
		return o;
	}
	
	/**
	 * @return l'id de l'utilisateur ayant emprunte le document, ou 0 si le document n'est pas emprunte
	 */
	public int estEmpruntePar() {
		Connection co = DBConnection.getConnection();
		String estEmprunteParQuery = "SELECT idUser FROM EMPRUNT WHERE idDoc = ?";
		PreparedStatement estEmprunteParStatement;
		try {
			estEmprunteParStatement = co.prepareStatement(estEmprunteParQuery);
			estEmprunteParStatement.setInt(1, this.id);
			ResultSet result;
			result = estEmprunteParStatement.executeQuery();
			int r =  result.first() ? result.getInt("idUser") : 0;
			estEmprunteParStatement.close();
			result.close();
			return r;
		} catch (SQLException e) { e.printStackTrace(); }
		return 0;
	}
}
