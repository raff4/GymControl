package com.bertazoli.server.businesslogic;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.bertazoli.client.rpc.LoginService;
import com.bertazoli.server.hibernate.HibernateUtil;
import com.bertazoli.shared.Util;
import com.bertazoli.shared.beans.User;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class LoginBusinessLogic implements LoginService {
    
    @Inject
    public LoginBusinessLogic() {
    }

    @Override
    public User validateUser(String username, String password) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("username", username));
        User user = (User) criteria.uniqueResult();
        session.getTransaction().commit();
        if (user != null) {
            if (user.getPassword().equals(Util.getEncryptedPassword(password, user.getSalt()))) {
                user.setLoggedIn(true);
                user.setPassword(null);
                user.setSalt(null);
                return user;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
