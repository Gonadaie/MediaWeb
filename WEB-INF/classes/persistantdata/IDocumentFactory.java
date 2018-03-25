package persistantdata;

import java.sql.ResultSet;
import java.sql.SQLException;

import mediatheque.CreationDocumentException;
import mediatheque.Document;

public interface IDocumentFactory {
	public Document createDocument(int type, Object... args) throws IllegalArgumentException;
	public Document createDocumentFromResultSet(ResultSet result) throws IllegalArgumentException, SQLException;
}
