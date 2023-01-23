package com.example.demo.controller;


import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("")
    public String showAllUser(ModelMap model, Principal principal) {
        List<User> messages = userService.getAllUser();
        model.addAttribute("admin", userService.findUserByName(principal.getName()));
        model.addAttribute("people", userService.getAllUser());
        model.addAttribute("person", new User());
        model.addAttribute("roles", roleService.getAllRole());
        return "admin";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(ModelMap model) {

        model.addAttribute("person", new User());

        model.addAttribute("roles", roleService.getAllRole());

        return "userInfo";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("person") User username) {

        userService.addUser(username);

        return "redirect:/admin";
    }

    @DeleteMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin";

    }

    @GetMapping("/user-update/{id}")
    public String updateUser(@PathVariable("id") Long id, ModelMap model) {
        User messages = userService.findUserById(id);
        model.addAttribute("person", userService.findUserById(id));
        List<Role> roles = roleService.getAllRole();
        model.addAttribute("roles", roles);
        return "userInfo";
    }

}
