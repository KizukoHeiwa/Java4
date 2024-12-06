package com.org.java4;

import com.org.java4.daos.FavoriteDAOImpl;
import com.org.java4.daos.UsersDAOImpl;
import com.org.java4.daos.VideoDAOImpl;
import com.org.java4.entities.Favorite;
import com.org.java4.entities.Users;
import com.org.java4.interfaces.FavoriteDAO;
import com.org.java4.interfaces.UsersDAO;
import com.org.java4.interfaces.VideoDAO;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TestDAO {
    public static void main(String[] args) {
        VideoDAO videoDAO = new VideoDAOImpl();
        FavoriteDAO favoriteDAO = new FavoriteDAOImpl();
        UsersDAO usersDAO = new UsersDAOImpl();

        for (Map.Entry<Users, LocalDate> entry : usersDAO.findUserFavoriteVideoID("20241103060110").entrySet()) {
            System.out.println(entry.getKey().getId() + " " + entry.getValue());
        }
//        videoDAO.findByShared().forEach(System.out::println);
//        videoDAO.findFavoriteByVideoId().forEach((k, v) -> System.out.println(k + " " + v));
//        videoDAO.findByFavoriteByUser("20241103070122").forEach(System.out::println);
//
//        for (Favorite f : favoriteDAO.findAll()) {
//            System.out.println(f.getVideoid().getTitle());
//            System.out.println(f.getUserid().getFullname());
//            System.out.println(f.getLikedate());
//        }

//        for (Map.Entry<String, Object[]> map : videoDAO.findFavoriteByVideoId().entrySet()) {
//            System.out.println(map.getKey() + " " + map.getValue()[0] + " " + map.getValue()[1] + " " + map.getValue()[2]);
//        }

//// In the servlet, add the map to the request attributes
//HashMap<String, Object[]> favoriteVideosMap = videoDAO.findFavoriteByVideoId();
//req.setAttribute("favoriteVideosMap", favoriteVideosMap);
//
//// In the JSP file, use the <c:forEach> tag to iterate over the map
//<c:forEach var="entry" items="${favoriteVideosMap}">
//    <c:set var="videoTitle" value="${entry.key}"/>
//    <c:set var="videoStats" value="${entry.value}"/>
//    <p>Title: ${videoTitle}</p>
//    <p>Like count: ${videoStats[0]}</p>
//    <p>Oldest like date: ${videoStats[1]}</p>
//    <p>Newest like date: ${videoStats[2]}</p>
//</c:forEach>
    }
}
