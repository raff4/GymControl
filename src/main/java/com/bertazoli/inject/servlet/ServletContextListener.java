package com.bertazoli.inject.servlet;

import com.bertazoli.server.filter.AuthFilter;
import com.bertazoli.server.servlet.LoginServlet;
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
                filter("/*").through(AuthFilter.class);
                serve("/login/login.do").with(LoginServlet.class);
            }
        });
    }
}
