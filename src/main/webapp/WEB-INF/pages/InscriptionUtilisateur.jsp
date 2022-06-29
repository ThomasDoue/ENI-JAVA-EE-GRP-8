<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style><%@include file="/WEB-INF/css/style.css"%></style>
</head>
<body>
<form method="post" action="Inscription">
Mon Profil
<br>
<div class="form-group">
<label for="name">Pseudo</label>
<input type="text" id="textbox1" name="Pseudo">
<br>
<label for="name">Prénom</label>
<input type="text" id="textbox2" name="Prenom">
<br>
<label for="name">Télephone</label>
<input type="text" id="Telephone" name="Telephone">
<br>
<label for="name">Code Postal</label>
<input type="text" id="CodePostal" name="CodePostal">
<br>
<label for="name">Mot de passe</label>
<input type="text" id="MotDePasse" name="MotDePasse">
</div>
<br>
<div class="form-group">
<label for="name">Nom</label>
<input type="text" id="Nom" name="Nom">
<br>
<label for="name">Email</label>
<input type="text" id="Email" name="Email">
<br>
<label for="name">Rue</label>
<input type="text" id="Rue" name="Rue">
<br>
<label for="name">Ville</label>
<input type="text" id="Ville" name="Ville">
<br>
<label for="name">Confirmation</label>
<input type="text" id="Confirmation" name="Confirmation">
</div>

</form>

</body>
</html>