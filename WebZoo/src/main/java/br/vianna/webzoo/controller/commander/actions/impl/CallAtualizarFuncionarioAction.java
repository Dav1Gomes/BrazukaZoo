package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.model.ETipoUsuario;
import br.vianna.webzoo.model.Funcionario;
import br.vianna.webzoo.model.dao.impl.FuncionarioDAO;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;

public class CallAtualizarFuncionarioAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        EntityManager em = null;

        try {
            em = JpaUtil.getEntityManager();
            FuncionarioDAO dao = new FuncionarioDAO(em);

            Funcionario f = dao.buscar(Integer.parseInt(request.getParameter("id")));

            f.setNome(request.getParameter("nome"));
            f.setCpf(request.getParameter("cpf"));
            f.setEmail(request.getParameter("email"));
            f.setTelefone(request.getParameter("telefone"));
            f.setCargo(request.getParameter("cargo"));
            f.setTurno(request.getParameter("turno"));
            f.setSenha(request.getParameter("senha"));
            f.setTipo(ETipoUsuario.FUNCIONARIO);
            f.setDataContratacao(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("dataContratacao")));

            dao.alterar(f);

            request.getSession().setAttribute("mensagemSucesso", "Funcionário atualizado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("mensagemErro", "Erro ao atualizar funcionário.");
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return "web?ac=viewFuncionarios";
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
