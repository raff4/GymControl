package com.bertazoli.server.businesslogic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.Session;

import com.bertazoli.server.hibernate.HibernateUtil;
import com.bertazoli.shared.beans.User;
import com.google.inject.Singleton;

@Singleton
public class UserBusinessLogic {
    public static String algorithm = "SHA-256";

    public User create(User user) {
        user.setLoggedIn(false);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String salt = RandomStringUtils.randomAscii(50);
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update((user.getPassword()+salt).getBytes());
        byte[] mdbytes = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i<mdbytes.length; i++) {
            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        String password = sb.toString();
        user.setPassword(password);
        user.setSalt(salt);
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        return user;
    }
}
