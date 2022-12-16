package com.example.workshopnextleveltechnologiesmvc.service;

import com.example.workshopnextleveltechnologiesmvc.data.dtoImport.CompanyRootDto;
import com.example.workshopnextleveltechnologiesmvc.data.entities.Company;
import com.example.workshopnextleveltechnologiesmvc.repositories.CompanyRepository;
import com.example.workshopnextleveltechnologiesmvc.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.example.workshopnextleveltechnologiesmvc.constants.FilePath.PATH_COMPANIES;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;


    @Autowired
    public CompanyService(CompanyRepository companyRepository, XmlParser xmlParser, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    public boolean areImported() {
        return companyRepository.count() > 0;
    }

    public String readCompaniesXmlFile() throws IOException {
        return Files.readString(Path.of(PATH_COMPANIES));
    }

    public void importCompanies() throws JAXBException, FileNotFoundException {
        CompanyRootDto companyRootDto = xmlParser.xmlParse(PATH_COMPANIES, CompanyRootDto.class);

        companyRootDto
                .getCompanies()
                .stream()
                .map(companyDto -> modelMapper.map(companyDto, Company.class))
                .forEach(companyRepository::saveAndFlush);

    }
}
