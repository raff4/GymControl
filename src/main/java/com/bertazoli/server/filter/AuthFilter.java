package com.bertazoli.server.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bertazoli.client.manager.SecurityManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class AuthFilter implements Filter {

    private SecurityManager securityManager;
    
    @Inject
    public AuthFilter(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        
        boolean isLoginPage  = req.getRequestURI().matches("^/login/.*");
        
        if (securityManager.isUserLoggedIn() || isLoginPage) {
            // this will send user to login page or to requested page if the user is logged in
            chain.doFilter(request, response);
        } else {
            HttpServletResponse res = (HttpServletResponse)response;
            res.sendRedirect("/login/login.html");
            res.flushBuffer();
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("initialising filter");
    }
}