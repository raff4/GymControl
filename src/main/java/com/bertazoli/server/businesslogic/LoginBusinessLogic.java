package com.bertazoli.server.businesslogic;

import org.hibernate.Query;
import org.hibernate.Session;

import com.bertazoli.client.rpc.LoginService;
import com.bertazoli.server.hibernate.HibernateUtil;
import com.bertazoli.shared.beans.User;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class LoginBusinessLogic implements LoginService {
    
    @Inject
    public LoginBusinessLogic() {
    }

    @Override
    public String helloWorld(String message) {
        return "This is comming from the server";
    }

    @Override
    public User validateUser(String username, String password) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT 1 FROM User");
        query.list();
        session.getTransaction().commit();
        return null;
    }
}
