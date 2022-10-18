package com.example.sl_utilities_provider.controllers;
import com.example.sl_utilities_provider.entities.Payment;
import com.example.sl_utilities_provider.entities.Service;
import com.example.sl_utilities_provider.repos.PaymentRepo;
import com.example.sl_utilities_provider.repos.ServiceRepo;
import com.example.sl_utilities_provider.utility.PdfUtilityPayment;
import com.example.sl_utilities_provider.utility.PdfUtilityService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/payments/")
public class PaymentController {

    @Autowired
    PaymentRepo paymentRepo;

    @Autowired
    ServiceRepo serviceRepo;

    @GetMapping("list/{id}")
    public String paymentList(@PathVariable( value = "id") long id,Model model) {
        System.out.println(id);
        model.addAttribute("service",serviceRepo.findById(id));
        model.addAttribute("payments", paymentRepo.findAll());
        return "card-details";
    }

    @GetMapping("payment/{id}")
    public String paymentCardList(Model model,@PathVariable( value = "id") long id) {
        model.addAttribute("service",serviceRepo.findById(id).get());
        model.addAttribute("payment", new Payment());
        return "add-payment";
    }


    @PostMapping("add/{id}")
    public String addPayment(@Valid Payment payment, BindingResult result,@PathVariable( value = "id") long id) {
        if (result.hasErrors()) {
            return "add-payment";
        }
        paymentRepo.save(payment);
        return "redirect:/payments/list/"+id;
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

    @GetMapping("report/{id}")
    public void exportToPDF(HttpServletResponse response,@PathVariable( value = "id") long id) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "inline; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);


        PdfUtilityPayment pdfUtility = new PdfUtilityPayment(serviceRepo.findById(id).get());
        pdfUtility.export(response);

    }
}
