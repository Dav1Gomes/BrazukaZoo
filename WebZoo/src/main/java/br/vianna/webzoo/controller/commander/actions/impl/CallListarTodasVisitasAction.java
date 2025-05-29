package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.model.Visita;
import br.vianna.webzoo.model.dao.impl.VisitaDao;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.util.List;

public class CallListarTodasVisitasAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            VisitaDao dao = new VisitaDao(em);
            List<Visita> visitas = dao.buscarTodos();

            for (Visita v : visitas) {
                try {
                    String nome = em.createQuery(
                                    "SELECT u.nome FROM Usuario u WHERE u.id = :id", String.class)
                            .setParameter("id", v.getIdVisitante())
                            .getSingleResult();
                    v.setNomeVisitante(nome);
                } catch (Exception ex) {
                    v.setNomeVisitante("Visitante #" + v.getIdVisitante());
                }
            }

            request.setAttribute("listaVisitas", visitas);
        } catch (Exception e) {
            request.setAttribute("mensagemErro", "Erro ao listar visitas.");
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return "/admin/visitas.jsp";
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
