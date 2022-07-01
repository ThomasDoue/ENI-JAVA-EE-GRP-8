<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<title>Insert title here</title>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<style><%@include file="/WEB-INF/pages/header.jsp"%></style>
<style>@import url('https://fonts.googleapis.com/css2?family=Lobster&display=swap');</style>
</head>
<body>
<div class="container d-flex justify-content-center ">
		<div class="row d-flex flex-row justify-content-center">

		<!-- 1RE LIGNE: SE CONNECTER -->
			<div class="col justify-content-center">
				<% if(request.getAttribute("errorLogin") != null) {%>
				<h3 class="erreur"><%=request.getAttribute("errorLogin")%></h3>
				<%}%>
				<!--  float:right; margin-top:auto; -->
				</div>
			</div>
		</div>


	<div class="container d-flex justify-content-center p-4 px-6" style=" height:25vh; width:100vw">
		<div class="row d-flex flex-row justify-content-center">

		<!-- 1RE LIGNE: SE CONNECTER -->
			
				
				<form action="ConnexionUtilisateurServlet" method="post" >
					<label for="idIdentifier">Identifiant: </label>
					<input class="input form-control-sm mb-4 ms-4 w-10" type="text" name="identifier" id="idIdentifier" value=""/><br />
					<label for="idPassword">Mot de passe: </label>
					<input class="input form-control-sm mb-3 w-10" type="text" name="password" id="idPassword" value=""/><br/>		
					<button type="submit" class="button">Connexion</button>
				</form>
				<!-- 2EME LIGNE: CREER UN COMPTE -->
				
				<form action="ConnexionUtilisateurServlet" method="get">
					<a href="ConnexionUtilisateurServlet?action=createAccount">
						<input class="button" type="button" value="Créer un compte">
					</a>
				</form>
			</div>
		</div>


<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>

</body>
</html>