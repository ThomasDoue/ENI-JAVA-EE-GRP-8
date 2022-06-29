<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/css/style.css">
<title>Retour Formulaire</title>
<style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>

  <h1>Retour Formulaire</h1>

<form  action="RETOURFORMULAIRE"  method="post">
  <div class="form-group">
	    <label for="pseudo">Pseudo :</label>
	 	<input type="text" id="pseudo" name="pseudo"class="form-control"><br/>
 </div>
 <div class="form-group">
 	<label for="nom">Nom   :</label>
 	<input type="text" id="nom" name="nom" class="form-control"><br/>
 </div>
 
 <div class="form-group">
 	<label for="email">Email   :</label>
 	<input type="text" id="email" name="email" class="form-control"><br/>
 </div>
 <div class="form-group">	
 	<label for="telephone">Teléphone   :</label>
 	<input type="text" id="telephone" name="telephone"class="form-control"  ><br/>
</div>
 <div class="form-group">
 	<label for="rue">Rue   :</label>
 	<input type="text" id="rue" name="rue" class="form-control"><br/>
</div>
 <div class="form-group">
 	<label for="code_Postal">Code Postal :</label>
 	<input type="text" id="code_Postal" name="code_Postal" class="form-control"><br/>
 </div>
 <div class="form-group">
  	<label for="ville">Ville :</label>
 	<input type="text" id="ville" name="ville" class="form-control"><br/>
 </div>
  
 
  <button type="modifier" class="btn btn-primary">Modifier</button>
</form>


	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</body>
</html>