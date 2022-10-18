package com.example.sl_utilities_provider.controllers;
import com.example.sl_utilities_provider.entities.Service;
import com.example.sl_utilities_provider.entities.Worker;
import com.example.sl_utilities_provider.repos.ServiceRepo;
import com.example.sl_utilities_provider.repos.WorkerRepo;
import com.example.sl_utilities_provider.utility.FileUploadUtil;
import com.example.sl_utilities_provider.utility.PdfUtilityService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public String addService(Service service, BindingResult result,@RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        service.setImage(fileName);
        for(String id: service.getWorkerIDs()){
            Worker worker = workerRepo.findById(Long.valueOf(id)).get();
            service.getWorkers().add(worker);
        }
        serviceRepo.save(service);
        String uploadDir = new File("service-photos").getAbsolutePath();
        FileUploadUtil.saveFile(uploadDir, String.valueOf(service.getId())+fileName, multipartFile);
        return "redirect:list";
    }
    @GetMapping("search")
    public String searchService(@RequestParam("searchInput") String searchInput,Model model){
        model.addAttribute("services", serviceRepo.findServiceByName(searchInput));
        return "services-list-admin";
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
        Service service = serviceRepo.findById(id).get();
        serviceRepo.deleteById(id);
        FileUploadUtil.deleteFile(id+service.getImage());
        return "redirect:/";
    }

    @GetMapping("category/{id}")
    public String category(@PathVariable(value = "id") String id,Model model) {
        model.addAttribute("services", serviceRepo.findServiceByCategory(id));
        return "index";
    }

    @GetMapping("report")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "inline; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Service> services = serviceRepo.findAll();

        PdfUtilityService pdfUtility = new PdfUtilityService(services);
        pdfUtility.export(response);

    }
}
