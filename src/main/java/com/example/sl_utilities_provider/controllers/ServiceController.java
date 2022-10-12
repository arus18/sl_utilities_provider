package com.example.sl_utilities_provider.controllers;
import com.example.sl_utilities_provider.entities.Service;
import com.example.sl_utilities_provider.repos.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/services/")
public class ServiceController {

    @Autowired
    ServiceRepo serviceRepo;

    @GetMapping("list")
    public String serviceList(Model model) {
        model.addAttribute("services", serviceRepo.findAll());
        return "services-list-admin";
    }

    @GetMapping("add_service")
    public String showAddServiceForm(Service service) {
        return "add-service";
    }

    @PostMapping("add")
    public String addService(@Valid Service service, BindingResult result) {
        if (result.hasErrors()) {
            return "add-service";
        }
        serviceRepo.save(service);
        return "redirect:list";
    }
}
