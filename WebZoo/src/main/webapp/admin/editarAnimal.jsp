<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, br.vianna.webzoo.model.Animal" %>

<jsp:include page="/includes/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">

<main class="admin-container">
    <h2>Editar Animais Registrados</h2>

    <%
        List<Animal> lista = (List<Animal>) request.getAttribute("listaAnimais");
        if (lista != null && !lista.isEmpty()) {
    %>
    <table style="width:100%; margin-top: 1rem; border-collapse: collapse;">
        <thead>
        <tr style="background-color: #089746; color: white;">
            <th>Nome</th>
            <th>Espécie</th>
            <th>Habitat</th>
            <th>Idade</th>
            <th>Recinto</th>
            <th>Exibição</th>
            <th>Saúde</th>
            <th>Ações</th>
        </tr>
        </thead>
        <tbody>
        <% for (Animal a : lista) { %>
        <tr style="border-bottom: 1px solid #ccc;">
            <td><%= a.getNome() %></td>
            <td><%= a.getEspecie() %></td>
            <td><%= a.getHabitat() %></td>
            <td><%= a.getIdade() %> anos</td>
            <td><%= a.getRecinto() %></td>
            <td><%= a.isExibicao() ? "Sim" : "Não" %></td>
            <td><%= a.getEstadoSaude() %></td>
            <td>
                <a href="<%= request.getContextPath() %>/web?ac=formEditarAnimal&id=<%= a.getId() %>" class="cta-button">Editar</a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } else { %>
    <p>Nenhum animal cadastrado.</p>
    <% } %>

    <%
        String msgOk = (String) session.getAttribute("mensagemSucesso");
        if (msgOk != null) {
    %>
    <div class="alert-success"><%= msgOk %></div>
    <%
            session.removeAttribute("mensagemSucesso");
        }

        String msgErro = (String) session.getAttribute("mensagemErro");
        if (msgErro != null) {
    %>
    <div class="alert-success" style="background-color:#f8d7da; color:#721c24; border-left: 6px solid #f44336;">
        <%= msgErro %>
    </div>
    <%
            session.removeAttribute("mensagemErro");
        }
    %>


</main>

<jsp:include page="/includes/footer.jsp" />
