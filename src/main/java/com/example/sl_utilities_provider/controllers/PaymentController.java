package com.example.sl_utilities_provider.controllers;
import com.example.sl_utilities_provider.entities.Payment;
import com.example.sl_utilities_provider.entities.Service;
import com.example.sl_utilities_provider.repos.PaymentRepo;
import com.example.sl_utilities_provider.repos.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/payments/")
public class PaymentController {

    @Autowired
    PaymentRepo paymentRepo;

    @GetMapping("list")
    public String paymentList(Model model) {
        model.addAttribute("payments", paymentRepo.findAll());
        return "card-details";
    }

    @GetMapping("payment")
    public String paymentCardList(Model model) {
        model.addAttribute("payment", new Payment());
        return "add-payment";
    }


    @PostMapping("add")
    public String addPayment(@Valid Payment payment, BindingResult result) {
        if (result.hasErrors()) {
            return "add-payment";
        }
        paymentRepo.save(payment);
        return "redirect:list";
    }

//    @GetMapping("showFormForUpdate/{id}")
//    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {
//        Payment payment = paymentRepo.findById(id).get();
//        payment.setId(payment.getPaymen().stream().map(w -> String.valueOf(w.getId())).collect(Collectors.toList()));
//        model.addAttribute("payment", payment);
//        List<Payment> workers = workerRepo.findAll();
//        model.addAttribute("options", workers);
//        return "update-service";
//    }

    @GetMapping("deletePayment/{id}")
    public String deletePayment(@PathVariable(value = "id") long id) {

        // call delete payment method
        paymentRepo.deleteById(id);
        return "redirect:/payments/list";
    }
}