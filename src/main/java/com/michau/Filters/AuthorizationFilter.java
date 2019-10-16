package com.michau.Filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "*")
public class AuthorizationFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String servletPath = req.getServletPath();

        if (isUnprotectedResource(servletPath)) {
            chain.doFilter(req, res);
        }
        else if (isProtectedResource(servletPath) &&
                req.getSession().getAttribute("user") == null){
            res.sendRedirect("/login");
            chain.doFilter(req, res);
        }
        else if(isProtectedResource(servletPath)&&req.getSession().getAttribute("user")!=null) {
            chain.doFilter(req, res);
        }else {
            res.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    private boolean isUnprotectedResource(String servletPath) {
        return servletPath.equalsIgnoreCase("/login") &&
                servletPath.equalsIgnoreCase("/register");
    }

    private boolean isProtectedResource(String servletPath) {
        return servletPath.equalsIgnoreCase("/user/unknown-sources") &&
                servletPath.equalsIgnoreCase("/user/skills") &&
                servletPath.equalsIgnoreCase("/user/sources");
    }
}
