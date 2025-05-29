package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.model.Funcionario;
import br.vianna.webzoo.model.TransferenciaRecinto;
import br.vianna.webzoo.model.Usuario;
import br.vianna.webzoo.model.dao.impl.TransferenciaDao;
import br.vianna.webzoo.model.dto.TransferenciaDTO;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class CallViewHistoricoTransferenciasFuncionarioAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        EntityManager em = null;

        try {
            Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioLogado");

            em = JpaUtil.getEntityManager();

            Funcionario funcionario = em.createQuery(
                            "SELECT f FROM Funcionario f WHERE f.usuario.id = :idUsuario", Funcionario.class)
                    .setParameter("idUsuario", usuario.getId())
                    .getSingleResult();

            TransferenciaDao dao = new TransferenciaDao(em);
            List<TransferenciaRecinto> lista = dao.buscarPorFuncionario(funcionario.getId());
            dao.close();

            List<TransferenciaDTO> dtos = lista.stream()
                    .map(TransferenciaDTO::new)
                    .collect(Collectors.toList());

            req.setAttribute("listaTransferencias", dtos);

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("mensagemErro", "Erro ao carregar o histórico de transferências.");
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return "/funcionario/historicoTransferenciasFuncionario.jsp";
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
