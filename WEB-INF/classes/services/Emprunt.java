package services;

import javax.servlet.http.*;

import mediatheque.Utilisateur;
import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Mediatheque;

public class Emprunt extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		Utilisateur user = (Utilisateur)session.getAttribute("user");
		Document doc = Mediatheque.getInstance().getDocument((int)req.getAttribute("idDocument"));
		try {
			doc.emprunter(user);
		} catch (EmpruntException e) { e.printStackTrace(); }
	}
}
