package com.supinfo.supcommerce.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/auth/*")
public class AuthenticateFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        if (session.getAttribute("username") != null) {
            chain.doFilter(req, response);
            return;
        }
        HttpServletResponse res = (HttpServletResponse) response;
        res.sendRedirect("/login");
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
