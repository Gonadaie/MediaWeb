package services;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatheque.ActionNonAutoriseeException;
import mediatheque.CreationDocumentException;
import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

public class Ajout extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		Utilisateur user = (Utilisateur)session.getAttribute("user");
		
		int docType = Integer.parseInt(req.getParameter("docType"));
		String docTitle = req.getParameter("docTitle");
		String docAuthor = req.getParameter("docAuthor");
		
		try {
			Mediatheque.getInstance().nouveauDocument(user, docType, docTitle, docAuthor);
		} catch (CreationDocumentException | ActionNonAutoriseeException e) { e.printStackTrace(); }
	}
}
