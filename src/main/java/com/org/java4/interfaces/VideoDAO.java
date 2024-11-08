package com.org.java4.interfaces;

import com.org.java4.entities.Video;

import java.util.List;

public interface VideoDAO {
    /**Truy vấn tất cả*/
    List<Video> findAll();
    /**Truy vấn theo mã*/
    Video findById(String id);
    /**Thêm mới*/
    void create(Video item);
    /**Cập nhật*/
    void update(Video item);
    /**Xóa theo mã*/
    void deleteById(String id);
}
