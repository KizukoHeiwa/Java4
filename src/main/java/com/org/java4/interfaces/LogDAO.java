package com.org.java4.interfaces;

import com.org.java4.entities.Log;

import java.util.List;

public interface LogDAO {
    /**Truy vấn tất cả*/
    List<Log> findAll();
    /**Truy vấn theo mã*/
    Log findById(int id);
    /**Thêm mới*/
    void create(Log item);
    /**Cập nhật*/
    void update(Log item);
    /**Xóa theo mã*/
    void deleteById(int id);
}
