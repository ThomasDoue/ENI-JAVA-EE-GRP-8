<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<title>Insert title here</title>


  
</head>
<body>

<div class="container">
 <div class="bg-light w-100 p-3" style="height: max-height;"> 
		<div class="row">
			<div class="">
					
				<!--  		  <div class="h-25 d-inline-block" style="width: 120px; background-color: rgba(0,0,255,.1)">Height 25%</div>
						  <div class="h-50 d-inline-block" style="width: 120px; background-color: rgba(0,0,255,.1)">Height 50%</div>
						  <div class="h-75 d-inline-block" style="width: 120px; background-color: rgba(0,0,255,.1)">Height 75%</div>
						  <div class="h-100 d-inline-block" style="width: 120px; background-color: rgba(0,0,255,.1)">Height 100%</div>
				-->		
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
					</form>
				
			</div>
					<div class="">
					<form action="ConnexionUtilisateurServlet" method="get">
						<a href="ConnexionUtilisateurServlet?action=createAccount">
						<input type="button" value="Sign in"></a>
					</form>
			</div>
		</div>
	</div>
</div>

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>

</body>
</html>