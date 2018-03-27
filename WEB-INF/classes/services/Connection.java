package services;

import mediatheque.Mediatheque;
import mediatheque.Utilisateur;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

public class Connection extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		PrintWriter out = resp.getWriter();
		
		String username = req.getParameter("user");
		String password = req.getParameter("password");
		
		if(username.equals("admin"))
			if(password.equals("admin")) {
				initDB();
				resp.sendRedirect(".");
				return;
			}
		
		try {
			Utilisateur user = connectUser(username, password);
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			resp.sendRedirect("accueil");
		}
		catch(ConnectionException e) {out.write(e.getMessage());}
		out.close();
	}
	
	public Utilisateur connectUser(String username, String password) throws ConnectionException {
		Utilisateur user;
		user = Mediatheque.getInstance().getUser(username, password);
		if(user != null) {
			return user;
		}
		else
			throw new ConnectionException();
	}
	
	public void initDB() {
		try {
			Class.forName("persistantdata.MediathequeData");
		}catch(ClassNotFoundException e) {e.printStackTrace();}
	}
}
