package com.timtro247.maven1311.controller.admin;

import com.timtro247.maven1311.model.*;
import com.timtro247.maven1311.service.AreaService;
import com.timtro247.maven1311.service.PriceService;
import com.timtro247.maven1311.service.TypeRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.security.Principal;

import org.springframework.web.bind.annotation.*;
import com.timtro247.maven1311.service.RoomService;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/admin-home")
public class RoomAdminController {

    private final RoomService roomService;
    private final TypeRoomService typeRoomService;
    private final AreaService areaService;
    private final PriceService priceService;

    @Autowired
    public RoomAdminController(RoomService roomService, TypeRoomService typeRoomService, AreaService areaService, PriceService priceService) {
        this.roomService = roomService;
        this.typeRoomService = typeRoomService;
        this.areaService = areaService;
        this.priceService = priceService;
    }

    @RequestMapping("/room/detail")
    public String roomDetail(@RequestParam("roomId") String roomId, Model model) {
        Rooms room = roomService.findRoomById(Long.parseLong(roomId)).get();
        model.addAttribute("room", room);
        return "/admin/room/detail";
    }

    @RequestMapping("/room/delete/{id}")
    public String deleteRoom(@PathVariable("id") Long id) {
        roomService.deleteRoomById(id);
        return "redirect:/admin-home/room";
    }

    @GetMapping("/room")
    public String getRoomsList(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
                               @RequestParam(name = "area_id", defaultValue = "0") Long areaId,
                               @RequestParam(name = "room_type_id", defaultValue = "0") Long roomTypeId,
                               @RequestParam(name = "price_id", defaultValue = "0") Long priceId,
                               Model model, Principal principal) {
        Response<Rooms> roomsResponse = roomService.findByRoomTypeAndAreaAndPrice(page, pageSize, roomTypeId, areaId, priceId);
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
        return "admin/room/index";
    }
}
