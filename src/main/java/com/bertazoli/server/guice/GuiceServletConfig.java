package com.bertazoli.server.guice;

import com.bertazoli.server.servlet.LoginServlet;
import com.bertazoli.server.servlet.SignUpServlet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class GuiceServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(
                new ServerModule(),
                new DispatchServletModule(),
                new ServletModule() {
                    @Override
                    protected void configureServlets() {
//                      filter("/*").through(AuthFilter.class);
                      serve("/login/login.do").with(LoginServlet.class);
                      serve("/signUp/signUp.do").with(SignUpServlet.class);
                    }
                });
    }
}
