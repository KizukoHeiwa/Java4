package com.org.java4.daos;

import com.org.java4.entities.Log;
import com.org.java4.interfaces.LogDAO;
import com.org.java4.utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class LogDAOImpl implements LogDAO {

    EntityManager em = XJPA.getEntityManager();
    @Override
    protected void finalize() throws Throwable {
        em.close();
    }
    @Override
    public List<Log> findAll() {
        String jpql = "SELECT l FROM Log l";
        TypedQuery<Log> query = em.createQuery(jpql, Log.class);
        return query.getResultList();
    }
    @Override
    public Log findById(int id) {
        return em.find(Log.class, id);
    }
    @Override
    public void create(Log entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
    @Override
    public void update(Log entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
    @Override
    public void deleteById(int id) {
        Log entity = em.find(Log.class, id);
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
}
