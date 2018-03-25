<!DOCTYPE html>

<html>

<head>

</head>

<body>
	<form method="POST" action="/MediaWeb/services/ajout">
		Titre : 
		<input type="text" name="docTitle"></input></br>
		Auteur : 
		<input type="text" name="docAuthor"></input></br>
		Type
		<input type="text" name="docType"></input></br>
		<input type="submit" value="Ajouter"></input>
	<form>

	<p>${result}</p>
</body>

</html>
