package com.org.java4.servlets;

import com.org.java4.daos.UsersDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/", "/index"})
public class Index extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getQueryString() != null) {
            if (req.getQueryString().contains("logout")) {
                req.getSession().setAttribute("user", null);
            }
        }
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (new UsersDAOImpl().findByIdOrEmail(email) != null && new UsersDAOImpl().findByIdOrEmail(email).getPassword().equals(password)) {
            req.getSession().setAttribute("user", new UsersDAOImpl().findByIdOrEmail(email));
        }

        String secureURI = (String) req.getSession().getAttribute("secureURI");
        if (secureURI != null) {
            req.getSession().removeAttribute("secureURI");
            resp.sendRedirect(req.getContextPath() + secureURI);
            return;
        }

        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
