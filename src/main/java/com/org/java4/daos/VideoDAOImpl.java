package com.org.java4.daos;

import com.org.java4.entities.Video;
import com.org.java4.interfaces.VideoDAO;
import com.org.java4.utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class VideoDAOImpl implements VideoDAO {
    EntityManager em = XJPA.getEntityManager();
    @Override
    protected void finalize() throws Throwable {
        em.close();
    }
    @Override
    public List<Video> findAll() {
        String jpql = "SELECT o FROM Video o";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        return query.getResultList();
    }
    @Override
    public Video findById(String id) {
        return em.find(Video.class, id);
    }
    @Override
    public void create(Video entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
    @Override
    public void update(Video entity) {
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
        Video entity = em.find(Video.class, id);
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public List<Video> findByViewsDescending() {
        return List.of();
    }

    @Override
    public List<Video> findByTitleContaining(String keyword) {
        return List.of();
    }

    @Override
    public List<String> findByFavorite() {
        String jpql = "select o.videoid.title from Favorite o";
        TypedQuery<String> q = em.createQuery(jpql,String.class);
        List<String> list = q.getResultList();
        list.forEach(System.out::println);
        return list;
    }

    @Override
    public List<Video> findByShared() {
        return List.of();
    }

    @Override
    public List<Video> findByLikedByUser(String userId) {
        return List.of();
    }

    @Override
    public List<Video> findBySharedByUserId(String userId) {
        return List.of();
    }

    @Override
    public List<Integer> findLikesByVideoId(String videoId) {
        return List.of();
    }
}
