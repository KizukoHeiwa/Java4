package com.org.java4.interfaces;

import com.org.java4.entities.Share;

import java.util.List;

public interface ShareDAO {
    /**Truy vấn tất cả*/
    List<Share> findAll();
    /**Truy vấn theo mã*/
    Share findById(String id);
    /**Thêm mới*/
    void create(Share item);
    /**Cập nhật*/
    void update(Share item);
    /**Xóa theo mã*/
    void deleteById(String id);
    /**Truy vấn các share theo video ?*/
    List<Share> findByVideoId(String videoId);
}
