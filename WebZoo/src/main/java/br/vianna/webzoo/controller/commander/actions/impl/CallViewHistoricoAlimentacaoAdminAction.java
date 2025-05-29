package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.model.Alimentacao;
import br.vianna.webzoo.model.dao.impl.AlimentacaoDAO;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.util.List;

public class CallViewHistoricoAlimentacaoAdminAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        EntityManager em = null;

        try {
            em = JpaUtil.getEntityManager();
            AlimentacaoDAO dao = new AlimentacaoDAO(em);
            List<Alimentacao> lista = dao.buscarTodas();
            request.setAttribute("listaAlimentacoes", lista);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagemErro", "Erro ao carregar histórico de alimentação.");
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return "/admin/historicoAlimentacao.jsp";
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
