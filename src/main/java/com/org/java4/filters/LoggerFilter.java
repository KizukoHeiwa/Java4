package com.org.java4.filters;

import com.org.java4.daos.LogDAOImpl;
import com.org.java4.entities.Log;
import com.org.java4.entities.Users;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;

@WebFilter("/videoDetail")
public class LoggerFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        Users user = (Users) req.getSession().getAttribute("user");
        String uri = req.getRequestURI();
        Date time = new Date();

        Log log = new Log();
        log.setUri(uri);
        log.setTime(time.toInstant());
        if (user == null) {
            log.setUsername("Guest");
        }
        else {
            log.setUsername(user.getFullname());
        }

        new LogDAOImpl().create(log);

        chain.doFilter(req, res);
    }
}
