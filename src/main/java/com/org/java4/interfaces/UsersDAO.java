package com.org.java4.interfaces;

import com.org.java4.entities.Users;

import java.util.List;

public interface UsersDAO {
    /**Truy vấn tất cả*/
    List<Users> findAll();
    /**Truy vấn theo trang*/
    List<Users> findByPage(int pageNumber, int pageSize);
    /**Đếm số lượng video*/
    int quantity();
    /**Truy vấn theo mã*/
    Users findByIdOrEmail(String search);
    /**Thêm mới*/
    void create(Users item);
    /**Cập nhật*/
    void update(Users item);
    /**Xóa theo mã*/
    void deleteById(String id);
}
