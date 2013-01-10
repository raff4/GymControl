package com.bertazoli.server.servlet;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class LoginServlet extends RemoteServiceServlet {
    private static final long serialVersionUID = -8779907580341576276L;
    
    @Inject
    public LoginServlet() {
    }
}