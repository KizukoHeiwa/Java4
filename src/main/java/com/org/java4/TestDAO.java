package com.org.java4;

import com.org.java4.daos.FavoriteDAOImpl;
import com.org.java4.daos.VideoDAOImpl;
import com.org.java4.entities.Favorite;
import com.org.java4.interfaces.FavoriteDAO;
import com.org.java4.interfaces.VideoDAO;

public class TestDAO {
    public static void main(String[] args) {
        VideoDAO videoDAO = new VideoDAOImpl();
        FavoriteDAO favoriteDAO = new FavoriteDAOImpl();
//        videoDAO.findByShared().forEach(System.out::println);
//        videoDAO.findFavoriteByVideoId().forEach((k, v) -> System.out.println(k + " " + v));
//        videoDAO.findByFavoriteByUser("20241103070122").forEach(System.out::println);

        for (Favorite f : favoriteDAO.findAll()) {
            System.out.println(f.getVideoid().getTitle());
            System.out.println(f.getUserid().getFullname());
            System.out.println(f.getLikedate());
        }
    }
}
