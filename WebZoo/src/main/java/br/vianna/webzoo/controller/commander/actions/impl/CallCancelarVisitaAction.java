package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.model.Visita;
import br.vianna.webzoo.model.dao.impl.VisitaDao;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;

public class CallCancelarVisitaAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        EntityManager em = null;

        try {
            int idVisita = Integer.parseInt(request.getParameter("id"));
            em = JpaUtil.getEntityManager();
            VisitaDao dao = new VisitaDao(em);

            Visita visita = dao.buscar(idVisita);
            if (visita != null) {
                visita.setStatus("Cancelada");
                dao.alterar(visita);
            }

            String tipoUsuario = (String) request.getSession().getAttribute("tipoUsuario");
            if ("ADMIN".equalsIgnoreCase(tipoUsuario)) {
                return "/web?ac=viewVisitasAdmin";
            } else {
                int idVisitante = Integer.parseInt(request.getParameter("idVisitante"));
                return "/web?ac=listarVisitas&idVisitante=" + idVisitante;
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagemErro", "Erro ao cancelar visita.");
            return "/visitante/minhasVisitas.jsp";
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
