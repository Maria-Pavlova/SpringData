package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.domain.dtos.BranchDto;
import hiberspring.domain.entities.Branch;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.TownRepository;
import hiberspring.service.BranchService;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import static hiberspring.common.Constants.*;

@Service
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, Gson gson,
                             ValidationUtil validationUtil, ModelMapper modelMapper, TownRepository townRepository) {
        this.branchRepository = branchRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
    }

    @Override
    public Boolean branchesAreImported() {
        return branchRepository.count()>0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return Files.readString(Path.of(PATH_BRANCHES));
    }

    @Override
    public String importBranches(String branchesFileContent) throws IOException {
        StringBuilder sb = new StringBuilder();

        Arrays.stream(gson.fromJson(this.readBranchesJsonFile(), BranchDto[].class))
                .filter(branchDto -> {
                    boolean isValid = validationUtil.isValid(branchDto);
                    if (branchRepository.findByName(branchDto.getName()).isPresent()) {
                        isValid= false;
                    }
                    sb.append(isValid ? String.format(SUCCESSFUL_IMPORT_MESSAGE, "Branch",branchDto.getName())
                            : INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                    return isValid;
                })
                .map(branchDto -> {
                    Branch branch = modelMapper.map(branchDto, Branch.class);
                    branch.setTown(townRepository.findByName(branchDto.getTown()).get());
                    return branch;
                })
                .forEach(branchRepository::saveAndFlush);
        return sb.toString();
    }
}
