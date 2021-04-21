package com.timtro247.maven1311.service.impl;

import com.timtro247.maven1311.model.*;
import com.timtro247.maven1311.repository.*;
import com.timtro247.maven1311.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final TypeRoomRepository typeRoomRepository;
    private final AreaRepository areaRepository;
    private final ImageRepository imageRepository;
    private final PriceRepository priceRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, TypeRoomRepository typeRoomRepository, AreaRepository areaRepository, ImageRepository imageRepository, PriceRepository priceRepository) {
        this.roomRepository = roomRepository;
        this.typeRoomRepository = typeRoomRepository;
        this.areaRepository = areaRepository;
        this.imageRepository = imageRepository;
        this.priceRepository = priceRepository;
    }

    @Override
    public List<Rooms> findRoomList() {
        return roomRepository.findAllByDeletedAtNull();
    }

    @Override
    public Optional<Rooms> findRoomById(Long roomId){
        return roomRepository.findById(roomId);
    }

    @Override
    public List<Rooms> findRoomByUser(Users user) {
        return roomRepository.findAllByUserAndDeletedAtNull(user);
    }

    @Override
    public List<Rooms> findRoomByRoomTypeAndArea(Long roomTypeId, Long areaId) {
        TypeRooms typeRoom = typeRoomRepository.findByIdAndDeletedAtNull(roomTypeId);
        Areas area  = areaRepository.findByIdAndDeletedAtNull(areaId);
        //TypeRooms typeRoom = typeRoomRepository.findByTypeNameAndDeletedAtNull(roomType.trim());
        //Areas area = areaRepository.findByAreaName(areaName);
        return  roomRepository.findAllByTypeRoomAndAreaAndDeletedAtNull(typeRoom, area);
    }

    @Override
    public void deleteRoomById(Long roomId) {
        Rooms room = roomRepository.findByIdAndDeletedAtNull(roomId);
        room.setDeletedAt(LocalDateTime.now());
        roomRepository.save(room);
    }

    @Override
    public Rooms save(Rooms room, MultipartFile[] files) {
        Prices price = getCostRange(room.getCost());
        room.setPrice(price);
        roomRepository.save(room);
        try {
            for (MultipartFile f: files) {
                Images image = new Images();
                image.setFileName(f.getOriginalFilename());
                FileOutputStream fos = new FileOutputStream("src/main/resources/static/web/images/"+f.getOriginalFilename());
                byte[] imageData = f.getBytes();
                fos.write(imageData);
                image.setRoom(room);
                imageRepository.save(image);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return room;
    }

    @Override
    public List<Rooms> findRoomByTypeRoom(Long roomTypeId) {
        TypeRooms typeRoom = typeRoomRepository.findByIdAndDeletedAtNull(roomTypeId);
        return  roomRepository.findAllByTypeRoomAndDeletedAtNull(typeRoom);
    }

    @Override
    public Response<Rooms> findByRoomTypeAndAreaAndPrice(int page, int pageSize,Long roomTypeId, Long areaId, Long priceId, Users user) {
        Pageable pageable = getPagination(page, pageSize);
        List<Areas> areas;
        List<TypeRooms> typeRooms;
        List<Prices> prices;
        Page<Rooms> roomsPage;
        if(areaId == 0 && roomTypeId == 0 && priceId == 0) {
            roomsPage = roomRepository.findAllByUserAndDeletedAtNull(pageable, user);
        } else {
            if (areaId == 0) {
                areas = areaRepository.findAllByDeletedAtNull();
            } else {
                areas = new ArrayList<>();
                Areas area = areaRepository.findByIdAndDeletedAtNull(areaId);
                areas.add(area);
            }
            if (roomTypeId == 0) {
                typeRooms = typeRoomRepository.findAllByDeletedAtNull();
            } else {
                typeRooms = new ArrayList<>();
                TypeRooms typeRoom = typeRoomRepository.findByIdAndDeletedAtNull(roomTypeId);
                typeRooms.add(typeRoom);
            }
            if (priceId == 0) {
                prices = priceRepository.findAllByDeletedAtNull();
            } else {
                prices = new ArrayList<>();
                Prices price = priceRepository.findByIdAndDeletedAtNull(priceId);
                prices.add(price);
            }
            roomsPage = roomRepository.findAllByTypeRoomInAndAreaInAndPriceInAndUserAndDeletedAtNull(pageable,typeRooms, areas, prices, user);
        }
        return new Response<>(roomsPage.getContent(), new Pagination(page, roomsPage.getTotalPages()));
    }

    @Override
    public Response<Rooms> findByRoomTypeAndAreaAndPrice(int page, int pageSize, Long roomTypeId, Long areaId, Long priceId) {
        Pageable pageable = getPagination(page, pageSize);
        List<Areas> areas;
        List<TypeRooms> typeRooms;
        List<Prices> prices;
        Page<Rooms> roomsPage;
        if(areaId == 0 && roomTypeId == 0 && priceId == 0) {
            roomsPage = roomRepository.findAll(pageable);
        } else {
            if (areaId == 0) {
                areas = areaRepository.findAllByDeletedAtNull();
            } else {
                areas = new ArrayList<>();
                Areas area = areaRepository.findByIdAndDeletedAtNull(areaId);
                areas.add(area);
            }
            if (roomTypeId == 0) {
                typeRooms = typeRoomRepository.findAllByDeletedAtNull();
            } else {
                typeRooms = new ArrayList<>();
                TypeRooms typeRoom = typeRoomRepository.findByIdAndDeletedAtNull(roomTypeId);
                typeRooms.add(typeRoom);
            }
            if (priceId == 0) {
                prices = priceRepository.findAllByDeletedAtNull();
            } else {
                prices = new ArrayList<>();
                Prices price = priceRepository.findByIdAndDeletedAtNull(priceId);
                prices.add(price);
            }
            roomsPage = roomRepository.findAllByTypeRoomInAndAreaInAndPriceIn(pageable,typeRooms,areas, prices);
        }
        return new Response<>(roomsPage.getContent(), new Pagination(page, roomsPage.getTotalPages()));
    }


    @Override
    public Long countAllByUser(Users user) {
        return roomRepository.countByUserAndDeletedAtNull(user);
    }

    public Pageable getPagination(int page, int pageSize) {
        return PageRequest.of((page>0)?--page:0, pageSize);
    }

    public Prices getCostRange(int cost) {
        List<Prices> prices = priceRepository.findAll();
        for(int i = 0; i < prices.size(); i++) {
            if(prices.get(i).getMinPrice() <= cost && prices.get(i).getMaxPrice() > cost) {
                return prices.get(i);
            }
        }
        return prices.get(prices.size() - 1);
    }
}
