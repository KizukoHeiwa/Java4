package com.org.java4.servlets;

import com.org.java4.daos.UsersDAOImpl;
import com.org.java4.daos.VideoDAOImpl;
import com.org.java4.entities.Video;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static java.lang.Math.ceil;

@WebServlet("/admin/*")
public class Admin extends HttpServlet {
    VideoDAOImpl videoDAO = new VideoDAOImpl();
    int pageVideoNumber = 0;
    int pageVideoSize = 5;
    int endVideoPage = (int) ceil((double) videoDAO.quantity() / pageVideoSize) - 1;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Video> listVideos = videoDAO.findByPage(pageVideoNumber, pageVideoSize);
        req.setAttribute("listVideos", listVideos);
        req.setAttribute("pageVideoNumber", pageVideoNumber);
        req.setAttribute("endVideoPage", endVideoPage);
        req.setAttribute("listUsers", new UsersDAOImpl().findAll());

        if (req.getRequestURI().contains("edit")) {
            if (req.getQueryString() != null) {
                if (req.getQueryString().contains("video")) {
                    req.setAttribute("videoEdit", new VideoDAOImpl().findById(req.getParameter("video")));
                }
                if (req.getQueryString().contains("user")) {
                    req.setAttribute("userEdit", new UsersDAOImpl().findByIdOrEmail(req.getParameter("user")));
                }
            }
        }

        req.getRequestDispatcher("/admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }
}
