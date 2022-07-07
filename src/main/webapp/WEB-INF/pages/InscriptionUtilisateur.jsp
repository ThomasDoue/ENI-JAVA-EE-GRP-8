<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription</title>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<style><%@include file="/WEB-INF/pages/header.jsp"%></style>
</head>
<body class = "bodyInscription">

<c:if test="${verifmdp == false}">
		<p>Le mot de passe et le mot de passe de confirmation n'est pas le même.</p>	
	</c:if>
<form class = "forminscription" action="<%=request.getContextPath()%>/Inscription" method="post">
	Mon Profil
	<br>
	<div class="form-group">
	<label for="name">Pseudo (uniquement des lettres et des chiffres)</label>
	<input class = "inputInscription" type="text" id="Pseudo" name="Pseudo" required pattern="[a-zA-Z0-9]+" minlength="1" maxlength="30">
	<br>
	<label for="name">Prénom</label>
	<input class = "inputInscription" type="text" id="Prenom" name="Prenom" required minlength="1" maxlength="30">
	<br>
	<label for="name">Télephone (format 0*-**-**-**-**)</label>
	<input class = "inputInscription" type="tel" id="Telephone" name="Telephone" pattern="[0-9]{2}-[0-9]{2}-[0-9]{2}-[0-9]{2}-[0-9]{2}" required minlength="8" maxlength="15" >
	<br>
	<label for="name">Code Postal</label>
	<input class = "inputInscription" type="text" id="CodePostal" name="CodePostal" required minlength="4" maxlength="10">
	<br>
	<label for="name">Mot de passe</label>
	<input class = "inputInscription" type="password" id="MotDePasse" name="MotDePasse" required minlength="1" maxlength="30">
	</div>
	<br>
	<label for="name">Nom</label>
	<input class = "inputInscription" type="text" id="Nom" name="Nom" required minlength="1" maxlength="30">
	<br>
	<label for="name">Email</label>
	<input class = "inputInscription" type="email" id="Email" name="Email" required minlength="1" maxlength="50">
	<br>
	<label for="name">Rue</label>
	<input class = "inputInscription" type="text" id="Rue" name="Rue" required minlength="1" maxlength="30">
	<br>
	<label for="name">Ville</label>
	<input class = "inputInscription" type="text" id="Ville" name="Ville" required minlength="1" maxlength="50">
	<br>
	<label for="name">Confirmation</label>
	<input class = "inputInscription" type="password" id="Confirmation" name="Confirmation" required minlength="1" maxlength="30">
	<br>
	<label for="name">125 crédits sont offerts à la création d'un nouveau compte !</label>
	<br>
	<button class = "button">Enregistrer</button>
</form>
</body>
</html>