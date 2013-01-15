package com.bertazoli.client.rpc;

import com.bertazoli.shared.beans.User;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("login")
public interface LoginService  extends RemoteService {
    public User validateUser(String username, String password);
}
