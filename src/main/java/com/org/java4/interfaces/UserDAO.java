package com.org.java4.interfaces;

import com.org.java4.model.User;

import java.util.List;

public interface UserDAO {
    /**Truy vấn tất cả*/
    List<User> findAll();
    /**Truy vấn theo mã*/
    User findById(String id);
    /**Thêm mới*/
    void create(User item);
    /**Cập nhật*/
    void update(User item);
    /**Xóa theo mã*/
    void deleteById(String id);
    /**Tìm user theo tên và role*/
    List<User> findByFullnameAndRole(String fullname, boolean role);
}
