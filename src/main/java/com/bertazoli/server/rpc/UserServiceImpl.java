package com.bertazoli.server.rpc;

import com.bertazoli.client.rpc.UserService;
import com.bertazoli.server.businesslogic.UserBusinessLogic;
import com.bertazoli.shared.beans.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UserServiceImpl extends RemoteServiceServlet implements UserService {

    private static final long serialVersionUID = -4535892596594422411L;
    private UserBusinessLogic userBusinessLogic;
    
    @Inject
    public UserServiceImpl(UserBusinessLogic userBusinessLogic) {
        this.userBusinessLogic = userBusinessLogic;
    }

    @Override
    public User create(User user) {
        return userBusinessLogic.create(user);
    }

}
