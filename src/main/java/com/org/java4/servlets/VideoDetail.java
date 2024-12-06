package com.org.java4.servlets;

import com.org.java4.daos.FavoriteDAOImpl;
import com.org.java4.daos.ShareDAOImpl;
import com.org.java4.daos.UsersDAOImpl;
import com.org.java4.daos.VideoDAOImpl;
import com.org.java4.entities.Favorite;
import com.org.java4.entities.Share;
import com.org.java4.entities.Users;
import com.org.java4.entities.Video;
import com.org.java4.utils.Mailer;
import com.org.java4.utils.XDate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static java.lang.Math.ceil;

@WebServlet("/videoDetail")
public class VideoDetail extends HttpServlet {
    VideoDAOImpl videoDAO = new VideoDAOImpl();
    int pageSize = 5;
    int endPage = (int) ceil(0.3 + (double) videoDAO.quantity() / pageSize) - 1;
    Random randomPage = new Random();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Users user = (Users) req.getSession().getAttribute("user");
        if (user != null) {
            req.setAttribute("listLikedVideos", videoDAO.findByFavoriteByUser(user.getId()));
        }
        if (req.getQueryString() != null) {
            if (req.getQueryString().contains("id")) {
                req.setAttribute("video", videoDAO.findById(req.getParameter("id")));

            }
            if (req.getQueryString().contains("logout")) {
                req.getSession().setAttribute("user", null);
                resp.sendRedirect(req.getContextPath() + "/videoDetail?id=" + req.getParameter("id"));
                return;
            }
        }

        List<Video> listVideos = videoDAO.findByPage(randomPage.nextInt(endPage), pageSize);
        req.setAttribute("listVideos", listVideos);

        Video video = videoDAO.findById(req.getParameter("id"));
        video.setViews(video.getViews() + 1);
        videoDAO.update(video);

        req.getRequestDispatcher("/videoDetail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String id = req.getParameter("id");
        Users user;
        boolean isLogin = true;
        user = new UsersDAOImpl().findByIdOrEmail(email);
        if (user == null) {
            isLogin = false;
        }

        if (req.getQueryString() != null) {
            if (req.getQueryString().contains("reg")) {
                if (isLogin) {
                    resp.sendRedirect(req.getContextPath() + "/videoDetail?id=" + req.getParameter("id") + "&reg=0");
                    return;
                }
                else {
                    user = new Users();
                    user.setId(id);
                    user.setPassword(password);
                    user.setFullname(fullname);
                    user.setEmail(email);
                    user.setAdmin(false);
                    new UsersDAOImpl().create(user);
                }
            }

            if (req.getQueryString().contains("share=1")) {
                if (req.getSession().getAttribute("user") != null) {
                    user = (Users) req.getSession().getAttribute("user");
                    String from = user.getEmail();
                    String to = req.getParameter("toEmail");
                    String subject = user.getFullname() + " đã share video này tới bạn!";
                    String body = "Đường dẫn tới link video: " + req.getRequestURL() + "?id=" + req.getParameter("id");

                    Mailer.sendEmail(from, to, subject, body);

                    String date = XDate.toString(new Date(), "yyyyMMddHHmmss");

                    Share share = new Share();
                    share.setId(date);
                    share.setUserid(user);
                    share.setVideoid(new VideoDAOImpl().findById(req.getParameter("id")));
                    share.setEmails(to);
                    share.setSharedate(LocalDate.now());
                    new ShareDAOImpl().create(share);
                }
                else {
                    resp.sendRedirect(req.getContextPath() + "/videoDetail?id=" + req.getParameter("id") + "&login=0");
                    return;
                }
            }

            if (req.getQueryString().contains("like")) {
                if (req.getSession().getAttribute("user") != null) {
                    user = (Users) req.getSession().getAttribute("user");

                    String date = XDate.toString(new Date(), "yyyyMMddHHmmss");

                    Favorite likedVideo = new Favorite();
                    likedVideo.setId(date);
                    likedVideo.setUserid(user);
                    likedVideo.setVideoid(new VideoDAOImpl().findById(req.getParameter("id")));
                    likedVideo.setLikedate(LocalDate.now());
                    new FavoriteDAOImpl().create(likedVideo);
                }
                else {
                    resp.sendRedirect(req.getContextPath() + "/videoDetail?id=" + req.getParameter("id") + "&login=0");
                    return;
                }
            }

            if (req.getQueryString().contains("unlike")) {
                if (req.getSession().getAttribute("user") != null) {
                    user = (Users) req.getSession().getAttribute("user");
                    if (new FavoriteDAOImpl().deleteByVideoIdAndUserId(req.getParameter("id"), user.getId()) > 0) {
                        resp.sendRedirect(req.getContextPath() + "/videoDetail?id=" + req.getParameter("id") + "&unlike=1");
                        return;
                    }
                }
                else {
                    resp.sendRedirect(req.getContextPath() + "/videoDetail?id=" + req.getParameter("id") + "&login=0");
                    return;
                }
            }

        }


        if (isLogin && !user.getPassword().equals(password) || user == null) {
            resp.sendRedirect(req.getContextPath() + "/videoDetail?id=" + req.getParameter("id") + "&login=1");
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
