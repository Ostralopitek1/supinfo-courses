package com.supfood.api.server.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "ApiAuthenticationFilter",
urlPatterns = "/api/*")
public class ApiAuthenticationFilter implements Filter {

    String API_TOKEN = "1367d318-0c30-11e8-ba89-0ed5f89f718b";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            HttpSession session = req.getSession();

            String requestToken = req.getHeader("token");

            if (req.getRequestURI().endsWith("/auth") &&
                    !requestToken.equals(API_TOKEN)) {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendError(403);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
