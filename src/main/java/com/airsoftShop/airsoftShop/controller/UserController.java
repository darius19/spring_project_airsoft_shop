package com.airsoftShop.airsoftShop.controller;

import com.airsoftShop.airsoftShop.dto.UserAuthDto;
import com.airsoftShop.airsoftShop.dto.UserProfileDto;
import com.airsoftShop.airsoftShop.dto.UserRegistrationDto;
import com.airsoftShop.airsoftShop.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping({"/register", "/login"})
    public String showRegisterForm(Model model) {
        model.addAttribute("userAuthDto", new UserAuthDto());
        model.addAttribute("userRegistrationDto", new UserRegistrationDto());
        model.addAttribute("userRegFailed", "");


        return "register-page";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserRegistrationDto userRegistrationDto, Model model) {
        String usersRegFailedMessage = userService.saveUser(userRegistrationDto) ? "" : "Something went wrong";
        model.addAttribute("userAuthDto", new UserAuthDto());
        model.addAttribute("userRegistrationDto", new UserRegistrationDto());
        model.addAttribute("userRegFailed", usersRegFailedMessage);

        return "register-page";
    }

    @GetMapping("/test")
    public String showTest() {

        return "test";
    }

    @GetMapping("/profile")
    public String showProfileForm(Model model, Authentication authentication ) throws IOException {
        model.addAttribute("userProfileDto", userService.getUserProfileDto(authentication.getName()));
        return "profile-page";
    }

    @PatchMapping("/profile")
    public String updateUser(@ModelAttribute UserProfileDto userProfileDto ,@RequestParam("image") MultipartFile multipartFile) throws IOException {
        userService.updateUser(userProfileDto, multipartFile);
        return "redirect:/profile";
    }

    @DeleteMapping("/profile")
    public String deleteUser(@ModelAttribute UserProfileDto userProfileDto) {
        userService.deleteUser(userProfileDto);
        return "redirect:/logout";
    }




}
