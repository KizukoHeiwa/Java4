package com.org.java4.interfaces;

import com.org.java4.entities.Video;

import java.util.HashMap;
import java.util.List;

public interface VideoDAO {
    /**Truy vấn tất cả*/
    List<Video> findAll();
    /**Truy vấn video active theo trang*/
    List<Video> findByPage(int pageNumber, int pageSize);
    /**Truy vấn tất cả theo trang*/
    List<Video> findAllByPage(int pageNumber, int pageSize);
    /**Đếm số lượng video*/
    int quantity();
    /**Truy vấn theo mã*/
    Video findById(String id);
    /**Tìm video theo keyword Title*/
    List<Video> findByTitle(String keyword);
    /**Thêm mới*/
    void create(Video item);
    /**Cập nhật*/
    void update(Video item);
    /**Xóa theo mã*/
    void deleteById(String id);
    /**Truy vấn các Video sắp xếp giảm theo Views*/
    List<Video> findByViewsDescending();
    /**Truy vấn các Video mà tiêu đề chứa ...*/
    List<Video> findByTitleContaining(String keyword);
    /**Truy vấn các Video được yêu thích*/
    List<Video> findByFavorite();
    /**Truy vấn các Video được share*/
    List<Video> findByShared();
    /**Truy vấn các Video được User ? yêu thích*/
    List<Video> findByFavoriteByUser(String userId);
    List<Video> findByFavoriteByUserPaged(String userId, int pageNumber, int pageSize);
    /**Truy vấn các Video được share bởi User ?*/
    List<Video> findBySharedByUserId(String userId);
    /** Thống kê lượng like của từng Video */
    HashMap<String, Object[]> findFavoriteByVideoId();
    /**Select oldest date by id*/
    String findOldestLikeDate(String videoId);
    /**Select latest date by id*/
    String findNewestLikeDate(String videoId);
    /** Truy vấn video không được ai thích*/
    List<Video> findVideoNoLike();
}
