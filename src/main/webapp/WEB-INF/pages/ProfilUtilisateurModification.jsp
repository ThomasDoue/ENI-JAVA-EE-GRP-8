<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
      
<title>Retour Formulaire</title>
</head>
<body>

  <h1>Retour Formulaire</h1>

<form  action="RetourFormulaire"  method="post">
  <div class="form-group">
	    <label for="pseudo">Pseudo : </label>
	 	<input type="text" id="pseudo" name="pseudo"class="form-control" value= "${pseudo}"><br/>
 </div>
 <div class="form-group">
 	<label for="nom">Nom   :</label>
 	<input type="text" id="nom" name="nom" class="form-control"  value= "${nom}"><br/>
 </div>
 
 <div class="form-group">
 	<label for="email">Email   :</label>
 	<input type="text" id="email" name="email" class="form-control" value= "${email}"><br/>
 </div>
 <div class="form-group">	
 	<label for="telephone">Teléphone   :</label>
 	<input type="text" id="telephone" name="telephone"class="form-control" value="${telephone}" ><br/>
</div>
 <div class="form-group">
 	<label for="rue">Rue   :</label>
 	<input type="text" id="rue" name="rue" class="form-control" value="${rue}"><br/>
</div>
 <div class="form-group">
 	<label for="code_Postal">Code Postal :</label>
 	<input type="text" id="code_Postal" name="code_Postal" class="form-control" value="${codePostal}"><br/>
 </div>
 <div class="form-group">
  	<label for="ville">Ville :</label>
 	<input type="text" id="ville" name="ville" class="form-control" value="${ville}"><br/>
 </div>
 <div class="form-group">
  	<label for="credit">Credit :</label>
 	<input type="text" id="credit" name="credit" class="form-control" value="${credit}"><br/>
 </div>
  

  
  
 
  <button type="modifier" class="btn btn-primary">Modifier</button>
</form>


	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</body>
</html>