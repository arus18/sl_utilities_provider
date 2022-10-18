package com.example.sl_utilities_provider.controllers;


import com.example.sl_utilities_provider.entities.User;
import com.example.sl_utilities_provider.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/users/")
public class UserController {


    @Autowired
    private PasswordEncoder passwordEncoder;
     @Autowired
     private UserRepo userRepo;
    // display list of user
    @GetMapping("listUsers")
    public String userList(Model model){
        model.addAttribute("listUsers", userRepo.findAll());
        return "user-admin";
    }


    @GetMapping("add_user")
    public String showAddUserForm(User user) {
        return "add-user";
    }

    @PostMapping("register")
    public String addUser(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "add-user";
        }
        System.out.println(user.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "redirect:/";
    }

    @GetMapping("deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") long id) {

        // call delete employee method
        userRepo.deleteById(id);
        return "redirect:/users/listUsers";
    }
}
