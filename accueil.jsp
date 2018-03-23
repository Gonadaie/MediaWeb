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

	<a href="/MediaWeb/emprunter">Emprunter un document</a>
	<a href="/MediaWeb/retourner">Retourner un document</a>
	<%
		if(user.getType() == 2){
	%>
		<a href='/MediaWeb/ajouter'>Ajouter un document</a>
	<%}%>
	
	<a href="/MediaWeb/disconnect">Deconnexion</a>
	
</body>

</html>
