package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		//Params optionnels
		String genre = req.getParameter("genre");
		String tmp = req.getParameter("duree");
		int duree = tmp == "" ? 0 : Integer.parseInt(tmp);
		tmp = req.getParameter("nbPages");
		int nbPages = tmp == "" ? 0 : Integer.parseInt(tmp);
		req.setAttribute("result", "Le document a bien ete ajoute");
		
		try {
			if(user.getType() > 2) throw new ActionNonAutoriseeException();
			else
				Mediatheque.getInstance().nouveauDocument(docType, docTitle, docAuthor, genre, duree, nbPages);
		} catch (CreationDocumentException | ActionNonAutoriseeException e) { req.setAttribute("result", e.getMessage()); }
		
		req.getRequestDispatcher("/ajouter").forward(req, resp);
	}
}
