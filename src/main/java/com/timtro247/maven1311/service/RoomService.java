package com.timtro247.maven1311.service;

import com.timtro247.maven1311.model.Response;
import com.timtro247.maven1311.model.Rooms;
import com.timtro247.maven1311.model.TypeRooms;
import com.timtro247.maven1311.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    List<Rooms> findRoomList();
    Optional<Rooms> findRoomById(Long roomId);
    List<Rooms> findRoomByUser(Users user);
    List<Rooms> findRoomByRoomTypeAndArea(Long roomTypeId, Long areaId);
    void deleteRoomById(Long roomId);
    Rooms save(Rooms room, MultipartFile[] files);
    Long countAllByUser(Users user);
    List<Rooms> findRoomByTypeRoom(Long roomTypeId);
    Response<Rooms> findByRoomTypeAndAreaAndPrice(int page, int pageSize,Long roomTypeId, Long areaId, Long priceId, Users user);
    Response<Rooms> findByRoomTypeAndAreaAndPrice(int page, int pageSize,Long roomTypeId, Long areaId, Long priceId);
}
