<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark bg-gradient">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">FINTECH</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
		        <a class="nav-link" aria-current="page" href="login.jsp">Home</a>
	        </li>
	        <li class="nav-item">
		        <a class="nav-link" aria-current="page" href="conta?acao=listar">Contas</a>
	        </li>
	        <li class="nav-item">
		        <a class="nav-link" href="objetivo?acao=listar">Objetivos</a>
	        </li>
        </ul>
		<c:if test="${empty user}">
			<span class="navbar-text text-danger" style="margin-right: 10px">${erro}</span>
			<p class="navbar-text mr-2">Olá visitante!</p>
		</c:if>
		<c:if test="${not empty user}">
			<c:url value="login" var="link">
				<c:param name="acao" value="logoff"/>
			</c:url>
			<span class="navbar-text mr-2">
				Olá, ${nome}!
				<a href="${link}" class="btn btn-outline-primary my-2 my-sm-0">Sair</a>
			</span>
		</c:if>
		<c:if test="${not empty user}">
			<c:url value="login" var="link">
				<c:param name="acao" value="abrir-form-edicao"/>
				<c:param name="usuario" value="${user}"/>
			</c:url>
			<span class="navbar-text mx-2">
				<a href="${link}" class="btn btn-outline-success my-2 my-sm-0">Alterar dados</a>
			</span>
		</c:if>
		<c:if test="${not empty user}">
			<button type="button" class="btn btn-outline-danger my-2 my-sm-0" data-bs-toggle="modal" data-bs-target="#excluirUsuario" onclick="cdExcluir.value = ${user}">
				Excluir usuário
			</button>
		</c:if>
    </div>
  </div>
</nav>

<!-- Modal -->
<div class="modal fade" id="excluirUsuario" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="staticBackdropLabel">Confirmação</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        Deseja realmente excluir seu usuário?
      </div>
      <div class="modal-body">
        Todas as contas, transações e objetivos vinculados também serão excluídos.
      </div>
      <div class="modal-footer">
      	<form action="login" method="post">
      		<input type="hidden" name="acao" value="excluir">
      		<input type="hidden" name="usuario" id="cdExcluir">
	        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
	        <button type="submit" class="btn btn-danger">Excluir</button>
        </form>
      </div>
    </div>
  </div>
</div>