package exam.service.impl;

import exam.model.dto.ShopRootDto;
import exam.model.entity.Shop;
import exam.repository.ShopRepository;
import exam.repository.TownRepository;
import exam.service.ShopService;
import exam.util.ValidationUtil;
import exam.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static exam.constants.filePath.PATH_SHOPS;

@Service
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;

    public ShopServiceImpl(ShopRepository shopRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper, TownRepository townRepository) {
        this.shopRepository = shopRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return shopRepository.count()>0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        return Files.readString(Path.of(PATH_SHOPS));
    }

    @Override
    public String importShops() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        xmlParser.fromFile(PATH_SHOPS, ShopRootDto.class)
                .getShops()
                .stream()
                .filter(shopDto -> {
                    boolean isValid = validationUtil.isValid(shopDto);
                    if (shopRepository.findByName(shopDto.getName()).isPresent()) {
                        isValid = false;
                    }
                    sb.append(isValid ? String.format("Successfully imported Shop %s - %.2f", shopDto.getName(), shopDto.getIncome())
                            : "Invalid shop").append(System.lineSeparator());

                    return isValid;
                })
                .map(shopDto -> {
                    Shop shop = modelMapper.map(shopDto, Shop.class);
                    shop.setTown(townRepository.findByName(shopDto.getTown().getName()).get());
                    return shop;
                })
                .forEach(shopRepository::saveAndFlush);

        return sb.toString();
    }
}
