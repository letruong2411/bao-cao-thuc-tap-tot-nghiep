package com.timtro247.maven1311.repository;

import com.timtro247.maven1311.model.Posts;
import com.timtro247.maven1311.model.Rooms;
import com.timtro247.maven1311.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PostRepository extends JpaRepository<Posts,Long> {

    Long countByUserAndDeletedAtNull(Users user);
    Page<Posts> findAllByRoomIn(Pageable pageable, List<Rooms> rooms);
    Page<Posts> findAllByRoomInAndUserAndDeletedAtNull(Pageable pageable, List<Rooms> rooms, Users user);
    Page<Posts> findAllByDeletedAtNull(Pageable pageable);
    Page<Posts> findAllByUserAndDeletedAtNull(Pageable pageable, Users user);
    Posts findByIdAndDeletedAtNull(Long id);

    @Query(value = "select posts.id,posts.updated_by,posts.created_at,posts.created_by,posts.deleted_at,posts.updated_at,posts.content,posts.save_post_day_number,posts.start_time,posts.title,posts.room_id,posts.user_id from posts inner join rooms on posts.room_id=rooms.id inner join prices on prices.id=rooms.price_id inner join type_rooms on type_rooms.id=rooms.type_room_id inner join areas on areas.id=rooms.area_id where price_id=?1 and type_room_id=?2 and area_id=?3 and Posts.deleted_at is null and Posts.deleted_at is null", nativeQuery = true)
    List<Posts> getPostsByPriceAndTypeRoomAndArea(Integer price_id, Integer type_room_id, Integer area_id);

    @Query(value = "select posts.id,posts.updated_by,posts.created_at,posts.created_by,posts.deleted_at,posts.updated_at,posts.content,posts.save_post_day_number,posts.start_time,posts.title,posts.room_id,posts.user_id from posts inner join rooms on posts.room_id=rooms.id inner join prices on prices.id=rooms.price_id inner join type_rooms on type_rooms.id=rooms.type_room_id inner join areas on areas.id=rooms.area_id where price_id=?1 and Posts.deleted_at is null", nativeQuery = true)
    List<Posts> getPostsByPrice(Integer price_id);

    @Query(value = "select posts.id,posts.updated_by,posts.created_at,posts.created_by,posts.deleted_at,posts.updated_at,posts.content,posts.save_post_day_number,posts.start_time,posts.title,posts.room_id,posts.user_id from posts inner join rooms on posts.room_id=rooms.id inner join prices on prices.id=rooms.price_id inner join type_rooms on type_rooms.id=rooms.type_room_id inner join areas on areas.id=rooms.area_id where type_room_id=?1 and Posts.deleted_at is null", nativeQuery = true)
    List<Posts> getPostsByTypeRoom(Integer type_room_id);

    @Query(value = "select posts.id,posts.updated_by,posts.created_at,posts.created_by,posts.deleted_at,posts.updated_at,posts.content,posts.save_post_day_number,posts.start_time,posts.title,posts.room_id,posts.user_id from posts inner join rooms on posts.room_id=rooms.id inner join prices on prices.id=rooms.price_id inner join type_rooms on type_rooms.id=rooms.type_room_id inner join areas on areas.id=rooms.area_id where area_id=?1 and Posts.deleted_at is null", nativeQuery = true)
    List<Posts> getPostsByArea(Integer area_id);

    @Query(value = "select posts.id,posts.updated_by,posts.created_at,posts.created_by,posts.deleted_at,posts.updated_at,posts.content,posts.save_post_day_number,posts.start_time,posts.title,posts.room_id,posts.user_id from posts inner join rooms on posts.room_id=rooms.id inner join prices on prices.id=rooms.price_id inner join type_rooms on type_rooms.id=rooms.type_room_id inner join areas on areas.id=rooms.area_id where price_id=?1 and type_room_id=?2 and Posts.deleted_at is null", nativeQuery = true)
    List<Posts> getPostsByPriceAndTypeRoom(Integer price_id, Integer type_room_id);

    @Query(value = "select posts.id,posts.updated_by,posts.created_at,posts.created_by,posts.deleted_at,posts.updated_at,posts.content,posts.save_post_day_number,posts.start_time,posts.title,posts.room_id,posts.user_id from posts inner join rooms on posts.room_id=rooms.id inner join prices on prices.id=rooms.price_id inner join type_rooms on type_rooms.id=rooms.type_room_id inner join areas on areas.id=rooms.area_id where type_room_id=?1 and area_id=?2 and Posts.deleted_at is null", nativeQuery = true)
    List<Posts> getPostsByTypeRoomAndArea(Integer type_room_id, Integer area_id);

    @Query(value = "select posts.id,posts.updated_by,posts.created_at,posts.created_by,posts.deleted_at,posts.updated_at,posts.content,posts.save_post_day_number,posts.start_time,posts.title,posts.room_id,posts.user_id from posts inner join rooms on posts.room_id=rooms.id inner join prices on prices.id=rooms.price_id inner join type_rooms on type_rooms.id=rooms.type_room_id inner join areas on areas.id=rooms.area_id where price_id=?1 and area_id=?2 and Posts.deleted_at is null", nativeQuery = true)
    List<Posts> getPostsByPriceAndArea(Integer price_id, Integer area_id);

    //search
    @Query(value = "select posts.id,posts.updated_by,posts.created_at,posts.created_by,posts.deleted_at,posts.updated_at,posts.content,posts.save_post_day_number,posts.start_time,posts.title,posts.room_id,posts.user_id from posts inner join rooms on posts.room_id=rooms.id where rooms.address like %?1% and Posts.deleted_at is null", nativeQuery = true)
    List<Posts> getPostsByAddress(String address);

    List<Posts> findAllByRoomInAndDeletedAtNull(List<Rooms> rooms);
}
