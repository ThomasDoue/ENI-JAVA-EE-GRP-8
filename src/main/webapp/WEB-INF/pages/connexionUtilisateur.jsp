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
	<form action="<%=request.getContextPath()%>/ConnexionUtilisateurServlet" method="post">
		<input type="hidden" name="idPizza" value="${empty utilisateur?'':utilisateur.id }"/>
		<label for="idIdentifier">Nom : </label>
		<input type="text" name="identifier" id="idIdentifier" value=""/><br />
		<label for="idPassword">Mot de passe : </label>
		<input type="text" name="password" id="idPassword" value=""/><br/>		
		<button>Log In</button>
	</form>


</body>
</html>