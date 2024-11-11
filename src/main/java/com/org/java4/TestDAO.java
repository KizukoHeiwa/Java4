package com.org.java4;

import com.org.java4.daos.VideoDAOImpl;
import com.org.java4.interfaces.VideoDAO;

public class TestDAO {
    public static void main(String[] args) {
        VideoDAO videoDAO = new VideoDAOImpl();
//        videoDAO.findByShared().forEach(System.out::println);
        videoDAO.findFavoriteByVideoId().forEach((k, v) -> System.out.println(k + " " + v));
    }
}
