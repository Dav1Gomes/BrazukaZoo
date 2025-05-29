package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.model.Fornecedor;
import br.vianna.webzoo.model.dao.impl.FornecedorDao;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.util.List;

public class CallListarFornecedoresAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        EntityManager em = null;

        try {
            em = JpaUtil.getEntityManager();
            FornecedorDao dao = new FornecedorDao(em);
            List<Fornecedor> lista = dao.buscarTodos();
            request.setAttribute("listaFornecedores", lista);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagemErro", "Erro ao carregar lista de fornecedores.");
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return "/admin/listarFornecedores.jsp";
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
