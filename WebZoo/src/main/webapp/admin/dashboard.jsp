<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="br.vianna.webzoo.model.*" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Admin - BrazukaZoo</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/admin.css">
</head>
<body>
<jsp:include page="/includes/header.jsp" />

<main class="admin-container">
    <h2 style="text-align: center; color: #372D85;">Painel Administrativo do BrazukaZoo</h2>

    <div class="admin-sections">

        <div class="admin-section">
            <h3>🐾 Animais</h3>
            <a href="${pageContext.request.contextPath}/web?ac=viewCadastroAnimal" class="cta-button">Cadastrar Animal</a>
            <a href="${pageContext.request.contextPath}/web?ac=viewEditarAnimal" class="cta-button">Animais Registrados</a>
            <a href="${pageContext.request.contextPath}/web?ac=viewHistoricoAlimentacaoAdmin" class="cta-button">Histórico de Alimentação</a>
        </div>

        <div class="admin-section">
            <h3>🧑‍🔧 Funcionários</h3>
            <a href="${pageContext.request.contextPath}/admin/cadastroFuncionario.jsp" class="cta-button">Cadastrar Funcionário</a>
            <a href="${pageContext.request.contextPath}/web?ac=viewFuncionarios" class="cta-button">Ver Funcionários</a>
            <a href="${pageContext.request.contextPath}/web?ac=viewHistoricoTransferenciasAdmin" class="cta-button">Histórico de Transferências</a>
        </div>

        <div class="admin-section">
            <h3>📦 Fornecedores</h3>
            <a href="${pageContext.request.contextPath}/admin/cadastrarFornecedor.jsp" class="cta-button">Cadastrar Fornecedor</a>
            <a href="${pageContext.request.contextPath}/web?ac=listarFornecedores" class="cta-button">Lista Fornecedores</a>
        </div>

        <div class="admin-section">
            <h3>📅 Visitas</h3>
            <a href="${pageContext.request.contextPath}/web?ac=viewVisitasAdmin" class="cta-button">Visitas Agendadas</a>
        </div>

    </div>
</main>

<jsp:include page="/includes/footer.jsp" />
</body>
</html>
