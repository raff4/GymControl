package com.bertazoli.inject.servlet;

import com.bertazoli.server.rpc.LoginServiceImpl;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class ServletContextListener extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule() {
            @Override
            protected void configureServlets() {
                serve("/gymcontrol/login").with(LoginServiceImpl.class);
            }
        });
    }
}
