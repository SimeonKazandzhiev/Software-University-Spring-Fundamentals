package com.example.gira.web;


import com.example.gira.model.binding.UserLoginBindingModel;
import com.example.gira.model.binding.UserRegisterBindingModel;
import com.example.gira.model.service.UserServiceModel;
import com.example.gira.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel(){
        return new UserRegisterBindingModel();
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegisterBindingModel userRegisterBindingModel, BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            redirectAttributes
                    .addFlashAttribute("userRegisterBindingModel",userRegisterBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",bindingResult);

            return "redirect:register";
        }
        UserServiceModel userServiceModel = modelMapper.map(userRegisterBindingModel, UserServiceModel.class);

        boolean success = userService.registerUser(userServiceModel);

        if(!success){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel",userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("userNameIsOccupied", true);
            return "redirect:register";
        }

        return "login";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginBindingModel userLoginBindingModel,BindingResult bindingResult,
                               RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel", bindingResult);
            return "redirect:login";
        }


        UserServiceModel user = userService.findByEmailAndPassword(userLoginBindingModel.getEmail(), userLoginBindingModel.getPassword());

        if (user==null){
            redirectAttributes.addFlashAttribute("userLoginBindingModel", userLoginBindingModel);
            redirectAttributes.addFlashAttribute("userNotFound", true);
            return "redirect:login";
        }

        userService.loginUser(user);
        return "redirect:/";
    }


    @ModelAttribute
    private UserLoginBindingModel userLoginBindingModel(){
        return new UserLoginBindingModel();
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/";
    }
}
