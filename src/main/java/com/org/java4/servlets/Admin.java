package com.org.java4.servlets;

import com.org.java4.daos.UsersDAOImpl;
import com.org.java4.daos.VideoDAOImpl;
import com.org.java4.entities.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/*")
public class Admin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("listVideos", new VideoDAOImpl().findAll());
        req.setAttribute("listUsers", new UsersDAOImpl().findAll());

        for (Users user : new UsersDAOImpl().findAll()) {
            System.out.println(user.getFullname());
        }
        req.getRequestDispatcher("/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().contains("edit")) {
            if (req.getQueryString() != null) {
                if (req.getQueryString().contains("video")) {
                    req.setAttribute("video", new VideoDAOImpl().findById(req.getParameter("video")));
                }
                if (req.getQueryString().contains("user")) {
                    req.setAttribute("user", new UsersDAOImpl().findByIdOrEmail(req.getParameter("user")));
                }
            }
        }
        doGet(req, resp);
    }
}
