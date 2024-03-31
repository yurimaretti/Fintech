<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
	<head>
		<meta charset="ISO-8859-1">
		<title>Fintech - Cadastro Transação</title>
		<%@include file="head.jsp" %>	
	</head>
	<body class="bg-light">
		<%@include file="header.jsp" %>	
		<div class="p-5 text-center">
			<h1 class="mb-3">Nova Transação</h1>
		</div>
		<c:if test="${not empty msg}">
			<div class="alert alert-success">${msg}</div>
		</c:if>
		<c:if test="${not empty erro}">
			<div class="alert alert-danger">${erro}</div>
		</c:if>
		<div class="container d-flex justify-content-center">
			<form class="my-2 col-md-6" action="transacao" method="post">
				<input type="hidden" value="cadastrar" name="acao">
				<div class="form-group mb-3">
					<label for="id_valor">Valor</label>
					<input type="text" name="valor" id="id_valor" class="form-control" placeholder="Valor..." required>
				</div>
				<div class="form-group mb-3">
					<label for="id_data">Data</label>
					<input type="text" name="data" id="id_data" class="form-control" placeholder="DD/MM/AAAA" required>
				</div>
				<div class="form-group mb-3">
					<label for="id_observacoes" class="form-label">Observações</label>
					<input type="text" name="observacoes" class="form-control" id="id_observacoes" placeholder="Descrição..." maxlength="200" required>
				</div>
				<div class="form-group mb-3">
					<label for="id_categoria" class="form-label">Categoria</label>
					<select name="categoria" id="id_categoria" class="form-control">
						<option class="italico">Categoria...</option>
						<c:forEach items="${categorias}" var="c">
							<option value="${c.codigo}">${c.nome}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group mb-3">
					<label for="id_conta" class="form-label">Conta</label>
					<select name="conta" id="id_conta" class="form-control">
						<option class="italico">Conta...</option>
						<c:forEach items="${contas}" var="cta">
							<option value="${cta.codigo}">${cta.nome}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group mb-3">
					<label for="id_tipo" class="form-label">Tipo</label>
					<select name="tipo" id="id_tipo" class="form-control">
						<option class="italico">Tipo...</option>
						<option>Despesa</option>
						<option>Receita</option>
					</select>
				</div>
				<button type="submit" class="btn btn-primary d-inline">Cadastrar</button>
				<a href="conta?acao=listar">
					<button type="button" class="btn btn-secondary">Voltar</button>
				</a>
			</form>			
		</div>
		<%@include file="footer.jsp" %>	
	</body>
</html>