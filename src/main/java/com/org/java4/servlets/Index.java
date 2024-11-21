package com.org.java4.servlets;

import com.org.java4.daos.UsersDAOImpl;
import com.org.java4.daos.VideoDAOImpl;
import com.org.java4.entities.Users;
import com.org.java4.entities.Video;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/", "/index"})
public class Index extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getQueryString() != null) {
            if (req.getQueryString().contains("logout")) {
                req.getSession().setAttribute("user", null);
            }
        }

        List<Video> listVideos = new VideoDAOImpl().findAll();
        req.setAttribute("listVideos", listVideos);

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Users user = null;
        try {
            user = new UsersDAOImpl().findByIdOrEmail(email);

        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/index?login=1");
            return;
        }

        if (!user.getPassword().equals(password)) {
            resp.sendRedirect(req.getContextPath() + "/index?login=1");
            return;
        }
        req.getSession().setAttribute("user", user);

        String secureURI = (String) req.getSession().getAttribute("secureURI");
        if (secureURI != null) {
            req.getSession().removeAttribute("secureURI");
            resp.sendRedirect(req.getContextPath() + secureURI);
            return;
        }

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
