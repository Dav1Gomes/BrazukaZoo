package br.vianna.webzoo.controller;

import br.vianna.webzoo.model.Animal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/testa/banco")
public class TestaBanco extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("webzoo_PU");
        EntityManager em = emf.createEntityManager();

        try {
            Animal animal = new Animal(
                    0,
                    "Leo",
                    "Leão",
                    "Savanas Africanas",
                    5,
                    Date.valueOf("2020-03-12"),
                    "Saudável",
                    "Recinto dos Felinos",
                    "Mamífero",
                    true,
                    "Panthera leo",
                    "Leo é um leão macho com uma juba impressionante..."
            );

            em.getTransaction().begin();
            em.persist(animal);
            em.getTransaction().commit();

            resp.getWriter().println("Animal persistido com sucesso!");

        } catch (Exception e) {
            resp.getWriter().println("Erro ao persistir o animal: " + e.getMessage());
            e.printStackTrace(resp.getWriter());
        } finally {
            em.close();
            emf.close();
        }
    }
}