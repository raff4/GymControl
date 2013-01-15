package com.bertazoli.server.businesslogic;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;

import com.bertazoli.server.hibernate.HibernateUtil;
import com.bertazoli.shared.Util;
import com.bertazoli.shared.beans.User;
import com.google.inject.Singleton;

@Singleton
public class UserBusinessLogic {

    public User create(User user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String salt = RandomStringUtils.randomAscii(50);
        String password = Util.getEncryptedPassword(user.getPassword(), salt);
        user.setPassword(password);
        user.setSalt(salt);
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        user.setPassword(null);
        user.setSalt(null);
        user.setLoggedIn(true);
        return user;
    }

    public boolean usernameExists(String username) {
        return false;
    }
}
