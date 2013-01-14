package com.bertazoli.server.actionvalidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.gwtplatform.dispatch.server.actionvalidator.ActionValidator;
import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.ActionException;
import com.gwtplatform.dispatch.shared.Result;

public class LoggedInActionValidator implements ActionValidator {
    
    private final Provider<HttpServletRequest> requestProvider;
    
    @Inject
    LoggedInActionValidator(Provider<HttpServletRequest> requestProvider) {
        this.requestProvider = requestProvider;
    }

    @Override
    public boolean isValid(Action<? extends Result> action) throws ActionException {
        boolean result = true;
        HttpSession session = requestProvider.get().getSession();
        Object authenticated = session.getAttribute("login.authenticated");
        if (authenticated == null) {
            result = false;
        }
        return result;
    }
}