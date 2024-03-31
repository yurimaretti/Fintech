<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="ISO-8859-1">
		<title>Fintech - Login</title>
		<%@include file="head.jsp" %>	
	</head>
	<body class="bg-light">
		<%@include file="header.jsp" %>	
		<div class="p-5 text-center">
			<h1 class="mb-3 display-1">FINTECH</h1>
			<h3>Cuidando da sua segurança financeira</h3>
		</div>
		<c:if test="${not empty msg}">
			<div class="alert alert-success">${msg}</div>
		</c:if>
		<c:if test="${not empty erro}">
			<div class="alert alert-danger">${erro}</div>
		</c:if>
		<c:if test="${empty user}">
			<div class="container d-flex justify-content-center">
				<form class="my-2" action="login" method="post">
					<input type="hidden" value="validar" name="acao">
					<div class="form-group mb-3">
						<label for="id_email" class="form-label">E-mail</label>
						<input type="email" name="email" class="form-control" id="id_email" placeholder="E-mail..." maxlength="64" required>
					</div>
					<div class="form-group mb-3">
						<label for="id_senha" class="form-label">Senha</label>
						<input type="password" name="senha" class="form-control" id="id_senha" placeholder="Senha..." maxlength="7" required>
					</div>
					<button type="submit" class="btn btn-primary d-inline">Entrar</button>
					<p class="d-inline ml-3">Não tem cadastro? <a href="cadastro_usuario.jsp">Cadastre-se aqui</a></p>
				</form>
			</div>
		</c:if>
		<c:if test="${not empty user}">
			<div class="container d-block justify-content-center text-center">
				<h1 class="my-5">BEM VINDO!</h1>
				<h4 class="my-5">Comece adicionando uma conta ou um objetivo na barra de menu.</h4>
			</div>
		</c:if>	
		<%@include file="footer.jsp" %>	
	</body>
</html>