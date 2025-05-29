package br.vianna.webzoo.controller;

import br.vianna.webzoo.model.Usuario;
import br.vianna.webzoo.model.ETipoUsuario;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

@WebServlet("/criarAdmin")
public class CriarAdminController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("webzoo_PU");
        EntityManager em = emf.createEntityManager();

        try {
            Usuario a = new Usuario();
            a.setNome("admin");
            a.setEmail("admin@zoo.com");
            a.setSenha("admin123");
            a.setTipo(ETipoUsuario.ADMIN);

            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();

            resp.getWriter().println("Admin criado com sucesso!");
        } catch (Exception e) {
            resp.getWriter().println("Erro ao criar admin: " + e.getMessage());
            e.printStackTrace(resp.getWriter());
        } finally {
            em.close();
            emf.close();
        }
    }
}