package com.org.java4.daos;

import com.org.java4.entities.Video;
import com.org.java4.interfaces.VideoDAO;
import com.org.java4.utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class VideoDAOImpl implements VideoDAO {
    EntityManager em = XJPA.getEntityManager();
    @Override
    protected void finalize() throws Throwable {
        em.close();
    }
    @Override
    public List<Video> findAll() {
        String jpql = "SELECT v FROM Video v";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        return query.getResultList();
    }

    @Override
    public List<Video> findByPage(int pageNumber, int pageSize) {
        TypedQuery<Video> query = em.createQuery("SELECT v FROM Video v", Video.class);

        // Đặt vị trí bắt đầu cho phân trang (pageNumber tính từ 0)
        query.setFirstResult(pageNumber * pageSize);

        // Đặt số lượng kết quả tối đa mỗi lần truy vấn (pageSize)
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    @Override
    public int quantity() {
        return this.findAll().size();
    }

    @Override
    public Video findById(String id) {
        return em.find(Video.class, id);
    }

    @Override
    public List<Video> findByTitle(String keyword) {
        String jpql = "SELECT v FROM Video v WHERE v.title LIKE :keyword";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
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
        String jpql = "SELECT v FROM Video v ORDER BY v.views DESC";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        return query.getResultList();
    }

    @Override
    public List<Video> findByTitleContaining(String keyword) {
        String jpql = "SELECT v FROM Video v WHERE v.title LIKE :keyword";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        query.setParameter("keyword", "%" + keyword + "%");
        return query.getResultList();
    }

    @Override
    public List<Video> findByFavorite() {
        String jpql = "select f.videoid from Favorite f";
        TypedQuery<Video> query = em.createQuery(jpql,Video.class);
        return query.getResultList();
    }

    @Override
    public List<Video> findByShared() {
        String jpql = "SELECT v FROM Video v JOIN v.shares s";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        return query.getResultList();
    }

    @Override
    public List<Video> findByFavoriteByUser(String userId) {
        String jpql = "SELECT v FROM Video v JOIN v.favorites f WHERE f.userid = :userId";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        query.setParameter("userId", new UsersDAOImpl().findByIdOrEmail(userId));
        return query.getResultList();
    }

    @Override
    public List<Video> findBySharedByUserId(String userId) {
        String jpql = "SELECT v FROM Video v JOIN v.shares s WHERE s.userid = :userId";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<String> findUserFavoriteVideoID(String videoId) {
        return List.of();
    }

    @Override
    public HashMap<String, Long> findFavoriteByVideoId() {
        String jpql = "SELECT v.id ,COUNT(l.id) FROM Video v JOIN v.favorites l group by v.id";
        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
        HashMap<String, Long> map = new HashMap<>();
        for (Object[] obj: query.getResultList()) {
            map.put((String) obj[0], (Long) obj[1]);
        }
        return map;
    }

    @Override
    public List<Video> findVideoNoLike() {
        String jpql = "SELECT v FROM Video v JOIN v.favorites f WHERE f.userid IS NULL";
        TypedQuery<Video> query = em.createQuery(jpql, Video.class);
        return query.getResultList();
    }
}
