<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="br.vianna.webzoo.model.Animal" %>

<jsp:include page="/includes/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/todosAnimais.css">



<main class="todos-animais-container" style="background-color: white; padding: 2rem;">
    <h2>🌍 Todos os Animais</h2>

    <div class="filtro-container">
        <label for="filtroTipo">Filtrar por tipo:</label>
        <select id="filtroTipo">
            <option value="todos">Todos</option>
            <option value="Mamífero">Mamífero</option>
            <option value="Ave">Ave</option>
            <option value="Réptil">Réptil</option>
            <option value="Anfíbio">Anfíbio</option>
            <option value="Peixe">Peixe</option>
            <option value="Inseto">Inseto</option>
        </select>
    </div>

    <div class="animal-cards">
        <%
            List<Animal> lista = (List<Animal>) request.getAttribute("listaAnimais");
            if (lista != null) {
                for (Animal a : lista) {
        %>
        <div class="animal-card" data-tipo="<%= a.getTipo() %>" onclick="this.classList.toggle('expand')">
            <div class="card-header">
                <h4><%= a.getNome() %> - <%= a.getEspecie() %></h4>
                <p><strong>Recinto:</strong> <%= a.getRecinto() %></p>
            </div>
            <div class="card-body">
                <p><strong>Nome Científico:</strong> <i><%= a.getNomeCientifico() %></i></p>
                <p><strong>Habitat:</strong> <%= a.getHabitat() %></p>
                <p><strong>Tipo:</strong> <%= a.getTipo() %></p>
                <p><strong>Descrição:</strong> <%= a.getDescricao() %></p>
                <p>
                    <strong>Exibição:</strong>
                    <% if (a.isExibicao()) { %>
                    <span style="color: green;">Está em Exposição</span>
                    <% } else { %>
                    <span style="color: red;">Não está disponível</span>
                    <% } %>
                </p>
            </div>
        </div>
        <%
                }
            }
        %>
    </div>
</main>

<script src="${pageContext.request.contextPath}/assets/js/filtroAnimais.js"></script>
<jsp:include page="/includes/footer.jsp" />
