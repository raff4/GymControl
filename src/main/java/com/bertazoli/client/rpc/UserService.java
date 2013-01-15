package com.bertazoli.client.rpc;

import com.bertazoli.shared.beans.User;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService {
    public User create(User user);
    public boolean usernameExists(String username);
}
