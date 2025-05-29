<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="br.vianna.webzoo.model.Visita" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    List<Visita> visitas = (List<Visita>) request.getAttribute("listaVisitas");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Minhas Visitas - BrazukaZoo</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/minhas-visitas.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/alerts.css">
</head>
<body>

<jsp:include page="/includes/header.jsp" />

<main class="minhas-visitas-container">
    <h2>Minhas Visitas</h2>

    <c:if test="${not empty listaVisitas}">
        <table class="tabela-visitas">
            <thead>
            <tr>
                <th>Data</th>
                <th>Horário</th>
                <th>Pessoas</th>
                <th>Observações</th>
                <th>Status</th>
                <th>Ações</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="v" items="${listaVisitas}">
                <tr>
                    <td><fmt:formatDate value="${v.dataVisita}" pattern="dd/MM/yyyy"/></td>
                    <td><fmt:formatDate value="${v.horario}" pattern="HH:mm"/></td>
                    <td>${v.quantidadePessoas}</td>
                    <td><c:out value="${v.observacoes != null ? v.observacoes : '-'}"/></td>
                    <td>
                        <c:choose>
                            <c:when test="${v.status eq 'Cancelada'}">
                                <span style="color: red; font-weight: bold;">Cancelada</span>
                            </c:when>
                            <c:otherwise>
                                <span style="color: green; font-weight: bold;">Ativa</span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:if test="${v.status eq 'Ativa'}">
                            <form action="web" method="post" style="display:inline;">
                                <input type="hidden" name="ac" value="cancelarVisita"/>
                                <input type="hidden" name="id" value="${v.id}"/>
                                <input type="hidden" name="idVisitante" value="${sessionScope.usuarioLogado.id}"/>
                                <button type="submit" class="cta-button" onclick="return confirm('Deseja realmente cancelar esta visita?');">Cancelar</button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${empty listaVisitas}">
        <p>Você ainda não tem visitas agendadas.</p>
    </c:if>
</main>

<%
    String msg = (String) session.getAttribute("mensagem");
    if (msg != null) {
%>
<div class="alert-success">
    <%= msg %>
</div>
<%
        session.removeAttribute("mensagem");
    }
%>

<jsp:include page="/includes/footer.jsp" />
<script src="<%= request.getContextPath() %>/assets/js/mensagens.js"></script>
</body>
</html>
