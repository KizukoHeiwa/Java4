package com.org.java4.daos;

import com.org.java4.entities.Users;
import com.org.java4.interfaces.UsersDAO;
import com.org.java4.utils.XJPA;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UsersDAOImpl implements UsersDAO {
    EntityManager em = XJPA.getEntityManager();
    @Override
    protected void finalize() throws Throwable {
        em.close();
    }
    @Override
    public List<Users> findAll() {
        String jpql = "SELECT o FROM Users o";
        TypedQuery<Users> query = em.createQuery(jpql, Users.class);
        return query.getResultList();
    }
    @Override
    public Users findByIdOrEmail(String search) {
        String jpql = "SELECT o FROM Users o WHERE o.id = :search OR o.email = :search";
        TypedQuery<Users> query = em.createQuery(jpql, Users.class);
        query.setParameter("search", search);
        return query.getSingleResult();
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
}
