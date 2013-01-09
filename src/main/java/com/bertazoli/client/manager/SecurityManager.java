package com.bertazoli.client.manager;

import com.google.inject.Singleton;

@Singleton
public class SecurityManager {
    
    public boolean validateUsernamePassword(String username, String password) {
        return true;
    }

    public boolean isUserLoggedIn() {
        return true;
    }
}
