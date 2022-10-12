package com.example.sl_utilities_provider.controllers;

import com.example.sl_utilities_provider.entities.Inquiry;
import com.example.sl_utilities_provider.repos.InquiryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/inquiries/")
public class InquiryController {

    @Autowired
    InquiryRepo inquiryRepo;

    @GetMapping("inquiryList")
    public String inquiryList(Model model) {
        model.addAttribute("inquiries", inquiryRepo.findAll());
        return "inquiry-list-admin";
    }

    @GetMapping("add_inquiry")
    public String showAddInquiryForm(Inquiry inquiry) {
        return "add-inquiry";
    }

    @PostMapping("addInquiry")
    public String addInquiry(@Valid Inquiry inquiry, RedirectAttributes ra) {
        inquiryRepo.save(inquiry);
        ra.addFlashAttribute("message", "The Inquiry has been added successfully.");
        return "redirect:inquiryList";
    }

    @GetMapping("deleteInquiry/{id}")
    public String deleteInquiry(@PathVariable(value = "id") long id) {
        inquiryRepo.deleteById(id);
        return "redirect:/inquiries/inquiryList";
    }

    @GetMapping("search")
    public String inquiryService(@RequestParam("searchInput") String searchInput, Model model){
        model.addAttribute("inquiries", inquiryRepo.findInquiryByInic(searchInput));
        return "inquiry-list-admin";
    }

}