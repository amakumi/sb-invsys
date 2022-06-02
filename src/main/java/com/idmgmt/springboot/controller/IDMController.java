package com.idmgmt.springboot.controller;

import com.idmgmt.springboot.model.IDM;
import com.idmgmt.springboot.service.IDMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/idm")
public class IDMController {

    @Autowired
    private IDMService idmService;

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
}
