package com.bertazoli.server.guice;

import com.bertazoli.server.rpc.LoginServiceImpl;
import com.bertazoli.server.rpc.UserServiceImpl;
import com.bertazoli.server.rpc.WorkoutCardioServiceImpl;
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
                        serve("/gymcontrol/user").with(UserServiceImpl.class);
                        serve("/gymcontrol/login").with(LoginServiceImpl.class);
                        serve("/gymcontrol/workoutcardio").with(WorkoutCardioServiceImpl.class);
                    }
                });
    }
}
