<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.vianna.webzoo.model.Funcionario" %>
<jsp:include page="/includes/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">

<%
    Funcionario f = (Funcionario) request.getAttribute("funcionario");
%>

<main class="admin-container">
    <h2>Editar Funcionário</h2>

    <form action="${pageContext.request.contextPath}/web?ac=atualizarFuncionario" method="post" class="formulario-funcionario">
        <input type="hidden" name="id" value="<%= f.getId() %>">

        <label>Nome:</label>
        <input type="text" name="nome" value="<%= f.getNome() %>" required>

        <label>CPF:</label>
        <input type="text" name="cpf" value="<%= f.getCpf() %>" required>

        <label>Email:</label>
        <input type="email" name="email" value="<%= f.getEmail() %>" required>

        <label>Telefone:</label>
        <input type="text" name="telefone" value="<%= f.getTelefone() %>" required>

        <label>Cargo:</label>
        <input type="text" name="cargo" value="<%= f.getCargo() %>" required>

        <label>Data de Contratação:</label>
        <input type="date" name="dataContratacao" value="<%= f.getDataContratacao() %>" required>

        <label>Turno:</label>
        <input type="text" name="turno" value="<%= f.getTurno() %>" required>

        <label>Senha:</label>
        <input type="password" name="senha" value="<%= f.getSenha() %>" required>

        <button type="submit" class="cta-button">Salvar Alterações</button>
    </form>
</main>

<jsp:include page="/includes/footer.jsp" />
