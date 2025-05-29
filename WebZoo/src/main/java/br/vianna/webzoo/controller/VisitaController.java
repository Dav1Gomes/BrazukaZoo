package br.vianna.webzoo.controller;

import br.vianna.webzoo.model.Usuario;
import br.vianna.webzoo.model.Visita;
import br.vianna.webzoo.model.dao.impl.VisitaDao;
import br.vianna.webzoo.util.JpaUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.EntityManager;

@WebServlet("/visita")
public class VisitaController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = null;

        try {
            Usuario visitante = (Usuario) req.getSession().getAttribute("usuarioLogado");

            if (visitante == null) {
                req.setAttribute("mensagemErro", "Você precisa estar logado.");
                req.getRequestDispatcher("/auth/login.jsp").forward(req, resp);
                return;
            }

            Date data = Date.valueOf(req.getParameter("dataVisita"));

            String horaStr = req.getParameter("horario");
            if (horaStr != null && horaStr.length() == 5) {
                horaStr += ":00";
            }
            Time horario = Time.valueOf(horaStr);

            int qtdPessoas = Integer.parseInt(req.getParameter("quantidadePessoas"));
            String obs = req.getParameter("observacoes");

            Visita visita = new Visita(0, visitante.getId(), data, horario, qtdPessoas, obs);
            visita.setNomeVisitante(visitante.getNome());

            em = JpaUtil.getEntityManager();
            VisitaDao dao = new VisitaDao(em);
            dao.inserir(visita);

            req.getSession().setAttribute("mensagem", "Visita agendada com sucesso!");
            resp.sendRedirect("web?ac=listarVisitas&idVisitante=" + visitante.getId());

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("mensagemErro", "Erro ao agendar visita.");
            req.getRequestDispatcher("/visitante/agendarVisita.jsp").forward(req, resp);
        } finally {
            if (em != null && em.isOpen()) em.close();
        }
    }
}
