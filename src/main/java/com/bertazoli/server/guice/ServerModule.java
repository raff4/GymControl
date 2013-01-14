package com.bertazoli.server.guice;

import com.bertazoli.server.handler.LoginHandler;
import com.bertazoli.shared.action.LoginAction;
import com.gwtplatform.dispatch.server.guice.HandlerModule;

public class ServerModule extends HandlerModule {

    @Override
    protected void configureHandlers() {
        bindHandler(LoginAction.class, LoginHandler.class);
        
    }
}
