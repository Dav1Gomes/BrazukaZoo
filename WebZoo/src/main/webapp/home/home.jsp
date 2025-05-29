<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.vianna.webzoo.model.Usuario" %>
<%@ page import="br.vianna.webzoo.model.Funcionario" %>
<%@ page import="br.vianna.webzoo.model.Animal" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>BrazukaZoo - Bem-vindo</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/alerts.css">
</head>
<body>

<%@ include file="/includes/header.jsp" %>

<main>
    <section class="hero" style="position: relative; overflow: hidden;">
        <img src="<%= request.getContextPath() %>/assets/img/capa-zoologico.png"
             alt="Capa BrazukaZoo"
             style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; object-fit: cover; z-index: 0; opacity: 0.75;" />

        <%
            objLogado = session.getAttribute("usuarioLogado");
            nomeUsuario = "Visitante";
            Integer idVisitante = null;

            if (objLogado instanceof Usuario) {
                Usuario usuarioLogado = (Usuario) objLogado;
                nomeUsuario = usuarioLogado.getNome();
                idVisitante = usuarioLogado.getId();
            } else if (objLogado instanceof Funcionario) {
                Funcionario funcionarioLogado = (Funcionario) objLogado;
                nomeUsuario = funcionarioLogado.getNome();
            }
        %>

        <div style="position: relative; z-index: 1; text-align: center; padding: 4rem 2rem; color: white; text-shadow: 1px 1px 4px rgba(0,0,0,0.6);">
            <% if (objLogado != null) { %>
            <h2>Bem-vindo(a), <%= nomeUsuario %>!</h2>
            <p>Estamos felizes em te receber no BrazukaZoo!</p>
            <% } else { %>
            <h2>Bem-vindo ao BrazukaZoo!</h2>
            <p>Conheça nossos animais, agende sua visita e descubra a natureza de um jeito divertido e consciente.</p>
            <% } %>
        </div>
    </section>

    <section class="animal-preview">
        <h3>🐾 Conheça Nossos Animais</h3>
        <div class="animal-cards">
            <%
                List<Animal> animaisDestaque = (List<Animal>) request.getAttribute("animaisDestaque");
                if (animaisDestaque != null) {
                    for (Animal a : animaisDestaque) {
            %>
            <div class="animal-card" onclick="this.classList.toggle('expand')">
                <div class="card-header">
                    <h4><%= a.getNome() %> - <%= a.getEspecie() %></h4>
                    <p><strong>Recinto:</strong> <%= a.getRecinto() %></p>
                </div>
                <div class="card-body">
                    <p><strong>Nome Científico:</strong> <i><%= a.getNomeCientifico() %></i></p>
                    <p><strong>Habitat:</strong> <%= a.getHabitat() %></p>
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
            <%  }
            } else { %>
            <p style="color: red;">ERRO: Lista de animaisDestaque está null</p>
            <% } %>
        </div>

        <div style="margin-top: 1.5rem;">
            <a class="cta-button" href="<%= request.getContextPath() %>/web?ac=viewTodosAnimais">Ver Todos os Animais</a>
        </div>
    </section>

    <section class="visita-chamada" style="background-color: white; padding: 2rem;">
        <h3>📆 Agende sua Visita</h3>
        <p>Venha viver uma experiência inesquecível com nossos animais e espaços verdes.</p>
        <% if (objLogado == null) { %>
        <a class="cta-button" href="<%= request.getContextPath() %>/auth/login.jsp">Fazer Agendamento</a>
        <% } else if (idVisitante != null) { %>
        <a class="cta-button" href="<%= request.getContextPath() %>/visitante/agendarVisita.jsp">Agendar Agora</a>
        <a class="cta-button" href="<%= request.getContextPath() %>/web?ac=listarVisitas&idVisitante=<%= idVisitante %>">Minhas Visitas</a>
        <% } %>
    </section>

    <section class="sobre" style="background-color: white; padding: 2rem;">
        <h3>🌱 Sobre o BrazukaZoo</h3>
        <p>Somos um zoológico moderno com foco na preservação ambiental, bem-estar animal e educação ecológica. Atuamos também com pesquisas científicas e programas de reintegração de espécies.</p>
    </section>
</main>

<%@ include file="/includes/footer.jsp" %>
<script src="<%= request.getContextPath() %>/assets/js/mensagens.js"></script>

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

</body>
</html>
