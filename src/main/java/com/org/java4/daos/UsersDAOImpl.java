package com.org.java4.daos;

import com.org.java4.entities.Users;
import com.org.java4.interfaces.UsersDAO;
import com.org.java4.utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public class UsersDAOImpl implements UsersDAO {
    EntityManager em = XJPA.getEntityManager();
    @Override
    protected void finalize() {
        em.close();
    }
    @Override
    public List<Users> findAll() {
        String jpql = "SELECT o FROM Users o";
        TypedQuery<Users> query = em.createQuery(jpql, Users.class);
        return query.getResultList();
    }

    @Override
    public List<Users> findByPage(int pageNumber, int pageSize) {
        TypedQuery<Users> query = em.createQuery("SELECT u FROM Users u", Users.class);

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
    public Users findByIdOrEmail(String search) {
        String jpql = "SELECT o FROM Users o WHERE o.id = :search OR o.email = :search";
        TypedQuery<Users> query = em.createQuery(jpql, Users.class);
        query.setParameter("search", search);
        if (!query.getResultList().isEmpty()) {
            return query.getSingleResult();
        }
        else {
            return null;
        }
    }
    @Override
    public void create(Users entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
    @Override
    public void update(Users entity) {
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
        Users entity = em.find(Users.class, id);
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public HashMap<Users, LocalDate> findUserFavoriteVideoID(String videoId) {
        String jpql = "SELECT u, f.likedate FROM Users u JOIN u.favorites f WHERE f.videoid.id = :videoId";
        TypedQuery<Object> query = em.createQuery(jpql, Object.class);
        query.setParameter("videoId", videoId);

        HashMap<Users, LocalDate> map = new HashMap<>();
        for (Object obj : query.getResultList()) {
            Object[] array = (Object[]) obj;
            map.put((Users) array[0], (LocalDate) array[1]);
        }
        return map;
    }
}
