package com.bertazoli.server.businesslogic;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bertazoli.beans.User;
import com.bertazoli.server.hibernate.HibernateUtil;

public class Login {
    Session session = null;
    
    public Login() {
        session = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    public User getUserByUsername(String username) {
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("SELECT 1 FROM User");
        query.list();
        return new User();
    }
}
