package com.bertazoli.server.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        
        String pathToBeIgnored = "/login";

        if (req.getRequestURI().startsWith(pathToBeIgnored)) {
            chain.doFilter(request, response);
        } else {
            if (session.getAttribute("securityInfo") != null && session.getAttribute("securityInfo").equals("true")) {
                System.out.println("user is valid");
                RequestDispatcher rd = req.getRequestDispatcher("/#welcome");
                rd.forward(request, response);
            } else {
                System.out.println("user is invalid");
                RequestDispatcher rd = req.getRequestDispatcher("/login/login.html");
                rd.forward(request, response);
            }    
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("init");
    }
}