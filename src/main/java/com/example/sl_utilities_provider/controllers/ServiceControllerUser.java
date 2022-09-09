package com.example.sl_utilities_provider.controllers;

import com.example.sl_utilities_provider.repos.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ServiceControllerUser {

    @Autowired
    ServiceRepo serviceRepo;

    @GetMapping("")
    public String serviceGrid(Model model) {
        model.addAttribute("services", serviceRepo.findAll());
        return "index";
    }
}
