<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, br.vianna.webzoo.model.Recinto" %>

<%
    Map<String, List<Recinto>> recintosPorTipo = (Map<String, List<Recinto>>) request.getAttribute("mapaRecintos");
    if (recintosPorTipo == null) {
        response.sendRedirect(request.getContextPath() + "/web?ac=viewCadastroAnimal");
        return;
    }
%>

<jsp:include page="/includes/header.jsp" />

<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/cadastroAnimal.css">

<main class="pagina-cadastro">
    <div class="formulario-container">
        <h2>Cadastro de Animal</h2>

        <form action="${pageContext.request.contextPath}/web?ac=cadastrarAnimal" method="post" class="formulario-admin">
                <div class="form-row">
                    <div class="form-group">
                        <label for="nome">Nome:</label>
                        <input type="text" name="nome" id="nome" required>
                    </div>
                    <div class="form-group">
                        <label for="especie">Espécie:</label>
                        <input type="text" name="especie" id="especie" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="nome_cientifico">Nome Científico:</label>
                        <input type="text" name="nome_cientifico" id="nome_cientifico" required>
                    </div>
                    <div class="form-group">
                        <label for="habitat">Habitat:</label>
                        <input type="text" name="habitat" id="habitat" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="idade">Idade:</label>
                        <input type="number" name="idade" id="idade" min="0" required>
                    </div>
                    <div class="form-group">
                        <label for="data_chegada">Data de Chegada:</label>
                        <input type="date" name="data_chegada" id="data_chegada" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="estado_saude">Estado de Saúde:</label>
                        <select name="estado_saude" id="estado_saude">
                            <option value="Saudável">Saudável</option>
                            <option value="Doente">Doente</option>
                            <option value="Em observação">Em observação</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="exibicao">Pode entrar em exibição?</label>
                        <select name="exibicao" id="exibicao">
                            <option value="true">Sim</option>
                            <option value="false">Não</option>
                        </select>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="tipo">Tipo:</label>
                        <select name="tipo" id="tipo" required>
                            <option value="">Selecione o tipo</option>
                            <option value="Mamífero">Mamífero</option>
                            <option value="Ave">Ave</option>
                            <option value="Réptil">Réptil</option>
                            <option value="Anfíbio">Anfíbio</option>
                            <option value="Peixe">Peixe</option>
                            <option value="Inseto">Inseto</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="recinto">Recinto:</label>
                        <select name="recinto" id="recinto" required>
                            <option value="">Selecione um tipo primeiro</option>
                        </select>
                    </div>
                </div>

                <div class="form-row" style="flex-direction: column;">
                    <label for="descricao">Descrição:</label>
                    <textarea name="descricao" id="descricao" rows="4" placeholder="Descrição do animal..." required></textarea>
                </div>

                <div class="form-row">
                    <button type="submit">Cadastrar Animal</button>
                </div>
            </form>
    </div>

    <%
        String msgSucesso = (String) session.getAttribute("mensagemSucesso");
        if (msgSucesso != null) {
    %>
    <div class="alert-success"><%= msgSucesso %></div>
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

<script src="${pageContext.request.contextPath}/assets/js/mensagens.js"></script>

<script>
    window.mapaRecintos = {
        <% for (Map.Entry<String, List<Recinto>> entry : recintosPorTipo.entrySet()) { %>
        "<%= entry.getKey() %>": [
            <% for (Recinto r : entry.getValue()) { %>
            { nome: "<%= r.getNome() %>" },
            <% } %>
        ],
        <% } %>
    };
</script>
<script src="${pageContext.request.contextPath}/assets/js/recintos.js"></script>

<jsp:include page="/includes/footer.jsp" />
