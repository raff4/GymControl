package com.bertazoli.server.rpc;

import com.bertazoli.client.rpc.LoginService;
import com.bertazoli.server.businesslogic.LoginBusinessLogic;
import com.bertazoli.shared.beans.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

    private static final long serialVersionUID = -5879498534468523044L;
    private LoginBusinessLogic login;
    
    @Inject
    public LoginServiceImpl(LoginBusinessLogic login) {
        this.login = login;
    }

    @Override
    public User validateUser(String username, String password) {
        return login.validateUser(username, password);
    }

}
