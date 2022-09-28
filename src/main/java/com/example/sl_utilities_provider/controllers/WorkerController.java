package com.example.sl_utilities_provider.controllers;
import com.example.sl_utilities_provider.entities.Worker;
import com.example.sl_utilities_provider.repos.WorkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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
}
