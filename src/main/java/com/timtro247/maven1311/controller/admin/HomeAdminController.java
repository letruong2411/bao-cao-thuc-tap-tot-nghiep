package com.timtro247.maven1311.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.annotation.MultipartConfig;

@CrossOrigin
@Controller
public class HomeAdminController {

    @GetMapping("/admin-home")
    public String adminHome() {
        return "admin/index/index";
    }
}
