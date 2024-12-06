package com.org.java4.daos;

import com.org.java4.entities.Share;
import com.org.java4.interfaces.ShareDAO;
import com.org.java4.utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class ShareDAOImpl implements ShareDAO {
    EntityManager em = XJPA.getEntityManager();
    @Override
    protected void finalize() throws Throwable {
        em.close();
    }
    @Override
    public List<Share> findAll() {
        String jpql = "SELECT o FROM Share o";
        TypedQuery<Share> query = em.createQuery(jpql, Share.class);
        return query.getResultList();
    }
    @Override
    public Share findById(String id) {
        return em.find(Share.class, id);
    }
    @Override
    public void create(Share entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
    @Override
    public void update(Share entity) {
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
        Share entity = em.find(Share.class, id);
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public List<Share> findByVideoId(String videoId) {
        String jpql = "SELECT o FROM Share o WHERE o.videoid.id = :videoId";
        TypedQuery<Share> query = em.createQuery(jpql, Share.class);
        query.setParameter("videoId", videoId);
        return query.getResultList();
    }
}
