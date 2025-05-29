package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.model.TransferenciaRecinto;
import br.vianna.webzoo.model.dao.impl.TransferenciaDao;
import br.vianna.webzoo.model.dto.TransferenciaDTO;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class CallViewHistoricoTransferenciasAdminAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        EntityManager em = null;

        try {
            em = JpaUtil.getEntityManager();
            TransferenciaDao dao = new TransferenciaDao(em);
            List<TransferenciaRecinto> lista = dao.buscarTodos();

            List<TransferenciaDTO> dtos = new ArrayList<>();

            for (TransferenciaRecinto t : lista) {
                em.refresh(t.getFuncionario());
                dtos.add(new TransferenciaDTO(t));
            }

            req.setAttribute("listaTransferencias", dtos);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("mensagemErro", "Erro ao carregar histórico geral de transferências.");
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return "/admin/historicoTransferencias.jsp";
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
