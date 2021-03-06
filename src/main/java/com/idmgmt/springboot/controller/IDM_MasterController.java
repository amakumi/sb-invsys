package com.idmgmt.springboot.controller;

import com.idmgmt.springboot.model.IDM_Master;
import com.idmgmt.springboot.service.IDM_MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/idmv1")
public class IDM_MasterController {

    @Autowired
    private IDM_MasterService idmService;

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
        IDM_Master idm_master = new IDM_Master();
        model.addAttribute("idm_master", idm_master);
        return "new_idm_master";
    }

    @PostMapping("/saveIDM")
    public String saveIDM(@ModelAttribute("idm_master") IDM_Master idm, RedirectAttributes attr) {
        // save employee to database

        idmService.saveIDM(idm);
        attr.addFlashAttribute("success", "IDM Master Service has been successfully added");
        return "redirect:/idmv1";
    }

    @PostMapping("/saveIDM2")
    public String saveIDM2(@ModelAttribute("idm_master") IDM_Master idm, RedirectAttributes attr) {
        // save employee to database
        idmService.saveIDM(idm);
        attr.addFlashAttribute("primary", "IDM Master Service has been successfully updated");
        return "redirect:/idmv1";
    }

    @GetMapping("/showFormForUpdate/{application_id}")
    public String showFormForUpdate(@PathVariable(value = "application_id") String id, Model model, RedirectAttributes attr) {

        // get employee from the service
        IDM_Master idm_master = idmService.getIDMById(id);

        // set employee as a model attribute to pre-populate the form
        model.addAttribute("idm_master", idm_master);
        attr.addFlashAttribute("primary", "IDM Master Service has been successfully updated");
        return "update_idm_master";
    }

    @GetMapping("/delete/{application_id}")
    public String deleteIDM(@PathVariable(value = "application_id") String id, RedirectAttributes attr) {

        // call delete employee method
        this.idmService.deleteIDMById(id);
        attr.addFlashAttribute("error", "IDM Master Service has been removed");
        return "redirect:/idmv1";
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<IDM_Master> page = idmService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<IDM_Master> listIDMasters = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listIDMasters", listIDMasters);
        return "idm_master";
    }



}
