package com.example.workshopnextleveltechnologiesmvc.web.controller;

import com.example.workshopnextleveltechnologiesmvc.service.CompanyService;
import com.example.workshopnextleveltechnologiesmvc.service.EmployeeService;
import com.example.workshopnextleveltechnologiesmvc.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping("/import")
public class ImportController extends BaseController {
    private final CompanyService companyService;
    private final ProjectService projectService;
    private final EmployeeService employeeService;

    @Autowired
    public ImportController(CompanyService companyService, ProjectService projectService, EmployeeService employeeService) {
        this.companyService = companyService;
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/xml")
    public ModelAndView xml() {

        ModelAndView modelAndView = super.view("xml/import-xml");
        boolean[] areImported = new boolean[]{
                companyService.areImported(), projectService.areImported(), employeeService.areImported()};
        modelAndView.addObject("areImported", areImported);
        return modelAndView;
    }

    @GetMapping("/companies")
    public ModelAndView companies() throws IOException {
        ModelAndView modelAndView = super.view("xml/import-companies");
        modelAndView.addObject("companies", this.companyService.readCompaniesXmlFile());
        return modelAndView;
    }

    @PostMapping("/companies")
    public ModelAndView companiesConfirm() throws JAXBException, FileNotFoundException {
        this.companyService.importCompanies();
        return super.redirect("/import/xml");
    }

    @GetMapping("/projects")
    public ModelAndView projects() throws IOException {
        ModelAndView modelAndView = super.view("xml/import-projects");
        modelAndView.addObject("projects", this.projectService.readProjectsXmlFile());
        return modelAndView;
    }

    @PostMapping("/projects")
    public ModelAndView projectsConfirm() throws JAXBException, FileNotFoundException {
        this.projectService.importProjects();
        return super.redirect("/import/xml");
    }

    @GetMapping("/employees")
    public ModelAndView employees() throws IOException {
        ModelAndView modelAndView = super.view("xml/import-employees");
        modelAndView.addObject("employees", this.employeeService.readEmployeesXmlFile());
        return modelAndView;
    }

    @PostMapping("/employees")
    public ModelAndView employeesConfirm() throws JAXBException, FileNotFoundException {
        this.employeeService.importEmployees();
        return super.redirect("/home");
    }
}
