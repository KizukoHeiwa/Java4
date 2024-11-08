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
    /**Truy vấn các Video sắp xếp giảm theo Views*/
    List<Video> findByViewsDescending();
    /**Truy vấn các Video mà tiêu đề chứa ...*/
    List<Video> findByTitleContaining(String keyword);
    /**Truy vấn các Video được yêu thích*/
    List<String> findByFavorite();
    /**Truy vấn các Video được share*/
    List<Video> findByShared();
    /**Truy vấn các Video được User ? yêu thích*/
    List<Video> findByLikedByUser(String userId);
    /**Truy vấn các Video được share bởi User ?*/
    List<Video> findBySharedByUserId(String userId);
    /**Truy vấn các lượt like của từng Video */
    List<Integer> findLikesByVideoId(String videoId);
}
