package org.numisoft.webproject.services;


import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.*;

/**
 * Created by kukolka on 06.04.16.
 */
public class AuthenticationFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        if (uri.endsWith("/webproject/") || uri.endsWith("login")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (null == session) {
            response.sendRedirect("/webproject");
        } else if (null == session.getAttribute("user")) {
            response.sendRedirect("/webproject");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    public void destroy() {

    }
}
