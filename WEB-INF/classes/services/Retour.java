package services;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mediatheque.DocNonPossedeException;
import mediatheque.Document;
import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

public class Retour extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		
		HttpSession session = req.getSession();
		Utilisateur user = (Utilisateur)session.getAttribute("user");
		int idDoc = Integer.parseInt(req.getParameter("idDocument"));
		Document doc = Mediatheque.getInstance().getDocument(idDoc);
		try {
			doc.retour(user);
		} catch (DocNonPossedeException e) { e.printStackTrace(); }
	}
}
