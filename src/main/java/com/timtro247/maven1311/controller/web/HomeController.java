package com.timtro247.maven1311.controller.web;

import com.timtro247.maven1311.model.*;
import com.timtro247.maven1311.model.*;
import com.timtro247.maven1311.dto.UsersDTO;
import com.timtro247.maven1311.model.Areas;
import com.timtro247.maven1311.model.Posts;
import com.timtro247.maven1311.model.TypeRooms;
import com.timtro247.maven1311.service.*;
import com.timtro247.maven1311.service.impl.AreaServiceImpl;
import com.timtro247.maven1311.service.impl.PostServiceImpl;
import com.timtro247.maven1311.service.impl.TypeRoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@CrossOrigin
@Controller
public class HomeController {

    private final UserService userService;
    private final PostService postService;
    private final AreaService areasService;
    private final TypeRoomService typeRoomsService;
    private final PriceService priceService;
    static List<Posts> filterPostList = null;
    static List<Posts> searchPostList = null;
    private final int sizePage = 9;

    @Autowired
    public HomeController(UserService userService, PostServiceImpl postService, AreaServiceImpl areasService, TypeRoomServiceImpl typeRoomsService, PriceService priceService) {
        this.userService = userService;
        this.postService = postService;
        this.areasService = areasService;
        this.typeRoomsService = typeRoomsService;
        this.priceService = priceService;
    }

    @GetMapping("/page/{pageNumber}")
    public String viewPage(Model model, @PathVariable(name = "pageNumber") int pageNumber, Principal principal) {

        if(principal != null) {
            Users user = userService.findByUsername(principal.getName());
            Long id=user.getId();
            model.addAttribute("id", id);
            model.addAttribute("USERMODEL", user);
        }
        List<Posts> postsList = postService.getPage(pageNumber).getContent();
        postsList.forEach(t->System.out.println(t.getRoom().getImages().stream().findFirst().get().getId()));

        List<Areas> areasList = areasService.getAllAreas();
        List<TypeRooms> typeRoomsList = typeRoomsService.findAllTypeRooms();
        List<Prices> pricesList = priceService.getAllPrices();
        int totalPages = postService.getPage(pageNumber).getTotalPages();
        model.addAttribute("postsList", postsList);
        model.addAttribute("areasList", areasList);
        model.addAttribute("typeRoomsList", typeRoomsList);
        model.addAttribute("pricesList", pricesList);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", totalPages);
        return "/web/index";
    }

    @GetMapping("/")
    public String home(Model model, Principal principal) {

        return viewPage(model, 1,principal);
    }

    @PostMapping("/filter")
    public String filterPage1(HttpServletRequest request, Model model, Principal principal) {
        if(principal != null) {
            Users user = userService.findByUsername(principal.getName());
            model.addAttribute("USERMODEL", user);
        }
        String typeRoom = request.getParameter("typeRoom");
        String area = request.getParameter("area");
        String price = request.getParameter("price");
        filterPostList = postService.filterPost(typeRoom, area, price, 1);
        return filter(request, model, 1,principal);
    }

    @GetMapping("/filter/page/{pageNumber}")
    public String filter(HttpServletRequest request, Model model, @PathVariable(name = "pageNumber") int pageNumber, Principal principal) {
        if(principal != null) {
            Users user = userService.findByUsername(principal.getName());
            model.addAttribute("USERMODEL", user);
        }
        List<Posts> postsList = postService.getSubList(filterPostList, pageNumber);
        List<Areas> areasList = areasService.getAllAreas();
        List<TypeRooms> typeRoomsList = typeRoomsService.findAllTypeRooms();
        List<Prices> pricesList = priceService.getAllPrices();
        int totalPages = (filterPostList.size() % sizePage > 0) ? (filterPostList.size() / sizePage + 1) : filterPostList.size() / sizePage;
        model.addAttribute("postsList", postsList);
        model.addAttribute("areasList", areasList);
        model.addAttribute("typeRoomsList", typeRoomsList);
        model.addAttribute("pricesList", pricesList);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", totalPages);
        return "/web/filter";
    }

    @PostMapping("/search")
    public String searchPage1(HttpServletRequest request, Model model, Principal principal) {
        if(principal != null) {
            Users user = userService.findByUsername(principal.getName());
            model.addAttribute("USERMODEL", user);
        }
        System.out.println("gia tri type room:" + request.getParameter("address"));
        String address = request.getParameter("address");
        searchPostList = postService.searchPost(address);
        return search(request, model, 1,principal);
    }

    @GetMapping("/search/page/{pageNumber}")
    public String search(HttpServletRequest request, Model model, @PathVariable(name = "pageNumber") int pageNumber, Principal principal) {
        if(principal != null) {
            Users user = userService.findByUsername(principal.getName());
            model.addAttribute("USERMODEL", user);
        }
        List<Posts> postsList = postService.getSubList(searchPostList, pageNumber);
        List<Areas> areasList = areasService.getAllAreas();
        List<TypeRooms> typeRoomsList = typeRoomsService.findAllTypeRooms();
        List<Prices> pricesList = priceService.getAllPrices();
        int totalPages = (searchPostList.size() % sizePage > 0) ? (searchPostList.size() / sizePage + 1) : searchPostList.size() / sizePage;
        model.addAttribute("postsList", postsList);
        model.addAttribute("areasList", areasList);
        model.addAttribute("typeRoomsList", typeRoomsList);
        model.addAttribute("pricesList", pricesList);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", totalPages);
        return "/web/search";
    }

    @GetMapping("/login")
    public String login(Model model) {
        Users user = new Users();
        model.addAttribute("user",user);
        return "/web/login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        Users user = new Users();
        model.addAttribute("user", user);
        return "web/register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute("user") Users user, Model model) {
        if (userService.findByUsername(user.getUsername()) != null) {
            model.addAttribute("emailExists", true);
            return "web/register";
        } else {
            userService.save(user);
            model.addAttribute("USER", user);
            return "redirect:/login";
        }
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") Users users, Model model) {
        return "redirect:/";
    }
}
