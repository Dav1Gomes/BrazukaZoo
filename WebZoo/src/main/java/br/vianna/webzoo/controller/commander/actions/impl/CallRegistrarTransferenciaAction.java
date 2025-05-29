package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.model.Animal;
import br.vianna.webzoo.model.Funcionario;
import br.vianna.webzoo.model.TransferenciaRecinto;
import br.vianna.webzoo.model.Usuario;
import br.vianna.webzoo.model.dao.impl.TransferenciaDao;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

public class CallRegistrarTransferenciaAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        EntityManager em = null;

        try {
            int idAnimal = Integer.parseInt(req.getParameter("idAnimal"));
            String origem = req.getParameter("recintoOrigem");
            String destino = req.getParameter("recintoDestino");
            String motivo = req.getParameter("motivo");

            Usuario usuario = (Usuario) req.getSession().getAttribute("usuarioLogado");

            em = JpaUtil.getEntityManager();

            em.getTransaction().begin();

            Funcionario funcionario = em.createQuery(
                            "SELECT f FROM Funcionario f WHERE f.usuario.id = :idUsuario", Funcionario.class)
                    .setParameter("idUsuario", usuario.getId())
                    .getSingleResult();

            Animal animal = em.find(Animal.class, idAnimal);

            animal.setRecinto(destino);
            em.merge(animal);

            TransferenciaRecinto t = new TransferenciaRecinto();
            t.setAnimal(animal);
            t.setRecintoOrigem(origem);
            t.setRecintoDestino(destino);
            t.setMotivo(motivo);
            t.setFuncionario(funcionario);
            t.setDataHora(LocalDateTime.now());

            TransferenciaDao dao = new TransferenciaDao(em);
            dao.inserir(t);

            em.getTransaction().commit();
            dao.close();

            req.getSession().setAttribute("mensagemSucesso", "Transferência registrada e recinto atualizado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            req.getSession().setAttribute("mensagemErro", "Erro ao registrar a transferência.");
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return "/web?ac=viewHistoricoTransferenciasFuncionario";
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
