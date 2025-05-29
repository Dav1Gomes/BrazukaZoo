package br.vianna.webzoo.controller;

import br.vianna.webzoo.model.ETipoUsuario;
import br.vianna.webzoo.model.Funcionario;
import br.vianna.webzoo.model.Usuario;
import br.vianna.webzoo.model.dao.impl.FuncionarioDAO;
import br.vianna.webzoo.model.dao.impl.UsuarioDao;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import java.io.IOException;

@WebServlet(value = "/validaLogin")
public class LoginValidationController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = null;

        try {
            em = JpaUtil.getEntityManager();

            String email = req.getParameter("email");
            String senha = req.getParameter("senha");

            UsuarioDao usuarioDao = new UsuarioDao(em);
            Usuario usu = usuarioDao.buscarPorLoginSenha(email, senha);

            if (usu == null) {
                req.setAttribute("erroLogin", "E-mail ou senha inválidos.");
                req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
                return;
            }

            req.getSession().setAttribute("usuarioLogado", usu);
            req.getSession().setAttribute("tipoUsuario", usu.getTipo().toString());

            if (usu.getTipo() == ETipoUsuario.FUNCIONARIO) {
                Funcionario funcionario = em.createQuery(
                                "SELECT f FROM Funcionario f WHERE f.usuario.id = :id", Funcionario.class)
                        .setParameter("id", usu.getId())
                        .getSingleResult();

                req.getSession().setAttribute("idFuncionario", funcionario.getId());
            }

            switch (usu.getTipo()) {
                case ADMIN:
                    resp.sendRedirect(req.getContextPath() + "/admin/dashboard.jsp");
                    break;
                case FUNCIONARIO:
                    resp.sendRedirect(req.getContextPath() + "/funcionario/dashboard.jsp");
                    break;
                case VISITANTE:
                default:
                    resp.sendRedirect(req.getContextPath() + "/index.jsp");
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erroLogin", "Erro interno ao tentar fazer login.");
            req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }
}
