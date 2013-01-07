package com.bertazoli.server.rpc;

import com.bertazoli.client.rpc.LoginService;
import com.bertazoli.server.businesslogic.Login;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Singleton;

@Singleton
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

    private static final long serialVersionUID = -5879498534468523044L;

    @Override
    public String helloWorld(String message) {
        new Login().getUserByUsername(message);
        return "This is comming from the server";
    }

}
