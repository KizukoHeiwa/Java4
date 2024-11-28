package com.org.java4.servlets;

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

@WebServlet("/likedVideos")
public class LikedVideos extends HttpServlet {
    VideoDAOImpl videoDAO = new VideoDAOImpl();
    int pageNumber = 0;
    int pageSize = 6;
    int endPage = 0;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("user");

        endPage = (int) ceil(0.5 + (double) videoDAO.findByFavoriteByUser(user.getId()).size() / pageSize) - 1;

        if (req.getQueryString() != null) {
            if (req.getQueryString().contains("logout")) {
                req.getSession().setAttribute("user", null);
                resp.sendRedirect("/");
                return;
            }
            if (req.getQueryString().contains("page")) {
                pageNumber = Integer.parseInt(req.getParameter("page"));
            }
        }

        List<Video> listVideos = videoDAO.findByFavoriteByUserPaged(user.getId(), pageNumber, pageSize);
        req.setAttribute("listVideos", listVideos);
        req.setAttribute("pageNumber", pageNumber);
        req.setAttribute("endPage", endPage);

        req.getRequestDispatcher("/likedVideos.jsp").forward(req, resp);
    }
}
