<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.LocalDate" %>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Agendar Visita - BrazukaZoo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/auth.css">

</head>
<body>

<jsp:include page="/includes/header.jsp" />

<main class="visita-container">
    <h2>Agendar Visita</h2>

    <c:if test="${not empty mensagemSucesso}">
        <div class="alert-success">${mensagemSucesso}</div>
    </c:if>
    <c:if test="${not empty mensagemErro}">
        <div class="alert-success" style="background-color:#f8d7da; color:#721c24; border-left: 6px solid #f44336;">
                ${mensagemErro}
        </div>
    </c:if>

    <form action="${pageContext.request.contextPath}/visita" method="post">
        <input type="hidden" name="ac" value="agendarVisita" />
        <label for="dataVisita">Data da Visita:</label>
        <input type="date" name="dataVisita" id="dataVisita" required min="<%= LocalDate.now() %>">

        <label for="horario">Horário:</label>
        <input type="time" name="horario" id="horario" required>

        <label for="quantidadePessoas">Quantidade de Pessoas:</label>
        <input type="number" name="quantidadePessoas" id="quantidadePessoas" required min="1" max="20">

        <label for="observacoes">Observações:</label>
        <textarea name="observacoes" id="observacoes" rows="4" placeholder="Informações adicionais..."></textarea>

        <button type="submit">Agendar Visita</button>
    </form>
</main>

<jsp:include page="/includes/footer.jsp" />
<script src="${pageContext.request.contextPath}/assets/js/mensagens.js"></script>
</body>
</html>
