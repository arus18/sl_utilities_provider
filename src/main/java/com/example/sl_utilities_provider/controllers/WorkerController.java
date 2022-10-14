package com.example.sl_utilities_provider.controllers;

import com.example.sl_utilities_provider.entities.Worker;
import com.example.sl_utilities_provider.repos.WorkerRepo;
import com.example.sl_utilities_provider.utility.PdfUtility;
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

@Controller
@RequestMapping("/workers/")
public class WorkerController {

    @Autowired
    WorkerRepo workerRepo;

    @GetMapping("workerList")
    public String workerList(Model model) {
        model.addAttribute("workers", workerRepo.findAll());
        return "worker-list-admin";
    }

    @GetMapping("add_worker")
    public String showAddWorkerForm(Worker worker) {
        return "add-worker";
    }

    @PostMapping("addWorker")
    public String addWorker(@Valid Worker worker, BindingResult result) {
        if (result.hasErrors()) {
            return "add-worker";
        }
        workerRepo.save(worker);
        return "redirect:workerList";
    }

    @GetMapping("deleteWorker/{id}")
    public String deleteWorker(@PathVariable(value = "id") long id) {
        workerRepo.deleteById(id);
        return "redirect:/workers/workerList";
    }

    @GetMapping("search")
    public String workerService(@RequestParam("searchInput") String searchInput, Model model){
        model.addAttribute("workers", workerRepo.findWorkerByNic(searchInput));
        return "worker-list-admin";
    }

    @GetMapping("showWorkerUpdateForm/{id}")
    public String showWorkerUpdateForm(@PathVariable( value = "id") long id, Model model) {
        Worker worker = workerRepo.findById(id).get();
        model.addAttribute("worker", worker);
        List<Worker> workers = workerRepo.findAll();
        return "update-worker";
    }

    /*@PostMapping("saveWorker")
    public String saveWorker(@Valid Worker worker, RedirectAttributes ra) {
        workerRepo.save(worker);
        ra.addFlashAttribute("message", "User saved");
        return "redirect:/workers/workerList";
    }*/

    @GetMapping("report")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "inline; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Worker> workers = workerRepo.findAll();

        PdfUtility pdfUtility = new PdfUtility(workers);
        pdfUtility.export(response);

    }

}
