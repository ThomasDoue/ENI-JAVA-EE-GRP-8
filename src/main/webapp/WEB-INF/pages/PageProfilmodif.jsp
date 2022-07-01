<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
      
<title>Mon Profil</title>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<style><%@include file="/WEB-INF/pages/header.jsp"%></style>
</head>
<body>

  <h1>Mon Profil</h1>

<form  action="<%=request.getContextPath()%>/ProfilModifierSupprimerServlet"  method="post">
  <div class="form-group">
	    <label for="pseudo">Pseudo : </label>
	 	<input type="text" id="pseudo" name="pseudo"class="form-control" value= "${pseudo}">
 
 
 	<label for="nom">Nom   :</label>
 	<input type="text" id="nom" name="nom" class="form-control"  value= "${nom}"><br/>
 </div>
 
  <div class="form-group">
 	<label for="prenom">Prenom   :</label>
 	<input type="text" id="prenom" name="prenom" class="form-control"  value= "${prenom}">
 
 	<label for="email">Email   :</label>
 	<input type="text" id="email" name="email" class="form-control" value= "${email}"><br/>
 </div>
 <div class="form-group">	
 	<label for="telephone">Teléphone   :</label>
 	<input type="text" id="telephone" name="telephone"class="form-control" value="${telephone}" >

 	<label for="rue">Rue   :</label>
 	<input type="text" id="rue" name="rue" class="form-control" value="${rue}"><br/>
</div>
<div class="form-group">
	<label for="codePostal">Code Postal :</label>
	<input type="text" id="codePostal" name="codePostal" class="form-control" value="${codePostal}">

 	<label for="ville">Ville :</label>
	<input type="text" id="ville" name="ville" class="form-control" value="${ville}"><br/>
</div>
 
 <div class="form-group">	
 	<label for="motdepassseactuel">Mot de Passe actuel  :</label>
 	<input type="password" id="motdepassseactuel" name="motdepassseactuel"class="form-control" value="${motdepassseactuel}" ><br/>
</div>
  
  <div class="form-group">
 	<label for="nouveaumotdepasse">Nouveau mot de passe :</label>
 	<input type="password" id="nouveaumotdepasse" name="nouveaumotdepasse" class="form-control" value="${nouveaumotdepasse}">

  	<label for="confirmation">Confirmation :</label>
 	<input type="password" id="confirmation" name="confirmation" class="form-control" value="${confirmation}"><br/>
 </div>
 
 <a>Credit : ${credit} </a><br/>
 </form>
 <input type="button" name="enregistrer" value="Bouton cliquer">
 
 
 
  



	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</body>
</html>