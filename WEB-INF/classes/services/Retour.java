package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatheque.DocNonPossedeException;
import mediatheque.Document;
import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

public class Retour extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		Utilisateur user = (Utilisateur)session.getAttribute("user");
		int idDoc = Integer.parseInt(req.getParameter("idDocument"));
		Document doc = Mediatheque.getInstance().getDocument(idDoc);
		req.setAttribute("result", "Le document a bien ete retourne.");
		try {
			doc.retour(user);
		} catch (DocNonPossedeException e) { req.setAttribute("result", e.getMessage()); }
		
		req.getRequestDispatcher("/retourner").forward(req, resp);
	}
}
