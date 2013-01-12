package com.bertazoli.server.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.inject.Singleton;

@Singleton
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = -8779907580341576276L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("username").equals("vitor") && req.getParameter("password").equals("123456")) {
            HttpSession session = req.getSession();
            session.setAttribute("user", "some object");
            resp.sendRedirect("/index.html?gwt.codesvr=127.0.0.1:9997");
        } else {
            System.out.println("wrong user/pass");
            resp.sendRedirect("/login/login.html");
        }
        resp.flushBuffer();
    }
}