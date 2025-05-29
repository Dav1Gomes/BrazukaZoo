package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.model.Fornecedor;
import br.vianna.webzoo.model.dao.impl.FornecedorDao;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;

public class CallCadastrarFornecedorAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        EntityManager em = null;

        try {
            String nome = request.getParameter("nome");
            String cnpj = request.getParameter("cnpj");
            String tipoFornecimento = request.getParameter("tipoFornecimento");
            String contato = request.getParameter("contato");
            String endereco = request.getParameter("endereco");
            String responsavelEntrega = request.getParameter("responsavelEntrega");

            Fornecedor fornecedor = new Fornecedor();
            fornecedor.setNome(nome);
            fornecedor.setCnpj(cnpj);
            fornecedor.setTipoFornecimento(tipoFornecimento);
            fornecedor.setContato(contato);
            fornecedor.setEndereco(endereco);
            fornecedor.setResponsavelEntrega(responsavelEntrega);

            em = JpaUtil.getEntityManager();
            FornecedorDao dao = new FornecedorDao(em);
            dao.inserir(fornecedor);

            request.getSession().setAttribute("mensagemSucesso", "Fornecedor cadastrado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("mensagemErro", "Erro ao cadastrar fornecedor.");
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return "/admin/cadastrarFornecedor.jsp";
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
