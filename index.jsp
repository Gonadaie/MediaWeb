<!DOCTYPE html>

<html>
<body>

	<%
		if(request.getSession().getAttribute("user") != null){
			response.sendRedirect("accueil");
		}
	%>
	
	<h1>Connexion</h1>
	<form method="POST" action="/MediaWeb/connection">
		<input type="text" name="user" placeholder="username"></input>
		<input type="password" name="password" placeholder="password"></input>
		<input type="submit" value="Connexion"></input>
	</form>
	<p>${result}</p>
</body>
</html>
