package com.timtro247.maven1311.service.impl;

import com.timtro247.maven1311.model.*;
import com.timtro247.maven1311.repository.PostRepository;
import com.timtro247.maven1311.repository.RoomRepository;
import com.timtro247.maven1311.repository.TypeRoomRepository;
import com.timtro247.maven1311.model.*;
import com.timtro247.maven1311.repository.*;
import com.timtro247.maven1311.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final RoomRepository roomRepository;
    private final TypeRoomRepository typeRoomRepository;
    private final AreaRepository areaRepository;
    private final PriceRepository priceRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, RoomRepository roomRepository, TypeRoomRepository typeRoomRepository, AreaRepository areaRepository, PriceRepository priceRepository) {
        this.postRepository = postRepository;
        this.roomRepository = roomRepository;
        this.typeRoomRepository = typeRoomRepository;
        this.areaRepository = areaRepository;
        this.priceRepository = priceRepository;
    }

    @Override
    public List<Posts> getAllPosts() {
        List<Posts> posts = new ArrayList<>();
        posts = postRepository.findAll();
        return posts;
    }

    @Override
    public Posts save(Posts posts) {
        return postRepository.save(posts);
    }

    @Override
    public List<Posts> filterPost(String typeRoomId, String areaId, String priceId, Integer pageNumber) {
        int pageSize = 9;
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        if (typeRoomId == null) {
            if (areaId == null)
                if (priceId == null)
                    return postRepository.findAll();
                else return postRepository.getPostsByPrice(Integer.parseInt(priceId));
            else if (priceId != null)
                return postRepository.getPostsByPriceAndArea(Integer.parseInt(priceId), Integer.parseInt(areaId));
        }
        if (areaId == null) {
            if (priceId == null)
                if (typeRoomId == null)
                    return postRepository.findAll();
                else return postRepository.getPostsByTypeRoom(Integer.parseInt(typeRoomId));
            else if (typeRoomId != null)
                return postRepository.getPostsByPriceAndTypeRoom(Integer.parseInt(priceId), Integer.parseInt(typeRoomId));
        }
        if (priceId == null) {
            if (typeRoomId == null)
                if (areaId == null)
                    return postRepository.findAll();
                else return postRepository.getPostsByArea(Integer.parseInt(areaId));
            else if (areaId != null)
                return postRepository.getPostsByTypeRoomAndArea(Integer.parseInt(typeRoomId), Integer.parseInt(areaId));
        }
        return postRepository.getPostsByPriceAndTypeRoomAndArea(Integer.parseInt(priceId), Integer.parseInt(typeRoomId), Integer.parseInt(areaId));
    }


    @Override
    public List<Posts> searchPost(String address) {
        return postRepository.getPostsByAddress(address);
    }


    @Override
    public Page<Posts> getPage(Integer pageNumber) {
        int pageSize = 9;
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return postRepository.findAll(pageable);
    }

    @Override
    public List<Posts> getSubList(List<Posts> postsList, int pageNumber) {
        int sizePage = 9;
        int start = sizePage * (pageNumber - 1);
        int end = start + sizePage;
        if (end > (postsList.size() - 1))
            end = postsList.size();
        return postsList.subList(start, end);
    }
    @Override
    public Posts findById(Long id) {
        return postRepository.findByIdAndDeletedAtNull(id);
    }

    @Override
    public List<Posts> findListPostByTypeRoom(Long typeRoomId) {
        TypeRooms typeRoom = typeRoomRepository.findByIdAndDeletedAtNull(typeRoomId);
        List<Rooms> rooms = roomRepository.findAllByTypeRoomAndDeletedAtNull(typeRoom);
        List<Posts> posts = postRepository.findAllByRoomInAndDeletedAtNull(rooms);
        return  posts;
    }
    @Override
    public Response<Posts> getPostsList(int page, int pageSize, Long areaId, Long roomTypeId, Long priceId) {
        Pageable pageable = getPagination(page, pageSize);
        List<Areas> areas;
        List<TypeRooms> typeRooms;
        List<Prices> prices;
        List<Rooms> rooms;
        Page<Posts> postsPage;
        if(areaId == 0 && roomTypeId == 0 && priceId == 0) {
            postsPage = postRepository.findAll(pageable);
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
            rooms = roomRepository.findAllByTypeRoomInAndAreaInAndPriceInAndDeletedAtNull(typeRooms, areas, prices);
            postsPage = postRepository.findAllByRoomIn(pageable, rooms);
        }
        return new Response<>(postsPage.getContent(), new Pagination(page, postsPage.getTotalPages()));
    }

    @Override
    public Response<Posts> getPostsList(int page, int pageSize, Long areaId, Long roomTypeId, Long priceId, Users user) {
        Pageable pageable = getPagination(page, pageSize);
        List<Areas> areas;
        List<TypeRooms> typeRooms;
        List<Prices> prices;
        List<Rooms> rooms;
        Page<Posts> postsPage;
        if(areaId == 0 && roomTypeId == 0 && priceId == 0) {
            postsPage = postRepository.findAllByUserAndDeletedAtNull(pageable, user);
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
            rooms = roomRepository.findAllByTypeRoomInAndAreaInAndPriceInAndDeletedAtNull(typeRooms, areas, prices);
            postsPage = postRepository.findAllByRoomInAndUserAndDeletedAtNull(pageable, rooms, user);
        }
        return new Response<>(postsPage.getContent(), new Pagination(page, postsPage.getTotalPages()));
    }

    @Override
    public void delete(Long id) {
        Posts post = postRepository.findByIdAndDeletedAtNull(id);
        post.setDeletedAt(LocalDateTime.now());
        postRepository.save(post);
    }

    @Override
    public Posts getPost(Long id) {
        return postRepository.findByIdAndDeletedAtNull(id);
    }

    @Override
    public Long CountAllByUser(Users user) {
        return postRepository.countByUserAndDeletedAtNull(user);
    }

    public Pageable getPagination(int page, int pageSize) {
        return PageRequest.of((page>0)?--page:0, pageSize);
    }
}
