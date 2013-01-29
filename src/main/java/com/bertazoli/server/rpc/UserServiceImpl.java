package com.bertazoli.server.rpc;

import net.sf.gilead.gwt.PersistentRemoteService;

import com.bertazoli.client.rpc.UserService;
import com.bertazoli.server.businesslogic.UserBusinessLogic;
import com.bertazoli.shared.beans.User;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UserServiceImpl extends PersistentRemoteService implements UserService {


    private static final long serialVersionUID = -4535892596594422411L;
    private UserBusinessLogic userBusinessLogic;
    
    @Inject
    public UserServiceImpl() {

    }

    @Override
    public User create(User user) {
        return userBusinessLogic.create(user);
    }

    @Override
    public boolean usernameExists(String username) {
        return userBusinessLogic.usernameExists(username);
    }

}
