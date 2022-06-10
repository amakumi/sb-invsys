package com.idmgmt.springboot.controller;

import com.idmgmt.springboot.model.IDM;
import com.idmgmt.springboot.repository.IDMRepository;
import com.idmgmt.springboot.service.IDMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/idm")
public class IDMController {

    @Autowired
    private IDMService idmService;
    @Autowired
    private IDMRepository repo;
    // display list of employees
    /*@GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "function", "asc", model);
    }*/

    @GetMapping("")
    public String viewHomePage(Model model) {
        return findPaginated(1, "id", "asc", model);
    }

    @GetMapping("/showNewIDMForm")
    public String showNewIDMForm(Model model) {
        // create model attribute to bind form data
        IDM idm = new IDM();
        model.addAttribute("idm", idm);
        return "new_idm";
    }

    @PostMapping("/saveIDM")
    public String saveIDM(@ModelAttribute("idm") IDM idm) {
        // save employee to database
        idmService.saveIDM(idm);
        return "redirect:/idm";
    }

    @GetMapping("/showFormForUpdate/{application_id}")
    public String showFormForUpdate(@PathVariable(value = "application_id") String id, Model model) {

        // get employee from the service
        IDM idm = idmService.getIDMById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("idm", idm);
        return "update_idm";
    }

    @GetMapping("/delete/{application_id}")
    public String deleteIDM(@PathVariable(value = "application_id") String id) {

        // call delete employee method
        this.idmService.deleteIDMById(id);
        return "redirect:/idm";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<IDM> page = idmService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<IDM> listIDMs = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listIDMs", listIDMs);
        return "idm";
    }

    public List<IDM> listAll() {
        return repo.findAll(Sort.by("id").ascending());
    }

    @GetMapping("/export")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=export_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<IDM> listIDMs = listAll();

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"App ID", "Function Name", "Endpoint", "Parameters", "Insert date", "Update date", "HTTP Method"};
        String[] nameMapping = {"id", "function", "endpoint", "params", "insDate", "updDate", "http"};

        csvWriter.writeHeader(csvHeader);

        for (IDM idm : listIDMs) {
            csvWriter.write(idm, nameMapping);
        }

        csvWriter.close();

    }
}
