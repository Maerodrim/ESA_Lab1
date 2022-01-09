package com.ssau.esalab.dao;

import com.ssau.esalab.model.Car;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CarDaoImpl implements CarDao {
    @PersistenceContext(unitName = "default")
    private EntityManager em;


    @Override
    public Car get(Integer id) {
        return em.find(Car.class, id);
    }

    @Override
    public List<Car> getAll() {
        TypedQuery<Car> getAllQuery = em.createQuery("select c from Car c", Car.class);
        List<Car> result = getAllQuery.getResultList();
        return result;
    }

    @Override
    public void save(Car car) {
        em.persist(car);

    }

    @Override
    public void update(Car car) {
        em.merge(car);

    }

    @Override
    public void delete(Car car) {
        em.remove(em.contains(car) ? car : em.merge(car));
    }
}
