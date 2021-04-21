package com.timtro247.maven1311.controller.admin;

import com.timtro247.maven1311.model.*;
import com.timtro247.maven1311.repository.UserRepository;
import com.timtro247.maven1311.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/admin-home")
public class PostAdminController {

    private final PriceService priceService;
    private final PostService postService;
    private final AreaService areaService;
    private final TypeRoomService typeRoomService;
    private final UserService userService;

    @Autowired
    public PostAdminController(PriceService priceService, PostService postService, AreaService areaService, TypeRoomService typeRoomService, UserRepository userRepository, UserService userService) {
        this.priceService = priceService;
        this.postService = postService;
        this.areaService = areaService;
        this.typeRoomService = typeRoomService;
        this.userService = userService;
    }

    @GetMapping("/post")
    public String getPostsList(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
                               @RequestParam(name = "area_id", defaultValue = "0") Long areaId,
                               @RequestParam(name = "room_type_id", defaultValue = "0") Long roomTypeId,
                               @RequestParam(name = "price_id", defaultValue = "0") Long priceId,
                               Model model, Principal principal) {
        Users user = userService.findByUsername(principal.getName());
        model.addAttribute("USERMODEL", user);
        Response<Posts> postsResponse = postService.getPostsList(page, pageSize, areaId, roomTypeId, priceId);
        List<Prices> prices = priceService.findAll();
        List<TypeRooms> typeRooms = typeRoomService.findAllTypeRooms();
        List<Areas> areas = areaService.getAllAreas();
        model.addAttribute("PRICES", prices);
        model.addAttribute("ROOMTYPES", typeRooms);
        model.addAttribute("AREAS", areas);
        model.addAttribute("RESPONSE", postsResponse);

        model.addAttribute("AREA_ID", areaId);
        model.addAttribute("ROOM_TYPE_ID", roomTypeId);
        model.addAttribute("PRICE_ID", priceId);

        return "/admin/post/index";
    }

    @GetMapping("/post/delete/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        postService.delete(id);
        return "redirect:/admin-home/post";
    }

    @GetMapping("/post_admin/{id}")
    public String getPostDetail( @PathVariable("id") Long id, @RequestParam(value = "room_type_id", defaultValue = "0") Long roomTypeId, Model model, Principal principal) {
        List<Areas> areasList = areaService.getAllAreas();
        List<TypeRooms> typeRoomsList = typeRoomService.findAllTypeRooms();
        List<Prices> pricesList = priceService.getAllPrices();
        model.addAttribute("areasList", areasList);
        model.addAttribute("typeRoomsList", typeRoomsList);
        model.addAttribute("pricesList", pricesList);

        Posts post = postService.findById(id);
        model.addAttribute("POSTMODEL",post);
        return "/admin/post/post_detail";

    }
}