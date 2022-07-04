<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	 	<input type="text" id="pseudo" name="pseudo"class="form-control" value= "${pseudo}" required minlenght="1">
 
 
 	<label for="nom">Nom   :</label>
 	<input type="text" id="nom" name="nom" class="form-control"  value= "${nom}" required minlenght="1"><br/>
 </div>
 
  <div class="form-group">
 	<label for="prenom">Prenom   :</label>
 	<input type="text" id="prenom" name="prenom" class="form-control"  value= "${prenom}" required minlenght="1">
 
 	<label for="email">Email   :</label>
 	<input type="text" id="email" name="email" class="form-control" value= "${email}" required minlenght="1"><br/>
 </div>
 <div class="form-group">	
 	<label for="telephone">Teléphone   :</label>
 	<input type="text" id="telephone" name="telephone"class="form-control" value="${telephone}" required minlenght="1" >

 	<label for="rue">Rue   :</label>
 	<input type="text" id="rue" name="rue" class="form-control" value="${rue}" required minlenght="1"><br/>
</div>
<div class="form-group">
	<label for="codePostal">Code Postal :</label>
	<input type="text" id="codePostal" name="codePostal" class="form-control" value="${codePostal}" required minlenght="1">

 	<label for="ville">Ville :</label>
	<input type="text" id="ville" name="ville" class="form-control" value="${ville}" required minlenght="1"><br/>
</div>
 
 <div class="form-group">	
 	<label for="motdepassseactuel">Mot de Passe actuel  :</label>
 	<input type="password" id="motdepassseactuel" name="motdepassseactuel"class="form-control" required minlenght="1" ><br/>
</div>

	<c:if test="${!empty erreurMotdePasse }">
		<h2>"${erreurMotdePasse}"</h2>
	</c:if>

  <div class="form-group">
 	<label for="nouveaumotdepasse">Nouveau mot de passe :</label>
 	<input type="password" id="nouveaumotdepasse" name="nouveaumotdepasse" class="form-control" required minlenght="1">

	

  	<label for="confirmation">Confirmation :</label>
 	<input type="password" id="confirmation" name="confirmation" class="form-control" required minlenght="1"><br/>
 </div>
 
 <a>Credit : ${credit} </a><br/>
 
 <!-- Button prend la propriéter button et type submit permet de capter l'action name est recupperer dans la servlet  -->
 <Button type="submit" name="actionUtilisateur" value="enregister">Enregister</Button>
 
 <Button type="submit" name="actionUtilisateur" value="supprimer">Supprimer mon Compte</Button>
 </form>
 
 
 
 
  



	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
</body>
</html>