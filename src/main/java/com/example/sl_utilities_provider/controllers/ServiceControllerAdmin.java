package com.example.sl_utilities_provider.controllers;
import com.example.sl_utilities_provider.entities.Service;
import com.example.sl_utilities_provider.entities.Worker;
import com.example.sl_utilities_provider.repos.ServiceRepo;
import com.example.sl_utilities_provider.repos.WorkerRepo;
import com.example.sl_utilities_provider.utility.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/services/")
public class ServiceControllerAdmin {

    @Autowired
    ServiceRepo serviceRepo;

    @Autowired
    WorkerRepo workerRepo;

    @GetMapping("list")
    public String serviceList(Model model) {
        model.addAttribute("services", serviceRepo.findAll());
        return "services-list-admin";
    }

    @GetMapping("add_service")
    public String showAddServiceForm(Service service,Model model) {
        List<Worker> workers = workerRepo.findAll();
        model.addAttribute("options", workers);
        return "add-service";
    }
    @PostMapping("add")
    public String updateService(Service service, BindingResult result,@RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        service.setImage(fileName);
        for(String id: service.getWorkerIDs()){
            Worker worker = workerRepo.findById(Long.valueOf(id)).get();
            service.getWorkers().add(worker);
        }
        System.out.println(service.getId());
        serviceRepo.save(service);
        String uploadDir = new File("service-photos").getAbsolutePath();
        FileUploadUtil.saveFile(uploadDir, String.valueOf(service.getId())+fileName, multipartFile);
        return "redirect:list";
    }
    @GetMapping("showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable( value = "id") long id, Model model) {
        Service service = serviceRepo.findById(id).get();
        service.setWorkerIDs(service.getWorkers().stream().map(w -> String.valueOf(w.getId())).collect(Collectors.toList()));
        model.addAttribute("service", service);
        List<Worker> workers = workerRepo.findAll();
        model.addAttribute("options", workers);
        return "update-service";
    }

    @GetMapping("deleteService/{id}")
    public String deleteService(@PathVariable (value = "id") long id) {

        // call delete employee method
        serviceRepo.deleteById(id);
        return "redirect:/list";
    }
}
