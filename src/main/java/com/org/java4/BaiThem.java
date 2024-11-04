package com.org.java4;

import com.org.java4.entities.UserManager;
import com.org.java4.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static java.lang.Math.ceil;

@WebServlet({"/baiThemLab1/*"})
public class BaiThem extends HttpServlet {
    UserManager userManager = new UserManager();
    int pageNumber = 0;
    int pageSize = 5;
    int endPage = (int) ceil(userManager.countAllUsers() / pageSize) - 1;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> listUsers = userManager.findWithPage(pageNumber, pageSize);
        req.setAttribute("listUsers", listUsers);
        req.setAttribute("pageNumber", pageNumber);
        req.setAttribute("endPage", endPage);
        req.getRequestDispatcher("/baiThemLab1.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        pageNumber = Integer.parseInt(req.getParameter("page"));
        doGet(req, resp);
    }
}
