package com.bertazoli.server.rpc;

import com.bertazoli.client.rpc.LoginService;
import com.bertazoli.server.businesslogic.Login;
import com.bertazoli.shared.beans.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

    private static final long serialVersionUID = -5879498534468523044L;
    private Login login;
    
    @Inject
    public LoginServiceImpl(Login login) {
        this.login = login;
    }

    @Override
    public String helloWorld(String message) {
        return login.helloWorld(message);
    }

    @Override
    public User validateUser(String username, String password) {
        return login.validateUser(username, password);
    }

}
