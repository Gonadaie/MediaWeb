<!DOCTYPE html>

<html>

<body>

	<%@ 
		page import="mediatheque.Mediatheque,mediatheque.Document,java.util.List"
	 %>
	
	<a href="/MediaWeb/accueil"> <-- Retour </a>

	<form method="POST" action="/MediaWeb/services/emprunt">
		<input type="text" name="idDocument"></input>	
		<input type="submit" value="Valider"></input>
	</form>
	<p>${result}</p>

	<%
		List<Document> docs = Mediatheque.getInstance().getDocumentsDisponibles();
		for(Document doc : docs){
			for(Object o : doc.affiche()){
				if(o != null){
	%>
			<%=o%> - 
	<%			}
			}
	%> </br> 
	
	<% } %></body>

</html>
