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

@WebServlet("/videoDetail")
public class VideoDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getQueryString() != null) {
            if (req.getQueryString().contains("id")) {
                req.setAttribute("video", new VideoDAOImpl().findById(req.getParameter("id")));
            }
            if (req.getQueryString().contains("logout")) {
                req.getSession().setAttribute("user", null);
            }
        }

        List<Video> listVideos = new VideoDAOImpl().findByPage(1, 4);
        req.setAttribute("listVideos", listVideos);

        req.getRequestDispatcher("/videoDetail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String id = req.getParameter("id");
        Users user = new Users();
        boolean isLogin = true;
        try {
            user = new UsersDAOImpl().findByIdOrEmail(email);
        } catch (Exception e) {
            isLogin = false;
        }

        if (req.getQueryString() != null) {
            if (req.getQueryString().contains("reg")) {
                if (isLogin) {
                    resp.sendRedirect(req.getContextPath() + "/?reg=0");
                    return;
                }
                else {
                    user.setId(id);
                    user.setPassword(password);
                    user.setFullname(fullname);
                    user.setEmail(email);
                    user.setAdmin(false);
                    new UsersDAOImpl().create(user);
                }
            }
        }


        if (isLogin && !user.getPassword().equals(password)) {
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
