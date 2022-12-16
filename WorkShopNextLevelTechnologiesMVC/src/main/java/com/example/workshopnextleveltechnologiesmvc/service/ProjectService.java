package com.example.workshopnextleveltechnologiesmvc.service;

import com.example.workshopnextleveltechnologiesmvc.data.dtoExport.FinishedProjectDto;
import com.example.workshopnextleveltechnologiesmvc.data.dtoImport.ProjectsRootDto;
import com.example.workshopnextleveltechnologiesmvc.data.entities.Project;
import com.example.workshopnextleveltechnologiesmvc.repositories.CompanyRepository;
import com.example.workshopnextleveltechnologiesmvc.repositories.ProjectRepository;
import com.example.workshopnextleveltechnologiesmvc.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import static com.example.workshopnextleveltechnologiesmvc.constants.FilePath.PATH_PROJECTS;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final CompanyRepository companyRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, XmlParser xmlParser, ModelMapper modelMapper, CompanyRepository companyRepository) {
        this.projectRepository = projectRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.companyRepository = companyRepository;
    }

    public boolean areImported() {
        return projectRepository.count() > 0;
    }

    public String readProjectsXmlFile() throws IOException {
        return Files.readString(Path.of(PATH_PROJECTS));
    }

    public void importProjects() throws JAXBException, FileNotFoundException {
        xmlParser.xmlParse(PATH_PROJECTS, ProjectsRootDto.class)
                .getProjects()
                .stream()
                .map(projectDto -> {
                            Project project = modelMapper.map(projectDto, Project.class);
                            project.setCompany(companyRepository.findByName(projectDto.getCompany().getName()).get());
                            return project;
                        }
                )
                .forEach(projectRepository::saveAndFlush);

    }

    public String getFinishedProjects() {
        return projectRepository.findAllByIsFinishedIsTrue()
                .stream()
                .map(project -> modelMapper.map(project, FinishedProjectDto.class))
                .map(FinishedProjectDto::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
