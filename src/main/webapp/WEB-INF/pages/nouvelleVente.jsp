<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/nouvelleVente" method="post">

	
	

	<div>
		<label for="article">Article :</label> 
		<input type="text" id="article" name="article" class="form-control" value="${article}" required minlenght="1" maxlength="4" > 
		
		
		<label for="article">Description :</label> 
		<textarea id="story" name="description" rows="5" cols="33" value="${description}">description du produit </textarea>
	</div>
	<div >
		<label for="telephone">Teléphone :</label> <input type="text"
			id="telephone" name="telephone" class="form-control"
			value="${telephone}" required minlenght="1"> <label
			for="rue">Rue :</label> <input type="text" id="rue" name="rue"
			class="form-control" value="${rue}" required minlenght="1"><br />
	</div>
	<div >
		<label for="codePostal">Code Postal :</label> <input type="text"
			id="codePostal" name="codePostal" class="form-control"
			value="${codePostal}" required minlenght="1"> <label
			for="ville">Ville :</label> <input type="text" id="ville"
			name="ville" class="form-control" value="${ville}" required
			minlenght="1"><br />
	</div>


</body>
</html>