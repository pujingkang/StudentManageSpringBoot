//package com.souls.filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebFilter("/*")
//public class LoginFilter implements Filter {
//    public void init(FilterConfig config) throws ServletException {
//    }
//
//    public void destroy() {
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        String URI = req.getRequestURI();
//        if (!URI.contains("login")) {
//            HttpSession session = req.getSession();
//            if (session.getAttribute("token") == null) {
//                session.setAttribute("msg", "login please~");
//                req.getRequestDispatcher("login").forward(req, response);
//
//            }
//        }
//        chain.doFilter(request, response);
//    }
//}
