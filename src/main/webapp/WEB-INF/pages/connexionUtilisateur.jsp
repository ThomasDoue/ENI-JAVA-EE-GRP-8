<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1 class="titreAccueil">Connexion</h1>

	<% if(request.getAttribute("errorLogin") != null) {%>
	<p><%=request.getAttribute("errorLogin")%>
	<%}%>
	
	<form action="ConnexionUtilisateurServlet" method="post">
		<label for="idIdentifier">Nom : </label>
		<input type="text" name="identifier" id="idIdentifier" value=""/><br />
		<label for="idPassword">Mot de passe : </label>
		<input type="text" name="password" id="idPassword" value=""/><br/>		
		<button type="submit" name="LogIn">Log In</button>
		<button type="button" name="redirectCreateAnAccount">Create an Account</button>
	</form>
</body>
</html>