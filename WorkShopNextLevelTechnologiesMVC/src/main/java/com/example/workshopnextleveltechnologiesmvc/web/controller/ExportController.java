package com.example.workshopnextleveltechnologiesmvc.web.controller;

import com.example.workshopnextleveltechnologiesmvc.service.EmployeeService;
import com.example.workshopnextleveltechnologiesmvc.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExportController extends BaseController {
    private final ProjectService projectService;
    private final EmployeeService employeeService;

    @Autowired
    public ExportController(ProjectService projectService, EmployeeService employeeService) {
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("export/project-if-finished")
    public String projects(Model model) {
        String finishedProjects = this.projectService.getFinishedProjects();
        model.addAttribute("projectsIfFinished", finishedProjects);
        return "export/export-project-if-finished";
    }

    @GetMapping("export/employees-above")
    public String employees(Model model) {
        String employeesOlderThan = this.employeeService.getEmployeesOlderThan(25);
        model.addAttribute("employeesAbove", employeesOlderThan);
        return "export/export-employees-with-age";
    }
}
