package com.bertazoli.client.events;

import com.bertazoli.client.handler.LoginAuthenticatedEventHandler;
import com.bertazoli.shared.beans.User;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.GwtEvent;

public class LoginAuthenticatedEvent extends GwtEvent<LoginAuthenticatedEventHandler> {

    private static final Type<LoginAuthenticatedEventHandler> TYPE = new Type<LoginAuthenticatedEventHandler>();

    public static Type<LoginAuthenticatedEventHandler> getType() {
      return TYPE;
    }

    public static void fire(EventBus eventBus, User currentUser) {
      eventBus.fireEvent(new LoginAuthenticatedEvent(currentUser));
    }

    private final User user;

    public LoginAuthenticatedEvent(User currentUser) {
      this.user = currentUser;
    }

    public User getCurrentUser() {
      return user;
    }

    @Override
    protected void dispatch(LoginAuthenticatedEventHandler handler) {
      handler.onLogin(this);
    }

    @Override
    public Type<LoginAuthenticatedEventHandler> getAssociatedType() {
      return getType();
    }
  }