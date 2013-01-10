package com.bertazoli.server.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.google.inject.Singleton;

@Singleton
public class HibernateUtil {
    
    private static final SessionFactory sessionFactory;
    
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            System.out.println("Failed to initialise session factory");
            throw new ExceptionInInitializerError(e);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
