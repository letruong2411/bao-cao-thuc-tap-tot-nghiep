package com.timtro247.maven1311.controller.web;

import com.timtro247.maven1311.model.*;
import com.timtro247.maven1311.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@Controller
public class RoomController {
    private final RoomService roomService;
    private final TypeRoomService typeRoomService;
    private final AreaService areaService;
    private final UserService userService;
    private final PriceService priceService;
    private final ImageService imageService;

    @Autowired
    public RoomController(RoomService roomService, TypeRoomService typeRoomService, AreaService areaService, UserService userService,
                          PriceService priceService, ImageService imageService) {
        this.roomService = roomService;
        this.typeRoomService = typeRoomService;
        this.areaService = areaService;
        this.userService = userService;
        this.priceService = priceService;
        this.imageService = imageService;
    }

    @GetMapping("/room")
    public String getRoomsList(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
                               @RequestParam(name = "area_id", defaultValue = "0") Long areaId,
                               @RequestParam(name = "room_type_id", defaultValue = "0") Long roomTypeId,
                               @RequestParam(name = "price_id", defaultValue = "0") Long priceId,
                               Model model, Principal principal) {
        Users user = userService.findByUsername(principal.getName());
        Response<Rooms> roomsResponse = roomService.findByRoomTypeAndAreaAndPrice(page, pageSize, roomTypeId, areaId, priceId, user);
        List<Prices> prices = priceService.findAll();
        List<TypeRooms> typeRooms = typeRoomService.findAllTypeRooms();
        List<Areas> areas = areaService.getAllAreas();
        model.addAttribute("PRICES", prices);
        model.addAttribute("ROOMTYPES", typeRooms);
        model.addAttribute("AREAS", areas);
        model.addAttribute("RESPONSE", roomsResponse);

        model.addAttribute("AREA_ID", areaId);
        model.addAttribute("ROOM_TYPE_ID", roomTypeId);
        model.addAttribute("PRICE_ID", priceId);
        model.addAttribute("USERMODEL", user);
        return "web/room_manager";
    }

    @GetMapping("/room/create")
    public String createRoom(Model model, Principal principal) {
        Users user = userService.findByUsername(principal.getName());
        model.addAttribute("room", new Rooms());
        List<TypeRooms> typeRoomList = typeRoomService.findAllTypeRooms();
        List<Areas> areaList = areaService.getAllAreas();
        model.addAttribute("typeRoomList", typeRoomList);
        model.addAttribute("areaList", areaList);
        model.addAttribute("USERMODEL", user);
        return "web/createroom";
    }

    @PostMapping("/room/create")
    public String save(@ModelAttribute("room") Rooms room, Principal principal, @RequestParam("pictures") MultipartFile[] files, Model model) throws IOException {
        Users user = userService.findByUsername(principal.getName());
        room.setUser(user);
        roomService.save(room, files);
        model.addAttribute("USERMODEL", user);
        return "redirect:/room";
    }
}
