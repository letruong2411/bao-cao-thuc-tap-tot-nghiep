package com.timtro247.maven1311.repository;

import com.timtro247.maven1311.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Rooms, Long> {

    List<Rooms> findAllByTypeRoomAndAreaAndDeletedAtNull(TypeRooms typeRoom, Areas area);

    List<Rooms> findAllByDeletedAtNull();

    List<Rooms> findAllByUserAndDeletedAtNull(Users user);

    List<Rooms> findAllByTypeRoomInAndAreaInAndPriceInAndDeletedAtNull(List<TypeRooms> typeRooms, List<Areas> areas, List<Prices> prices);

    Rooms findByIdAndDeletedAtNull(Long id);

    List<Rooms> findAllByTypeRoomAndDeletedAtNull(TypeRooms typeRooms);

    Long countByUserAndDeletedAtNull(Users user);

    Page<Rooms> findAllByTypeRoomInAndAreaInAndPriceInAndUserAndDeletedAtNull(Pageable pageable,
                                                                              List<TypeRooms> typeRooms,
                                                                              List<Areas> areas,
                                                                              List<Prices> prices,
                                                                              Users user);

    Page<Rooms> findAllByTypeRoomInAndAreaInAndPriceIn(Pageable pageable,
                                                       List<TypeRooms> typeRooms,
                                                       List<Areas> areas,
                                                       List<Prices> prices);

    Page<Rooms> findAllByUserAndDeletedAtNull(Pageable pageable, Users user);
}
