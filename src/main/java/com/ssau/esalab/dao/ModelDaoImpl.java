package com.ssau.esalab.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import com.ssau.esalab.model.Model;

@Stateless
public class ModelDaoImpl implements ModelDao {

    private static String SELECT_MODEL = "select distinct c from Model c left join fetch c.manager";

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    @Override
    public Model get(Integer id) {
        return em.find(Model.class, id);
    }

    @Override
    public List<Model> getAll() {
        TypedQuery<Model> getAllQuery = em.createQuery(SELECT_MODEL, Model.class);
        List<Model> result = getAllQuery.getResultList();
        return result;
    }

    @Override
    public void save(Model model) {
        em.persist(model);
    }

    @Override
    public void update(Model model) {
        em.merge(model);
    }

    @Override
    public void delete(Model model) {
        em.remove(em.contains(model) ? model : em.merge(model));
    }
}
