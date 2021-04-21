package com.timtro247.maven1311.controller.admin;


import com.timtro247.maven1311.model.Response;
import com.timtro247.maven1311.model.Users;
import com.timtro247.maven1311.service.PostService;
import com.timtro247.maven1311.service.RoomService;
import com.timtro247.maven1311.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@Controller
@RequestMapping("/admin-home")
public class UserAdminController {

    private final UserService userService;
    private final PostService postService;
    private final RoomService roomService;

    @Autowired
    public UserAdminController(UserService userService, PostService postService, RoomService roomService) {
        this.userService = userService;
        this.postService = postService;
        this.roomService = roomService;
    }

    @GetMapping("/user")
    public String home(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
                       @RequestParam(name = "search_data", defaultValue = "") String searchData,
                       Model model, Principal principal) {
        Response<Users> usersResponse = userService.findUsersList(page, pageSize, searchData);
        model.addAttribute("SEARCHDATA", searchData);
        model.addAttribute("RESPONSE", usersResponse);
        return "/admin/user/index";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin-home/user";
    }

    @GetMapping("/user/active/{id}")
    public String activeUser(@PathVariable("id") Long id) {
        userService.activeUser(id);
        return "redirect:/admin-home/user";
    }

    @GetMapping("/user/{id}")
    public String getUserDetail(@PathVariable("id") Long id, Model model, Principal principal) {
        Users user = userService.findById(id);
        Long postsNumberTotal = postService.CountAllByUser(user);
        Long roomsNumberTotal = roomService.countAllByUser(user);

        if (user.getGender() == 2) {
            model.addAttribute("GENDERIMAGE", "/web/images/girl.jpg");
        } else {
            model.addAttribute("GENDERIMAGE", "/web/images/man.jpg");
        }
        model.addAttribute("ROOMSNUMBERTOTAL", roomsNumberTotal);
        model.addAttribute("POSTSNUMBERTOTAL", postsNumberTotal);
        model.addAttribute("USERMODEL", user);
        return "/admin/user/profile";
    }
}
