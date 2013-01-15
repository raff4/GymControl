package com.bertazoli.server.handler;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.bertazoli.server.businesslogic.LoginBusinessLogic;
import com.bertazoli.shared.action.LoginAction;
import com.bertazoli.shared.action.LoginResult;
import com.bertazoli.shared.beans.User;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

public class LoginHandler implements ActionHandler<LoginAction, LoginResult> {
    public final Provider<HttpServletRequest> requestProvider;
    private LoginBusinessLogic login;
    
    @Inject
    public LoginHandler(ServletContext servletContext,
            Provider<HttpServletRequest> requestProvider,
            LoginBusinessLogic login) {
        this.requestProvider = requestProvider;
        this.login = login;
    }

    @Override
    public LoginResult execute(LoginAction action, ExecutionContext context) throws ActionException {
        LoginResult result = null;
        User user = login.validateUser(action.getUsername(), action.getPassword());
        if (user != null) {
            HttpSession session = requestProvider.get().getSession();
            result = new LoginResult(session.getId(), user);
        }
        return result;
    }

    @Override
    public Class<LoginAction> getActionType() {
        return LoginAction.class;
    }

    @Override
    public void undo(LoginAction action, LoginResult result, ExecutionContext context) throws ActionException {
        
    }
}