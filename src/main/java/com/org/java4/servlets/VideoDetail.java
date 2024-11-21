package com.org.java4.servlets;

import com.org.java4.daos.VideoDAOImpl;
import com.org.java4.entities.Video;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/videoDetail")
public class VideoDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getQueryString() != null) {
            if (req.getQueryString().contains("id")) {
                req.setAttribute("video", new VideoDAOImpl().findById(req.getParameter("id")));
            }
        }

        List<Video> listVideos = new VideoDAOImpl().findByPage(1, 4);
        req.setAttribute("listVideos", listVideos);

        req.getRequestDispatcher("/videoDetail.jsp").forward(req, resp);
    }
}
