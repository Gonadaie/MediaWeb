<!DOCTYPE html>

<html>

<head>

</head>

<body>
	
	<a href="/MediaWeb/accueil"> <-- Retour </a></br>

	<form method="POST" action="/MediaWeb/services/ajout">
		Type
		<select name="docType">
			<option value="1">Livre</option>
			<option value="2">CD</option>
			<option value="3">DVD</option>
		</select></br>
		Titre : 
		<input type="text" name="docTitle"></input></br>
		Auteur : 
		<input type="text" name="docAuthor"></input></br>

		<!-- Optional infos -->
		<div id="nbPages">
			Nombre de pages :
			<input type="text" name="nbPages"></input></br>
		</div>
		
		<div id="duree">
			Duree :
			<input type="text" name="duree"></input></br>
		</div>
		
		<div id="genre">
			Genre :
			<input type="text" name="genre"></input></br>
		</div>
	

		<input type="submit" value="Ajouter"></input>
	<form>

	<p>${result}</p>


	<script>
		function hideAll(callback){
			var opt = document.querySelectorAll('#nbPages, #duree, #genre');
			opt.forEach(function(item, index){
					item.style.display = 'none';
				});
			if(callback !== undefined) callback();
		}
		
		function display(name){
			if(name === 'Livre'){
				document.querySelectorAll("#nbPages, #genre").forEach(function(item, index) { item.style.display = 'initial' });
			}
			if(name === 'CD'){
				document.querySelectorAll("#duree, #genre").forEach(function(item, index) { item.style.display = 'initial' });
			}
			if(name === 'DVD'){
				document.querySelectorAll("#duree, #genre").forEach(function(item, index) { item.style.display = 'initial' });
			}

		}

		hideAll();
		var list = document.getElementsByName("docType")[0];
		display(list.options[list.selectedIndex].innerHTML);
		list.addEventListener('change', function(){ hideAll(function() {
				display(this.options[this.selectedIndex].innerHTML);
			}.bind(list))
		});

		
	</script>
</body>

</html>
