package br.vianna.webzoo.model.dao.impl;

import br.vianna.webzoo.model.Visita;
import br.vianna.webzoo.model.dao.GenericsDAO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class VisitaDao extends GenericsDAO<Visita, Integer> {

    public VisitaDao(EntityManager em) {
        this.connection = em;
    }

    @Override
    protected Class<Visita> getClasseEntidade() {
        return Visita.class;
    }

    @Override
    public Visita inserir(Visita obj) throws Exception {
        connection.getTransaction().begin();
        connection.persist(obj);
        connection.getTransaction().commit();
        return obj;
    }

    @Override
    public Visita alterar(Visita obj) throws Exception {
        connection.getTransaction().begin();
        Visita v = connection.merge(obj);
        connection.getTransaction().commit();
        return v;
    }

    @Override
    public Visita apagar(Visita obj) throws Exception {
        connection.getTransaction().begin();
        Visita v = connection.find(Visita.class, obj.getId());
        if (v != null) connection.remove(v);
        connection.getTransaction().commit();
        return v;
    }

    @Override
    public Visita apagarByKey(Integer key) throws Exception {
        return apagar(buscar(key));
    }

    @Override
    public Visita buscar(Integer key) {
        return connection.find(Visita.class, key);
    }

    @Override
    public List<Visita> buscarTodos() {
        return connection.createQuery(
                "SELECT v FROM Visita v ORDER BY v.dataVisita DESC, v.horario DESC", Visita.class
        ).getResultList();
    }

    public List<Visita> listarPorVisitante(int idVisitante) {
        return connection.createQuery(
                "SELECT v FROM Visita v WHERE v.idVisitante = :id ORDER BY v.dataVisita DESC, v.horario DESC", Visita.class
        ).setParameter("id", idVisitante).getResultList();
    }

    public List<Visita> listarTodasVisitas() {
        return connection.createQuery(
                "SELECT v FROM Visita v ORDER BY v.dataVisita DESC, v.horario DESC", Visita.class
        ).getResultList();
    }

    public void cancelarVisita(int idVisita) {
        connection.getTransaction().begin();
        Visita v = connection.find(Visita.class, idVisita);
        if (v != null) {
            v.setStatus("Cancelada");
            connection.merge(v);
        }
        connection.getTransaction().commit();
    }

    public long contarTodas() {
        return connection.createQuery("SELECT COUNT(v) FROM Visita v", Long.class).getSingleResult();
    }
}
