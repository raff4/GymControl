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
import javax.servlet.http.HttpSession;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class AuthFilter implements Filter {

    @Inject
    public AuthFilter() {
        
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        
        boolean isLoginPage  = req.getRequestURI().matches("^/login/.*");
        boolean isAuthenticated = false;
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            isAuthenticated = true;
        }
        
        if (isAuthenticated || isLoginPage) {
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