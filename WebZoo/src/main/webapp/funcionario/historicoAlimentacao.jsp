<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, br.vianna.webzoo.model.Alimentacao" %>

<jsp:include page="/includes/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">

<main class="admin-container">
    <h2>Histórico de Alimentações</h2>

    <%
        List<Alimentacao> lista = (List<Alimentacao>) request.getAttribute("listaAlimentacoes");
        if (lista != null && !lista.isEmpty()) {
    %>
    <table class="tabela-animais">
        <thead>
        <tr>
            <th>Animal</th>
            <th>Tipo de Alimento</th>
            <th>Data e Hora</th>
        </tr>
        </thead>
        <tbody>
        <%
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm");
            for (Alimentacao a : lista) {
        %>
        <tr>
            <td><%= a.getAnimal().getNome() %></td>
            <td><%= a.getTipoAlimento() %></td>
            <td><%= sdf.format(a.getDataHora()) %></td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } else { %>
    <p>Não há registros de alimentação.</p>
    <% } %>
</main>

<jsp:include page="/includes/footer.jsp" />
