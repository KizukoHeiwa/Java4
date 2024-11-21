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

import static java.lang.Math.ceil;

//@WebServlet({"/", "/index"})
public class Index extends HttpServlet {
    VideoDAOImpl videoDAO = new VideoDAOImpl();
    int pageNumber = 0;
    int pageSize = 6;
    int endPage = (int) ceil((double) videoDAO.quantity() / pageSize) - 1;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getQueryString() != null) {
            if (req.getQueryString().contains("logout")) {
                req.getSession().setAttribute("user", null);
            }
            if (req.getQueryString().contains("page")) {
                pageNumber = Integer.parseInt(req.getParameter("page"));
            }
        }

        List<Video> listVideos = videoDAO.findByPage(pageNumber, pageSize);
        req.setAttribute("listVideos", listVideos);
        req.setAttribute("pageNumber", pageNumber);
        req.setAttribute("endPage", endPage);

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
            resp.sendRedirect(req.getContextPath() + "/?login=1");
            return;
        }

        if (!user.getPassword().equals(password)) {
            resp.sendRedirect(req.getContextPath() + "/?login=1");
            return;
        }
        req.getSession().setAttribute("user", user);

        String secureURI = (String) req.getSession().getAttribute("secureURI");
        if (secureURI != null) {
            req.getSession().removeAttribute("secureURI");
            resp.sendRedirect(req.getContextPath() + secureURI);
            return;
        }

        doGet(req, resp);
    }
}
