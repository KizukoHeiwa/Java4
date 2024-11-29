package com.org.java4.servlets;

import com.org.java4.daos.UsersDAOImpl;
import com.org.java4.daos.VideoDAOImpl;
import com.org.java4.entities.Users;
import com.org.java4.entities.Video;
import com.org.java4.utils.XDate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static java.lang.Math.ceil;

@WebServlet("/admin/*")
public class Admin extends HttpServlet {
    VideoDAOImpl videoDAO = new VideoDAOImpl();
    UsersDAOImpl usersDAO = new UsersDAOImpl();

    List<Video> listVideos;
    List<Users> listUsers;


    int pageVideoNumber = 0;
    int pageVideoSize = 10;
    int endVideoPage = (int) ceil(0.3 + (double) videoDAO.quantity() / pageVideoSize) - 1;

    int pageUserNumber = 0;
    int pageUserSize = 10;
    int endUserPage = (int) ceil(0.3 + (double) usersDAO.quantity() / pageUserSize) - 1;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getQueryString() != null) {
            if (req.getQueryString().contains("videoPage")) {
                pageVideoNumber = Integer.parseInt(req.getParameter("videoPage"));
            }
            if (req.getQueryString().contains("userPage")) {
                pageUserNumber = Integer.parseInt(req.getParameter("userPage"));
            }
        }

        req.setAttribute("listVideoLikeNum", videoDAO.findFavoriteByVideoId());

        listVideos = videoDAO.findByPage(pageVideoNumber, pageVideoSize);
        req.setAttribute("quantity", videoDAO.quantity());
        req.setAttribute("listVideos", listVideos);
        req.setAttribute("pageVideoNumber", pageVideoNumber);
        req.setAttribute("endVideoPage", endVideoPage);

        listUsers = usersDAO.findByPage(pageUserNumber, pageUserSize);
        req.setAttribute("usersNumber", usersDAO.quantity());
        req.setAttribute("listUsers", listUsers);
        req.setAttribute("pageUserNumber", pageUserNumber);
        req.setAttribute("endUserPage", endUserPage);

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
        if (req.getRequestURI().contains("video")) {
            try {
                if (req.getRequestURI().contains("create")) {
                    String date = XDate.toString(new Date(), "yyyyMMddHHmmss");
                    String title = req.getParameter("title");
                    String poster = req.getParameter("poster");
                    String description = req.getParameter("description");
                    boolean active = req.getParameter("active") != null;

                    Video video = new Video();
                    video.setId(date);
                    video.setTitle(title);
                    video.setPoster(poster);
                    video.setViews(0);
                    video.setDescription(description);
                    video.setActive(active);
                    videoDAO.create(video);

                    resp.sendRedirect(req.getContextPath() + "/admin/edit?video=" + video.getId());
                    return;
                }

                if (req.getRequestURI().contains("update")) {
                    String id = req.getParameter("id");
                    String title = req.getParameter("title");
                    String poster = req.getParameter("poster");
                    String description = req.getParameter("description");
                    boolean active = req.getParameter("active") != null;

                    Video video = new Video();
                    video.setId(id);
                    video.setTitle(title);
                    video.setPoster(poster);
                    video.setViews(videoDAO.findById(id).getViews());
                    video.setDescription(description);
                    video.setActive(active);
                    videoDAO.update(video);

                    resp.sendRedirect(req.getContextPath() + "/admin/edit?video=" + video.getId());
                    return;
                }

                if (req.getRequestURI().contains("delete")) {
                    String id = req.getParameter("id");
                    videoDAO.deleteById(id);

                    resp.sendRedirect(req.getContextPath() + "/admin/edit?video");
                    return;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                pageVideoNumber = 0;
                endVideoPage = (int) ceil(0.3 + (double) videoDAO.quantity() / pageVideoSize) - 1;
            }
        }

        if (req.getRequestURI().contains("user")) {
            if (usersDAO.findByIdOrEmail(req.getParameter("id")) == null) {
                resp.sendRedirect(req.getContextPath() + "/admin/edit?user=error");
                return;
            }
            try {
                if (req.getRequestURI().contains("update")) {
                    String id = req.getParameter("id");
                    String fullname = req.getParameter("fullname");
                    String email = req.getParameter("email");
                    String password = req.getParameter("password");
                    boolean admin = req.getParameter("admin").equals("admin");

                    Users user = new Users();
                    user.setId(id);
                    user.setFullname(fullname);
                    user.setEmail(email);
                    user.setPassword(password);
                    user.setAdmin(admin);
                    usersDAO.update(user);

                    resp.sendRedirect(req.getContextPath() + "/admin/edit?user=" + user.getId());
                    return;
                }

                if (req.getRequestURI().contains("delete")) {
                    String id = req.getParameter("id");
                    usersDAO.deleteById(id);

                    resp.sendRedirect(req.getContextPath() + "/admin/edit?user");
                    return;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                pageUserNumber = 0;
                endUserPage = (int) ceil(0.3 + (double) usersDAO.quantity() / pageUserSize) - 1;
            }
        }

        doGet(req, resp);
    }
}
