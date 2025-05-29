<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Erro - BrazukaZoo</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
</head>
<body>

<jsp:include page="/includes/header.jsp" />

<main class="visita-container">
    <h2 style="color: #d9534f;">Ops! Algo deu errado</h2>
    <p style="margin-bottom: 1.5rem; font-size: 1.1rem;">
        <strong>Mensagem:</strong>
        <span style="color: #444;">
            ${msg != null ? msg : "Erro inesperado. Tente novamente mais tarde."}
        </span>
    </p>

    <a href="<%= request.getContextPath() %>/index.jsp" class="cta-button" style="background-color:#2783FF;">
        Voltar para a Página Inicial
    </a>
</main>

<jsp:include page="/includes/footer.jsp" />

</body>
</html>
