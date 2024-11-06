package com.org.java4.daos;

import com.org.java4.interfaces.UserDAO;
import com.org.java4.model.User;
import com.org.java4.utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    EntityManager em = XJPA.getEntityManager();
    @Override
    protected void finalize() throws Throwable {
        em.close();
    }
    @Override
    public List<User> findAll() {
        String jpql = "SELECT o FROM User o";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        return query.getResultList();
    }
    @Override
    public User findById(String id) {
        return em.find(User.class, id);
    }
    @Override
    public void create(User entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
    @Override
    public void update(User entity) {
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
        User entity = em.find(User.class, id);
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    @Override
    public List<User> findByFullnameAndRole(String search, boolean searchRole) {
        if (!search.isEmpty()) {
            String jpql = "SELECT o FROM User o WHERE o.fullname LIKE :search and o.admin = :searchRole";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("search", "%" + search + "%");
            query.setParameter("searchRole", searchRole);
            return query.getResultList();
        }
        else {
            return findAll();
        }
    }

}
