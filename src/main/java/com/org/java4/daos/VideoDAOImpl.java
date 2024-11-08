package com.org.java4.daos;

import com.org.java4.entities.Video;
import com.org.java4.interfaces.VideoDAO;

import java.util.List;

public class VideoDAOImpl implements VideoDAO {

    @Override
    public List<Video> findAll() {
        return List.of();
    }

    @Override
    public Video findById(String id) {
        return null;
    }

    @Override
    public void create(Video item) {

    }

    @Override
    public void update(Video item) {

    }

    @Override
    public void deleteById(String id) {

    }
}
