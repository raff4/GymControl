package com.bertazoli.client.handler;

import com.bertazoli.client.events.LoginAuthenticatedEvent;
import com.google.gwt.event.shared.EventHandler;

public interface LoginAuthenticatedEventHandler extends EventHandler {
    public void onLogin(LoginAuthenticatedEvent event);
}
