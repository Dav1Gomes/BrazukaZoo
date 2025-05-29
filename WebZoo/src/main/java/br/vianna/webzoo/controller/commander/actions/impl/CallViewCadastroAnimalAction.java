package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.model.Recinto;
import br.vianna.webzoo.util.JpaUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.util.*;

public class CallViewCadastroAnimalAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        EntityManager em = null;

        try {
            em = JpaUtil.getEntityManager();
            List<Recinto> lista = ((EntityManager) em).createQuery("SELECT r FROM Recinto r ORDER BY r.tipoAnimal, r.nome", Recinto.class).getResultList();

            Map<String, List<Recinto>> mapa = new HashMap<>();
            for (Recinto r : lista) {
                mapa.computeIfAbsent(r.getTipoAnimal(), k -> new ArrayList<>()).add(r);
            }

            request.setAttribute("mapaRecintos", mapa);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagemErro", "Erro ao carregar recintos.");
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return "/admin/cadastroAnimal.jsp";
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
