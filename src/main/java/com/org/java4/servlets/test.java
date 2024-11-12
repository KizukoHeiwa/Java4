package com.org.java4.servlets;

import com.org.java4.daos.FavoriteDAOImpl;
import com.org.java4.daos.UsersDAOImpl;
import com.org.java4.daos.VideoDAOImpl;
import jakarta.persistence.Tuple;
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
        req.setAttribute("userFavs", new FavoriteDAOImpl().findByUserId("20241103070122"));
        req.setAttribute("listFav", new FavoriteDAOImpl().findAll());
        req.getRequestDispatcher("/test.jsp").forward(req, resp);
    }
}