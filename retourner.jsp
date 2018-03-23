<!DOCTYPE html>

<html>

<head>

</head>

<body>

	<%@ 
		page import="mediatheque.Mediatheque,mediatheque.Document,mediatheque.Utilisateur,java.util.List"
	 %>
	
	<form method="POST" action="/MediaWeb/services/retour">
		<input type="text" name="idDocument"></input>
		<input type="submit" value="Valider"></input>
	</form>
	<p>${result}</p>

	<%
		Utilisateur u =(Utilisateur)request.getSession().getAttribute("user");
		out.println(u.getId());
		List<Document> docs = Mediatheque.getInstance().getDocumentsEmpruntesPar((Utilisateur)request.getSession().getAttribute("user"));
		out.println(docs.size());	
		for(Document doc : docs){
			out.println("Oui");	
	%>
		<%=doc.affiche()[0]%> by <%=doc.affiche()[1]%> - <%=doc.affiche()[2]%>	
	<%
		}
	%>
</body>

</html>
