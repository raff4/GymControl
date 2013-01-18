package com.bertazoli.client.manager;

import com.bertazoli.shared.beans.User;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class SecurityManager {
    private User user;
    
    @Inject
    public SecurityManager() {
        System.out.println("creating security manager");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
