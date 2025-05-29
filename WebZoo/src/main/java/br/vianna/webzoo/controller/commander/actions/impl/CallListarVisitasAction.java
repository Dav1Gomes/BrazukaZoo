package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.model.Visita;
import br.vianna.webzoo.model.dao.impl.VisitaDao;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.util.List;

public class CallListarVisitasAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        EntityManager em = null;
        try {
            int idVisitante = Integer.parseInt(request.getParameter("idVisitante"));
            em = JpaUtil.getEntityManager();
            VisitaDao dao = new VisitaDao(em);
            List<Visita> visitas = dao.listarPorVisitante(idVisitante);
            request.setAttribute("listaVisitas", visitas);
        } catch (Exception e) {
            request.setAttribute("mensagemErro", "Erro ao carregar suas visitas.");
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return "/visitante/minhasVisitas.jsp";
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
