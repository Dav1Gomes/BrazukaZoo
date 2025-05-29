package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.model.Fornecedor;
import br.vianna.webzoo.model.dao.impl.FornecedorDao;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.util.List;

public class CallViewFornecedoresAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            FornecedorDao dao = new FornecedorDao(em);
            List<Fornecedor> lista = dao.buscarTodos();
            req.setAttribute("listaFornecedores", lista);
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("mensagemErro", "Erro ao carregar fornecedores.");
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
