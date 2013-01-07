package com.bertazoli.remote.gwt.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("login")
public interface LoginService  extends RemoteService {
    String helloWorld(String message);
}
