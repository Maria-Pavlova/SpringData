package com.example.workshopnextleveltechnologiesmvc.web.controller;

import com.example.workshopnextleveltechnologiesmvc.service.CompanyService;
import com.example.workshopnextleveltechnologiesmvc.service.EmployeeService;
import com.example.workshopnextleveltechnologiesmvc.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController{

    private final CompanyService companyService;
    private final ProjectService projectService;
    private final EmployeeService employeeService;

    @Autowired
    public HomeController(CompanyService companyService, ProjectService projectService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @GetMapping("/home")
    public ModelAndView home(){
        boolean areImported = companyService.areImported()
                && employeeService.areImported()
                && projectService.areImported();

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("areImported", areImported);

        return modelAndView;
    }
}
