<!DOCTYPE html>

<html>

<head>

</head>

<body>

	<%@ 
		page import="mediatheque.Mediatheque,mediatheque.Document,mediatheque.Utilisateur,java.util.List"
	 %>
	
	<a href="/MediaWeb/accueil"> <-- Retour </a>

	<form method="POST" action="/MediaWeb/services/retour">
		<input type="text" name="idDocument"></input>
		<input type="submit" value="Valider"></input>
	</form>
	<p>${result}</p>

	<%
		List<Document> docs = Mediatheque.getInstance().getDocumentsEmpruntesPar((Utilisateur)request.getSession().getAttribute("user"));
		for(Document doc : docs){
			for(Object o : doc.affiche()){
				if(o != null){
	%>
			<%=o%> - 
	<%			}
			}
	%> </br> 
	
	<% } %>
</body>

</html>
