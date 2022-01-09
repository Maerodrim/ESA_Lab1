package com.ssau.esalab.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

import com.ssau.esalab.dao.ModelDao;
import com.ssau.esalab.dao.impl.AbstractDaoImpl;
import com.ssau.esalab.model.Model;

@Stateless
public class ModelDaoImpl extends AbstractDaoImpl<Model> implements ModelDao {

    private static String SELECT_MODEL = "select distinct c from Model c left join fetch c.manager";

    @Override
    public List<Model> getAll() {
        TypedQuery<Model> getAllQuery = em.createQuery(SELECT_MODEL, Model.class);
        List<Model> result = getAllQuery.getResultList();
        return result;
    }
}
