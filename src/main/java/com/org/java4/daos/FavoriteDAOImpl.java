package com.org.java4.daos;

import com.org.java4.entities.Favorite;
import com.org.java4.interfaces.FavoriteDAO;
import com.org.java4.utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class FavoriteDAOImpl implements FavoriteDAO {
    EntityManager em = XJPA.getEntityManager();
    @Override
    protected void finalize() throws Throwable {
        em.close();
    }
    @Override
    public List<Favorite> findAll() {
        String jpql = "SELECT o FROM Favorite o";
        TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
        return query.getResultList();
    }
    @Override
    public Favorite findById(String id) {
        return em.find(Favorite.class, id);
    }
    @Override
    public void create(Favorite entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
    @Override
    public void update(Favorite entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
    @Override
    public void deleteById(String id) {
        Favorite entity = em.find(Favorite.class, id);
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
}
