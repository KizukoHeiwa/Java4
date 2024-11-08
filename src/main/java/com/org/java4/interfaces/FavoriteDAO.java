package com.org.java4.interfaces;

import com.org.java4.entities.Favorite;

import java.util.List;

public interface FavoriteDAO {
    /**Truy vấn tất cả*/
    List<Favorite> findAll();
    /**Truy vấn theo mã*/
    Favorite findById(String id);
    /**Thêm mới*/
    void create(Favorite item);
    /**Cập nhật*/
    void update(Favorite item);
    /**Xóa theo mã*/
    void deleteById(String id);
}
