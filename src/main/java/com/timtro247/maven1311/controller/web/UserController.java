package com.timtro247.maven1311.controller.web;

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
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PostService postService;
    private final RoomService roomService;

    @Autowired
    public UserController(UserService userService, PostService postService, RoomService roomService) {
        this.userService = userService;
        this.postService = postService;
        this.roomService = roomService;
    }

    @GetMapping("/profile")
    public String getProfile(Model model, Principal principal) {
        Users user = userService.findByUsername(principal.getName());
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
        return "/web/profile";
    }

    @GetMapping("profile/edit")
    public String editProfile(Model model, Principal principal) {
        Users user = userService.findByUsername(principal.getName());
        model.addAttribute("USERMODEL", user);
        return "/web/edit_profile";
    }

    @PostMapping("/profile/edit")
    public String editProfilePOST(@ModelAttribute("USERMODEL") Users user, Model model, Principal principal) {
        boolean message = userService.update(user);
        if (!message) {
            user = userService.findByUsername(principal.getName());
            model.addAttribute("USERMODEL", user);
            model.addAttribute("message", true);
            return "/web/edit_profile";
        }
        return "redirect:/user/profile";
    }
}
