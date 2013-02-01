package com.bertazoli.client.gatekeeper;

import com.bertazoli.client.events.LoginAuthenticatedEvent;
import com.bertazoli.client.handler.LoginAuthenticatedEventHandler;
import com.bertazoli.client.manager.SecurityManager;
import com.bertazoli.shared.beans.User;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;

@Singleton
public class LoggedInGatekeeper implements Gatekeeper {
    private User user;
    private EventBus eventBus;
    private SecurityManager securityManager;
    
    @Inject
    public LoggedInGatekeeper(EventBus eventBus, SecurityManager securityManager) {
        this.eventBus = eventBus;
        this.securityManager = securityManager;
        
        this.eventBus.addHandler(LoginAuthenticatedEvent.getType(), new LoginAuthenticatedEventHandler() {
            @Override
            public void onLogin(LoginAuthenticatedEvent event) {
                user = event.getCurrentUser();
                LoggedInGatekeeper.this.securityManager.setUser(user);
            }
        });
    }

    @Override
    public boolean canReveal() {
        if (securityManager.getUser() != null) {
            if (securityManager.getUser().isLoggedIn() == true) {
                return true;
            }
        }
        
        if (user != null) {
            if (user.isLoggedIn() == true) {
                return true;
            }
        }
        return false;
    }

}
