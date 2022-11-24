package exam.service.impl;

import com.google.gson.Gson;
import exam.model.dto.LaptopDto;
import exam.model.entity.Laptop;
import exam.repository.LaptopRepository;
import exam.repository.ShopRepository;
import exam.service.LaptopService;
import exam.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static exam.constants.filePath.PATH_LAPTOPS;

@Service
public class LaptopServiceImpl implements LaptopService {
    private final LaptopRepository laptopRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final ShopRepository shopRepository;

    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, ShopRepository shopRepository) {
        this.laptopRepository = laptopRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.shopRepository = shopRepository;
    }

    @Override
    public boolean areImported() {
        return laptopRepository.count()>0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return Files.readString(Path.of(PATH_LAPTOPS));
    }

    @Override
    public String importLaptops() throws IOException {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(gson.fromJson(this.readLaptopsFileContent(), LaptopDto[].class))
                .filter(laptopDto -> {
                    boolean isValid = validationUtil.isValid(laptopDto);
                    if (laptopRepository.findByMacAddress(laptopDto.getMacAddress()).isPresent()) {
                        isValid = false;
                    }
                    sb.append(isValid ? String.format("Successfully imported Laptop %s - %.2f - %d - %d",
                           laptopDto.getMacAddress(),laptopDto.getCpuSpeed(),laptopDto.getRam(),laptopDto.getStorage())
                            : "Invalid Laptop").append(System.lineSeparator());

                    return isValid;
                })
                .map(laptopDto -> {
                    Laptop laptop = modelMapper.map(laptopDto, Laptop.class);
                    laptop.setShop(shopRepository.findByName(laptopDto.getShop().getName()).get());
                    return laptop;
                })
                .forEach(laptopRepository::saveAndFlush);

        return sb.toString();
    }

    @Override
    public String exportBestLaptops() {
        List<Laptop> laptops = laptopRepository.findAllByOrderByCpuSpeedDescRamDescStorageDescMacAddressAsc();
          return   laptops
                    .stream()
                    .map(Laptop::toString)
                    .collect(Collectors.joining(System.lineSeparator()));

    }
}
