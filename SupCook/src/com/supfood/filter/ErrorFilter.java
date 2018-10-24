package com.supfood.filter;

import com.supfood.api.error.ApiError;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter(filterName = "ErrorFilter",
urlPatterns = "/*")
public class ErrorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            // Dirty fix to not apply filter to API
            if (!request.getRequestURI().startsWith("/api")) {
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("error")) {
                            ApiError apiError = ApiError.getError(cookie.getValue());
                            request.setAttribute("errorMsg", apiError.getMessage());
                            break;
                        }
                    }
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
