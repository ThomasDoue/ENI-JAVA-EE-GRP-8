<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<style><%@include file="/WEB-INF/pages/header.jsp"%></style>
<h1 class="titre">Liste des enchères</h1>
</head>
<body>
<div class="flex">
<div>
<form action="">
		<label>Filtres : </label> <input /> <br>
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
<button>Rechercher</button>

</div>
</div>

	</body>
</html>