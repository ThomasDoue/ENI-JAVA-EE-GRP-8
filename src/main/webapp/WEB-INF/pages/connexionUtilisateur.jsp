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
 
<header>
	<div class="contenair" class="w-100 p-3" style="background-color: #e8f4ea;">
			<div class="row">
				<div class="col">
					<h1 style="  padding:1em;text-indent:2em; color:#b8d8be">ENI-encheres</h1>
				</div>
			</div>
		</div>
</header>


	<div class="container bg-light px-4" style="height:100vh; width:100vw">
		<div class="row gx-5" style="text-align:center;">



		
			<!-- 1RE LIGNE: SE CONNECTER -->
			<div class="col">
				<h1 class="titreAccueil">Connexion</h1>
				<% if(request.getAttribute("errorLogin") != null) {%>
				<p><%=request.getAttribute("errorLogin")%>
				<%}%>
				<!--  float:right; margin-top:auto; -->
				<form action="ConnexionUtilisateurServlet" method="post" >
					<label for="idIdentifier" style="width:5em; float:center; margin:1em;">Nom : </label>
					<input class=" form-control-sm mb-3 w-10" type="text" name="identifier" id="idIdentifier" value="" style="margin:3em;"/><br />
					<label for="idPassword" style="width:5em; float:center; margin:1em;">Mot de passe : </label>
					<input class="form-control-sm mb-3 w-10"  type="text" name="password" id="idPassword" value=""style="margin:3em;"/><br/>		
					<button type="submit" class="btn btn-outline-primary" name="LogIn">Log In</button>
				</form>
				
			</div>
		</div>
			
		<!-- 2EME LIGNE: CREER UN COMPTE -->
		<div class="row gx-5">
			<form action="ConnexionUtilisateurServlet" method="get">
				<a href="ConnexionUtilisateurServlet?action=createAccount">
				<input class="btn btn-secondary" type="button" value="Sign in" style="padding:5% 10%;"></a>
			</form>
			
		</div>
	
</div>

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>

</body>
</html>