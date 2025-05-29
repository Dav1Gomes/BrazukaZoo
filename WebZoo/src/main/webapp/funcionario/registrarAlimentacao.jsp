<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, br.vianna.webzoo.model.Animal" %>

<jsp:include page="/includes/header.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/admin.css">

<main class="admin-container">
    <h2>Registrar Alimentação</h2>

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

    <form action="${pageContext.request.contextPath}/web?ac=CallRegistrarAlimentacaoAction" method="post" class="formulario-admin">

        <label for="idAnimal">Selecione o Animal:</label>
        <select name="idAnimal" id="idAnimal" required>
            <option value="">-- Selecione --</option>
            <%
                List<Animal> lista = (List<Animal>) request.getAttribute("listaAnimais");
                if (lista != null) {
                    for (Animal a : lista) {
            %>
            <option value="<%= a.getId() %>"><%= a.getNome() %> - <%= a.getEspecie() %></option>
            <%
                    }
                }
            %>
        </select>

        <label for="tipoAlimento">Tipo de Alimento:</label>
        <input type="text" name="tipoAlimento" id="tipoAlimento" required>

        <button type="submit">Registrar</button>
    </form>
</main>

<jsp:include page="/includes/footer.jsp" />
