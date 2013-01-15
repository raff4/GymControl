package com.bertazoli.server.guice;

import com.google.inject.servlet.ServletModule;
import com.gwtplatform.dispatch.server.guice.DispatchServiceImpl;
import com.gwtplatform.dispatch.shared.ActionImpl;

public class DispatchServletModule extends ServletModule {

    @Override
    public void configureServlets() {
//        install(new JpaPersistModule("gymcontrol"));
//        filter("/*").through(PersistFilter.class);
        serve("/" + ActionImpl.DEFAULT_SERVICE_NAME).with(DispatchServiceImpl.class);
    }
}
