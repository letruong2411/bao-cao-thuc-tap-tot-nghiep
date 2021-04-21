package com.timtro247.maven1311.controller.web;

import com.timtro247.maven1311.model.*;
import com.timtro247.maven1311.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@CrossOrigin
@Controller
public class PostController {

    private final PostService postService;
    private final TypeRoomService typeRoomsService;
    private final UserService userService;
    private final RoomService roomService;
    private final AreaService areasService;
    private final PriceService priceService;

    @Autowired
    public PostController(PostService postService, TypeRoomService typeRoomService, UserService userService, RoomService roomService, AreaService areasService, PriceService priceService) {
        this.postService = postService;
        this.roomService = roomService;
        this.userService = userService;
        this.typeRoomsService = typeRoomService;
        this.areasService = areasService;
        this.priceService = priceService;
    }

    @GetMapping("post/{id}")
    public String getPostDetail( @PathVariable("id") Long id, @RequestParam(value = "room_type_id", defaultValue = "0") Long roomTypeId, Model model, Principal principal) {
        if(principal != null) {
            Users user = userService.findByUsername(principal.getName());
            Long ID=user.getId();
            model.addAttribute("USERMODEL", user);
            model.addAttribute("id", ID);
        }
        List<Areas> areasList = areasService.getAllAreas();
        List<TypeRooms> typeRoomsList = typeRoomsService.findAllTypeRooms();
        List<Prices> pricesList = priceService.getAllPrices();
        model.addAttribute("areasList", areasList);
        model.addAttribute("typeRoomsList", typeRoomsList);
        model.addAttribute("pricesList", pricesList);


        Posts post = postService.findById(id);
            model.addAttribute("POSTMODEL",post);
            return "/web/detail";

    }
    @GetMapping("/createPost")
    public String goToCreatePage(@RequestParam(name = "idUser") Long idUser, Model model, Principal principal) {
        if(principal != null) {
            Users user = userService.findByUsername(principal.getName());
            Long ID=user.getId();
            model.addAttribute("id", ID);
        }
        Users user = userService.findByUsername(principal.getName());
        List<Rooms> roomsList = roomService.findRoomByUser(userService.findById(idUser));
        model.addAttribute("USERMODEL", user);
        model.addAttribute("roomList", roomsList);
        return "/web/create_post.html";
    }

    @PostMapping("/createPost/{idUser}")
    public String createPost(@PathVariable("idUser") Long idUser, Model model, HttpServletRequest request,Principal principal) {
        if(principal != null) {
            Users user = userService.findByUsername(principal.getName());
            Long ID=user.getId();
            model.addAttribute("id", ID);
        }
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String roomId = request.getParameter("roomId");
        Optional<Rooms> room = roomService.findRoomById(Long.parseLong(roomId));
        String savePostDayNumber = request.getParameter("savePostDayNumber");
        Users user = userService.findById(idUser);
        Posts post = new Posts(title, content, null, Integer.parseInt(savePostDayNumber), user, null, room.get(), null);
        postService.save(post);
        return "redirect:/";
    }

    @GetMapping("/post")
    public String getPostsList(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
                               @RequestParam(name = "area_id", defaultValue = "0") Long areaId,
                               @RequestParam(name = "room_type_id", defaultValue = "0") Long roomTypeId,
                               @RequestParam(name = "price_id", defaultValue = "0") Long priceId,
                               Model model, Principal principal) {
        if(principal != null) {
            Users user = userService.findByUsername(principal.getName());
            Long ID=user.getId();
            model.addAttribute("id", ID);
        }
        Users user = userService.findByUsername(principal.getName());
        model.addAttribute("USERMODEL", user);

        Response<Posts> postsResponse = postService.getPostsList(page, pageSize, areaId, roomTypeId, priceId, user);
        List<Prices> prices = priceService.findAll();
        List<TypeRooms> typeRooms = typeRoomsService.findAllTypeRooms();
        List<Areas> areas = areasService.getAllAreas();
        model.addAttribute("PRICES", prices);
        model.addAttribute("ROOMTYPES", typeRooms);
        model.addAttribute("AREAS", areas);
        model.addAttribute("RESPONSE", postsResponse);

        model.addAttribute("AREA_ID", areaId);
        model.addAttribute("ROOM_TYPE_ID", roomTypeId);
        model.addAttribute("PRICE_ID", priceId);

        return "/web/post_manager";
    }
}
