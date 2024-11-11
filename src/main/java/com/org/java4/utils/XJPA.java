package com.org.java4.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class XJPA {
    private static EntityManagerFactory factory;
    static {
        factory = Persistence.createEntityManagerFactory("ASM_JAVA4");
    }
    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }
}
