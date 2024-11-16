package com.org.java4.servlets;

import com.org.java4.daos.FavoriteDAOImpl;
import com.org.java4.daos.VideoDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/test")
public class test extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getQueryString() != null) {
            if (req.getParameter("search") == null) {
                req.setAttribute("userFavs", new FavoriteDAOImpl().findByUserId("20241103070122"));
            }
            else if (req.getParameter("search") != null) {
                req.setAttribute("userFavs", new FavoriteDAOImpl().findByUserId(req.getParameter("search")));
            }
            if (req.getParameter("keyword") != null) {
                req.setAttribute("listVideo", new VideoDAOImpl().findByTitle(req.getParameter("keyword")));
            }
        }
        req.setAttribute("listFav", new FavoriteDAOImpl().findAll());
        req.setAttribute("listFavVideo", new VideoDAOImpl().findByFavorite());
//        req.setAttribute("listVideoNoLike", new VideoDAOImpl().findVideoNoLike());
        req.getRequestDispatcher("/test.jsp").forward(req, resp);
    }
}