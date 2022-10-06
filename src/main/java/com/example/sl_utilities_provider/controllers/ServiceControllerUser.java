package com.example.sl_utilities_provider.controllers;
import com.example.sl_utilities_provider.entities.Service;
import com.example.sl_utilities_provider.repos.ServiceRepo;
import com.example.sl_utilities_provider.utility.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ServiceControllerUser {

    @Autowired
    ServiceRepo serviceRepo;

    @GetMapping("")
    public String serviceGrid(Model model) {
        model.addAttribute("services", serviceRepo.findAll());
        return "index";
    }

    @GetMapping("/search")
    public String searchService(@RequestParam("searchInput") String searchInput, Model model){
        model.addAttribute("services", serviceRepo.findServiceByName(searchInput));
        return "index";
    }
}
