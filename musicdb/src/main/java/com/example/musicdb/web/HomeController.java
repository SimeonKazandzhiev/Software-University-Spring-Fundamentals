package com.example.musicdb.web;


import com.example.musicdb.security.CurrentUser;
import com.example.musicdb.service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final AlbumService albumService;

    public HomeController(CurrentUser currentUser, AlbumService albumService) {
        this.currentUser = currentUser;
        this.albumService = albumService;
    }

    @GetMapping
    public String home(Model model) {
        if (currentUser.getId() == null) {
            return "index";
        }

        model.addAttribute("albums", albumService.findAllAlbums());
        model.addAttribute("totalCopies", albumService.totalCopies());

        return "home";
    }
}
