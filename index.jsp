<!DOCTYPE html>

<html>
<body>

	<%
		if(request.getSession().getAttribute("user") != null){
			response.sendRedirect("/acceuil");
		}
	%>
	
	<h1>Connexion</h1>
	<form method="POST" action="connection">
		<input type="text" name="user" beholder="username"></input>
		<input type="password" name="password" beholder="password"></input>
		<input type="submit" value="Connexion"></input>
	</form>
</body>
</html>
