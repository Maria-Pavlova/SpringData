package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.AgentImportDto;
import softuni.exam.models.entity.Agent;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.AgentService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

import static softuni.exam.constants.FilePath.PATH_AGENTS;

@Service
public class AgentServiceImpl implements AgentService {
    private final AgentRepository agentRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;

    @Autowired
    public AgentServiceImpl(AgentRepository agentRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, TownRepository townRepository) {
        this.agentRepository = agentRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return agentRepository.count() > 0;
    }

    @Override
    public String readAgentsFromFile() throws IOException {
        return Files.readString(Path.of(PATH_AGENTS));
    }

    @Override
    public String importAgents() throws IOException {
        StringBuilder sb = new StringBuilder();
       Arrays.stream(gson.fromJson(readAgentsFromFile(), AgentImportDto[].class))
                .filter(agentImportDto -> {
                    Optional<Agent> byFirstName = agentRepository.findByFirstName(agentImportDto.getFirstName());
                    Optional<Agent> byEmail = agentRepository.findByEmail(agentImportDto.getEmail());
                    boolean isValid = validationUtil.isValid(agentImportDto);
                    if (byFirstName.isPresent() || byEmail.isPresent()){
                        isValid = false;
                    }
                        sb.append( isValid ? String.format("Successfully imported Agent %s %s",
                                        agentImportDto.getFirstName(), agentImportDto.getLastName())
                                        : "Invalid agent")
                                .append(System.lineSeparator());
                    return isValid;
                }).map(agentImportDto ->{
                           Agent agent = modelMapper.map(agentImportDto, Agent.class);
                           agent.setTown(townRepository.findByTownName(agentImportDto.getTown()).get());
                           return agent;
                       }
                     )
                .forEach(agentRepository::saveAndFlush);

        return sb.toString();

    }
}
