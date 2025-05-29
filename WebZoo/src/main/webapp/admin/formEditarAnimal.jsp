<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.vianna.webzoo.model.Animal" %>

<jsp:include page="/includes/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">

<main class="admin-container">
    <h2>Editar Informações do Animal</h2>

    <%
        Animal a = (Animal) request.getAttribute("animal");
        if (a != null) {
    %>
    <form action="${pageContext.request.contextPath}/web?ac=atualizarAnimal" method="post" class="formulario-admin">
        <input type="hidden" name="id" value="<%= a.getId() %>"/>

        <label for="nome">Nome:</label>
        <input type="text" name="nome" id="nome" value="<%= a.getNome() %>" required>

        <label for="especie">Espécie:</label>
        <input type="text" name="especie" id="especie" value="<%= a.getEspecie() %>" required>

        <label for="nome_cientifico">Nome Científico:</label>
        <input type="text" name="nome_cientifico" id="nome_cientifico" value="<%= a.getNomeCientifico() %>" required>

        <label for="habitat">Habitat:</label>
        <input type="text" name="habitat" id="habitat" value="<%= a.getHabitat() %>" required>

        <label for="idade">Idade:</label>
        <input type="number" name="idade" id="idade" value="<%= a.getIdade() %>" min="0" required>

        <label for="data_chegada">Data de Chegada:</label>
        <input type="date" name="data_chegada" id="data_chegada" value="<%= a.getDataChegada() %>" required>

        <label for="estado_saude">Estado de Saúde:</label>
        <input type="text" name="estado_saude" id="estado_saude" value="<%= a.getEstadoSaude() %>" required>

        <label for="recinto">Recinto:</label>
        <input type="text" name="recinto" id="recinto" value="<%= a.getRecinto() %>" required>

        <label for="tipo">Tipo:</label>
        <input type="text" name="tipo" id="tipo" value="<%= a.getTipo() %>" required>

        <label for="exibicao">Pode entrar em exibição?</label>
        <select name="exibicao" id="exibicao">
            <option value="true" <%= a.isExibicao() ? "selected" : "" %>>Sim</option>
            <option value="false" <%= !a.isExibicao() ? "selected" : "" %>>Não</option>
        </select>

        <label for="descricao">Descrição:</label>
        <textarea name="descricao" id="descricao" rows="4" required><%= a.getDescricao() %></textarea>

        <button type="submit">Salvar Alterações</button>
    </form>
    <% } else { %>
    <p style="color: red; text-align: center;">Animal não encontrado.</p>
    <% } %>
</main>

<jsp:include page="/includes/footer.jsp" />
