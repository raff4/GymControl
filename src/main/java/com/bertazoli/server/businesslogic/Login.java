package com.bertazoli.server.businesslogic;

import com.bertazoli.client.rpc.LoginService;
import com.bertazoli.shared.beans.User;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class Login implements LoginService {
    
    @Inject
    public Login() {
        
    }

    @Override
    public String helloWorld(String message) {
        return "This is comming from the server";
    }

    @Override
    public User validateUser(String username, String password) {
        return null;
    }
}
