package com.org.java4.servlet;

import com.org.java4.daos.UserDAOImpl;
import com.org.java4.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@WebServlet({"/user/crud/index",
        "/user/crud/edit/*",
        "/user/crud/create",
        "/user/crud/update",
        "/user/crud/delete",
        "/user/crud/reset",
        "/user/crud/search"})
public class lab2 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAOImpl userDAO = new UserDAOImpl();
        User form = new User();
        try {
            BeanUtils.populate(form, req.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        String message = "Enter user information";
        String path = req.getServletPath();
        if (path.contains("edit")) {
            String id = req.getPathInfo().substring(1);
            form = userDAO.findById(id);
            message = "Edit: " + id;
        } else if (path.contains("create")) {
            message = "Create: " + form.getId();
            if (userDAO.findById(form.getId()) == null) {
                userDAO.create(form);
                form = new User();
            }
            else {
                message = "Duplicate user id";
            }
        } else if (path.contains("update")) {
            message = "Update: " + form.getId();
            userDAO.update(form);
//            form = new User();
        } else if (path.contains("delete")) {
            message = "Delete: " + form.getId();
            userDAO.deleteById(form.getId());
            form = new User();
        }
        else if (path.contains("reset")) {
            form = new User();
        } else if (path.contains("search")) {
            form = new User();
            String search = req.getParameter("search");
            boolean searchRole = Boolean.parseBoolean(req.getParameter("searchRole"));
            List<User> users = userDAO.findByFullnameAndRole(search, searchRole);
            req.setAttribute("users", users);
            req.setAttribute("message", message);
            req.getRequestDispatcher("/lab2.jsp").forward(req, resp);
            return;
        }
        List<User> list = userDAO.findAll();
        req.setAttribute("message", message);
        req.setAttribute("user", form);
        req.setAttribute("users", list);
        req.getRequestDispatcher("/lab2.jsp").forward(req, resp);
    }
}
