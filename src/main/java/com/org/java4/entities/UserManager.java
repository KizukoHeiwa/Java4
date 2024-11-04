package com.org.java4.entities;

import com.org.java4.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UserManager {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
    EntityManager em = factory.createEntityManager();

    public void findAll() {
        String jpql = "SELECT o FROM User o";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        List<User> list = query.getResultList();

        list.forEach(user -> {
            String fullname = user.getFullname();
            boolean admin = user.getAdmin();
            System.out.println(fullname + ": " + admin);
        });
    }

    public void findById(String id) {
        User user = em.find(User.class, id);
        String fullname = user.getFullname();
        boolean admin = user.getAdmin();
        System.out.println(fullname + ":" + admin);
    }

    public void create() {
        User user = new User("U06", "123", "teo@gmail.com", "Tèo", false);
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void update() {
        User user = em.find(User.class, "U06");
        user.setFullname("Nguyễn Văn Tèo");
        user.setEmail("teonv@gmail.com");

        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteById() {
        User user = em.find(User.class, "U06");
        try {
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public void findWhere() {
        String jpql = "SELECT o FROM User o WHERE o.email LIKE :search AND o.admin=:role";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("search", "%@fpt.edu.vn");
        query.setParameter("role", false);
        List<User> list = query.getResultList();

        list.forEach(user -> {
            String fullname = user.getFullname();
            boolean admin = user.getAdmin();
            String email = user.getEmail();
            System.out.println(fullname + ": " + admin + " - " + email);
        });
    }

    public List<User> findWithPage(int pageNumber, int pageSize) {
        TypedQuery<User> query = em.createQuery("SELECT o FROM User o", User.class);

        // Đặt vị trí bắt đầu cho phân trang (pageNumber tính từ 0)
        query.setFirstResult(pageNumber * pageSize);

        // Đặt số lượng kết quả tối đa mỗi lần truy vấn (pageSize)
        query.setMaxResults(pageSize);
        List<User> list = query.getResultList();

        return list;
    }
}

