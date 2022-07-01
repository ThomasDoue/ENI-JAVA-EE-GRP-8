<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/NouvelleVente"
		method="post"></form>

	<div>
		<label for="pseudo">Pseudo : </label> <input type="text" id="pseudo"
			name="pseudo" class="form-control" value="${pseudo}" required
			minlenght="1"> <label for="nom">Nom :</label> <input
			type="text" id="nom" name="nom" class="form-control" value="${nom}"
			required minlenght="1"><br />
	</div>

	<div class="form-group">
		<label for="prenom">Prenom :</label> <input type="text" id="prenom"
			name="prenom" class="form-control" value="${prenom}" required
			minlenght="1"> <label for="email">Email :</label> <input
			type="text" id="email" name="email" class="form-control"
			value="${email}" required minlenght="1"><br />
	</div>
	<div class="form-group">
		<label for="telephone">Teléphone :</label> <input type="text"
			id="telephone" name="telephone" class="form-control"
			value="${telephone}" required minlenght="1"> <label
			for="rue">Rue :</label> <input type="text" id="rue" name="rue"
			class="form-control" value="${rue}" required minlenght="1"><br />
	</div>
	<div class="form-group">
		<label for="codePostal">Code Postal :</label> <input type="text"
			id="codePostal" name="codePostal" class="form-control"
			value="${codePostal}" required minlenght="1"> <label
			for="ville">Ville :</label> <input type="text" id="ville"
			name="ville" class="form-control" value="${ville}" required
			minlenght="1"><br />
	</div>


</body>
</html>