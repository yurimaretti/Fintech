<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="ISO-8859-1">
		<title>Fintech - Cadastro Categoria</title>
		<%@include file="head.jsp" %>	
	</head>
	<body class="bg-light">
		<%@include file="header.jsp" %>	
		<div class="p-5 text-center">
			<h1 class="mb-3">Nova Categoria</h1>
		</div>
		<c:if test="${not empty msg}">
			<div class="alert alert-success">${msg}</div>
		</c:if>
		<c:if test="${not empty erro}">
			<div class="alert alert-danger">${erro}</div>
		</c:if>
		<div class="container d-flex justify-content-center">
			<form class="my-2 col-md-6" action="categoria" method="post">
				<input type="hidden" value="cadastrar" name="acao">
				<div class="form-group mb-3">
					<label for="id_categoria" class="form-label">Categoria</label>
					<input type="text" name="categoria" class="form-control form-outline" id="id_categoria" placeholder="Categoria..." maxlength="30" required>
				</div>
				<button type="submit" class="btn btn-primary d-inline">Cadastrar</button>
				<a href="categoria?acao=listar">
					<button type="button" class="btn btn-secondary">Voltar</button>
				</a>
			</form>			
		</div>
		<%@include file="footer.jsp" %>	
	</body>
</html>