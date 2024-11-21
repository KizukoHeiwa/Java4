package com.org.java4.filters;

import com.org.java4.entities.Users;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter({"/admin/*","/videoDetail", "/likedVideos"})
public class AuthFilter implements Filter {
    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("user");
        if (user == null) {
            session.setAttribute("secureURI", req.getRequestURI());
            resp.sendRedirect(req.getContextPath() + "/index?login=0");
        } else if (!user.getAdmin()){
            session.setAttribute("secureURI", req.getRequestURI());
            resp.sendRedirect(req.getContextPath() + "/index?auth=0");
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }
}
