package com.bertazoli.remote.gwt.server;

import com.bertazoli.remote.gwt.rpc.LoginService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

    @Override
    public String helloWorld(String message) {
        return "This is comming from the server";
    }

}
