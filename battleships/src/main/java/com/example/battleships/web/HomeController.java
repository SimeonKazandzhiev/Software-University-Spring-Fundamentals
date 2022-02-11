package com.example.battleships.web;


import com.example.battleships.model.binding.Ships;
import com.example.battleships.security.CurrentUser;
import com.example.battleships.service.ShipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final ShipService shipService;

    public HomeController(CurrentUser currentUser, ShipService shipService) {
        this.currentUser = currentUser;
        this.shipService = shipService;
    }

    @GetMapping()
    public String home(Model model) {
        if (currentUser.getId() == null) {
            return "index";
        }
        model.addAttribute("allShips", shipService.findAllShips());
        model.addAttribute("loggedUserShips", shipService.findByUser(currentUser.getId()));

        model.addAttribute("notLoggedUserShips", shipService.findByUserNot(currentUser.getId()));
        Ships ships = new Ships();
        model.addAttribute("ships", ships);

        return "home";
    }



    @PostMapping("/fire")
    public String fire(Ships ships) {

        shipService.fire(ships.getShip1(),ships.getShip2());


        return "redirect:/";
    }

}
