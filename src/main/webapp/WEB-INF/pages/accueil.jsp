<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Accueil</title>
	<style><%@include file="/WEB-INF/css/style.css"%></style>
	<style><%@include file="/WEB-INF/pages/header.jsp"%></style>
<meta charset="UTF-8">
<title>Accueil</title>
<style>
@import url('https://fonts.googleapis.com/css2?family=Lobster&display=swap');
</style>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<style><%@include file="/WEB-INF/pages/header.jsp"%></style>

<style><%@include file="/WEB-INF/pages/header.jsp"%></style>
<<<<<<< HEAD
=======


</head>
<body class="bodyAccueil">
<h1 class="titre">Liste des enchères</h1>
<div class="flex">
	<div>
		<form action="">
			<label >Filtres : </label> <input class="inputAccueil" placeholder="Entrez les filtres souhaitez" /> <br>
			<label>Catégories :</label>
			<SELECT name="nom" size="1">
				<OPTION>Informatique</OPTION>
				<OPTION>Ameublement</OPTION>
				<OPTION>Vetement</OPTION>
				<OPTION>Sport</OPTION>
				<OPTION>Loisir</OPTION>
			</SELECT>
		</form>
	</div>
	<div>
	<button class="button" >Rechercher</button>

	</div>
</div>

>>>>>>> branch 'main' of https://github.com/ThomasDoue/ENI-JAVA-EE-GRP-8
</body>
<footer>
<form>
	 <input type="button" value="Rafraichir." onClick="refresh">
	 <input type="button" value="Précedent." onclick="history.back()">
</form>
</footer>
	</body>
</html>