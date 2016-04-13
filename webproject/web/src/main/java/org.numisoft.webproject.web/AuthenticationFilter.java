package org.numisoft.webproject.web;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * AuthenticationFilter checks if Session contains User
 * If there is no User in Session Filter redirects to Login page
 *
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
