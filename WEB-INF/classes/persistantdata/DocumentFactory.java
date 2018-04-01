package persistantdata;

import java.sql.ResultSet;
import java.sql.SQLException;

import mediatheque.Document;

public class DocumentFactory implements IDocumentFactory {

	@Override
	/**
	 * @brief Fonction très sale
	 * @param type : Type de document (1 -> Livre, 2 -> CD)
	 * @param args : Titre, auteur,
	 * @return Document cree 
	 */
	public Document createDocument(int type, Object... args) throws IllegalArgumentException {
		
		if(args.length != 3 && args.length != 5) throw new IllegalArgumentException();
		
		switch(type) {
		case 1:
			if(args.length == 3) return new Livre((int)args[0], (String)args[1], (String)args[2]);
			if(args.length == 5) return new Livre((int)args[0], (String)args[1], (String)args[2], (int)args[3], (String)args[4]);
			break;
		case 2:
			if(args.length == 3) return new CD((int)args[0], (String)args[1], (String)args[2]);
			if(args.length == 5) return new CD((int)args[0], (String)args[1], (String)args[2], (int)args[3], (String)args[4]);
			break;
		case 3:
			if(args.length == 3) return new DVD((int)args[0], (String)args[1], (String)args[2]);
			if(args.length == 5) return new DVD((int)args[0], (String)args[1], (String)args[2], (int)args[3], (String)args[4]);
			break;
		}
		
		throw new IllegalArgumentException();
	}
	
	@Override
	public Document createDocumentFromResultSet(ResultSet result) throws IllegalArgumentException, SQLException {
		switch(result.getInt("type")) {
		case 1:
			return createDocument(1, result.getInt("id"), result.getString("titre"), result.getString("nomAuteur"), result.getInt("nbPages"), result.getString("genre"));
		case 2:
			return createDocument(2, result.getInt("id"), result.getString("titre"), result.getString("nomAuteur"), result.getInt("duree"), result.getString("genre"));
		case 3:
			return createDocument(3, result.getInt("id"), result.getString("titre"), result.getString("nomAuteur"), result.getInt("duree"), result.getString("genre"));
		}
		return null;
	}
}
