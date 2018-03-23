package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatheque.ActionNonAutoriseeException;
import mediatheque.CreationDocumentException;
import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

public class Ajout extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Utilisateur user = (Utilisateur)session.getAttribute("user");
		
		int docType = Integer.parseInt(req.getParameter("docType"));
		String docTitle = req.getParameter("docTitle");
		String docAuthor = req.getParameter("docAuthor");
		
		req.setAttribute("result", "Le document a bien ete ajoute");
		
		try {
			Mediatheque.getInstance().nouveauDocument(user, docType, docTitle, docAuthor);
		} catch (CreationDocumentException | ActionNonAutoriseeException e) { req.setAttribute("result", e.getMessage()); }
		
		req.getRequestDispatcher("/ajouter").forward(req, resp);
	}
}
