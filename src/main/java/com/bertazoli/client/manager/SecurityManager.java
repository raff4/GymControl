package com.bertazoli.client.manager;

import com.google.inject.Singleton;

@Singleton
public class SecurityManager {
    private boolean isUserLoggedIn = false;
    
    public boolean validateUsernamePassword(String username, String password) {
        return true;
    }

    public boolean isUserLoggedIn() {
        return isUserLoggedIn;
    }

    public void setUserLoggedIn(boolean isUserLoggedIn) {
        this.isUserLoggedIn = isUserLoggedIn;
        
    }
}
