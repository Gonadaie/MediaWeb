package services;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import mediatheque.Utilisateur;
import mediatheque.Document;
import mediatheque.EmpruntException;
import mediatheque.Mediatheque;

public class Emprunt extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Utilisateur user = (Utilisateur)session.getAttribute("user");
		int idDoc = Integer.parseInt(req.getParameter("idDocument"));
		Document doc = Mediatheque.getInstance().getDocument(idDoc);
		
		req.setAttribute("result", "Le document a bien ete emprunte.");
		
		try {
			doc.emprunter(user);
		} catch (EmpruntException e) { req.setAttribute("result", e.getMessage()); }
		
		req.getRequestDispatcher("/emprunter").forward(req, resp);
	}
}
