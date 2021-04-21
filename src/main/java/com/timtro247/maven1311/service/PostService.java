package com.timtro247.maven1311.service;

import com.timtro247.maven1311.model.Posts;
import com.timtro247.maven1311.model.TypeRooms;
import com.timtro247.maven1311.model.Response;
import com.timtro247.maven1311.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface PostService {

    List<Posts> getAllPosts();
    Posts save(Posts posts);
    Long CountAllByUser(Users user);
    Page<Posts> getPage(Integer pageNumber);
    List<Posts> filterPost(String typeRoomId, String areaId, String priceId,Integer pageNumber);
    List<Posts> searchPost(String address);
    List<Posts> getSubList(List<Posts> postsList,int pageNumber);
    Posts findById(Long id);
    List<Posts> findListPostByTypeRoom(Long typeRoomId);
    Response<Posts> getPostsList(int page, int pageSize, Long areaId, Long roomTypeId, Long priceId);
    Response<Posts> getPostsList(int page, int pageSize, Long areaId, Long roomTypeId, Long priceId, Users user);
    void delete(Long id);
    Posts getPost(Long id);
}
