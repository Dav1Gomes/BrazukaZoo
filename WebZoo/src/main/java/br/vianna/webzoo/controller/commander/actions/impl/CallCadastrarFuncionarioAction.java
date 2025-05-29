package br.vianna.webzoo.controller.commander.actions.impl;

import br.vianna.webzoo.controller.commander.actions.ICommanderAction;
import br.vianna.webzoo.model.*;
import br.vianna.webzoo.model.dao.impl.FuncionarioDAO;
import br.vianna.webzoo.model.dao.impl.UsuarioDao;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.sql.Date;

public class CallCadastrarFuncionarioAction implements ICommanderAction {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        EntityManager em = null;

        try {
            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String email = request.getParameter("email");
            String telefone = request.getParameter("telefone");
            String cargo = request.getParameter("cargo");
            Date dataContratacao = Date.valueOf(request.getParameter("dataContratacao"));
            String turno = request.getParameter("turno");
            String senha = request.getParameter("senha");

            em = JpaUtil.getEntityManager();

            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setTipo(ETipoUsuario.FUNCIONARIO);

            UsuarioDao usuarioDao = new UsuarioDao(em);
            usuarioDao.inserir(usuario);

            Funcionario funcionario = new Funcionario();
            funcionario.setNome(nome);
            funcionario.setCpf(cpf);
            funcionario.setEmail(email);
            funcionario.setTelefone(telefone);
            funcionario.setCargo(cargo);
            funcionario.setDataContratacao(dataContratacao);
            funcionario.setTurno(turno);
            funcionario.setSenha(senha);
            funcionario.setTipo(ETipoUsuario.FUNCIONARIO);
            funcionario.setUsuario(usuario);

            FuncionarioDAO funcionarioDAO = new FuncionarioDAO(em);
            funcionarioDAO.inserir(funcionario);

            request.getSession().setAttribute("mensagemSucesso", "Funcionário cadastrado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("mensagemErro", "Erro ao cadastrar funcionário.");
        } finally {
            if (em != null && em.isOpen()) em.close();
        }

        return "/admin/cadastroFuncionario.jsp";
    }

    @Override
    public boolean isAuthorized(HttpServletRequest req) {
        return true;
    }
}
