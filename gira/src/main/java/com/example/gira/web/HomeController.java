package com.example.gira.web;


import com.example.gira.model.enums.ClassificationNameEnum;
import com.example.gira.model.enums.ProgressNameEnum;
import com.example.gira.security.CurrentUser;
import com.example.gira.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final TaskService taskService;

    public HomeController(CurrentUser currentUser, TaskService taskService) {
        this.currentUser = currentUser;
        this.taskService = taskService;
    }


    @GetMapping("/")
    public String home(Model model) {
        if (currentUser.getId() == null) {
            return "index";
        }
        model.addAttribute("tasks",taskService.findAllTasks() );
        model.addAttribute("progresses", ProgressNameEnum.values());
        model.addAttribute("classifications", ClassificationNameEnum.values());
        return "home";
    }
}
