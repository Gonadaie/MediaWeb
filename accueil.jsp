<!DOCTYPE html>

<html>

<head

</head>

<body>

	<%@
		page import="mediatheque.Utilisateur"
	%>
	
	<%
		Utilisateur user = (Utilisateur)session.getAttribute("user");	
	%>
	
	<h1>Bienvenue <%= user.getName() %> !</h1>

	<a href="emprunter">Emprunter un document</a>
	<a href="retourner">Retourner un document</a>
	<%
		if(user.getType() == 2)
			out.println("<a href='ajouter'>Ajouter un document</a>");
	%>
	
</body>

</html>
