package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.OfferRootDto;
import softuni.exam.models.entities.Offer;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static softuni.exam.constants.FilePath.PATH_OFFERS;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final CarRepository carRepository;
    private final SellerRepository sellerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, XmlParser xmlParser,
                            ValidationUtil validationUtil, ModelMapper modelMapper,
                            CarRepository carRepository, SellerRepository sellerRepository) {
        this.offerRepository = offerRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.carRepository = carRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public boolean areImported() {
        return offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(Path.of(PATH_OFFERS));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        xmlParser.fromFile(PATH_OFFERS, OfferRootDto.class)
                .getOffers()
                .stream()
                .filter(offerDto -> {
                    boolean isValid = validationUtil.isValid(offerDto);
                    if (offerRepository.findByDescriptionAndAddedOn(offerDto.getDescription(), offerDto.getAddedOn()).isPresent()) {
                        isValid = false;
                    }
                    sb.append(isValid ? String.format("Successfully imported offer - %s - %s",
                            offerDto.getAddedOn(), offerDto.isHasGoldStatus())
                            : "Invalid offer").append(System.lineSeparator());
                    return isValid;
                })
                .map(offerDto -> {
                            Offer offer = modelMapper.map(offerDto, Offer.class);
                            offer.setCar(carRepository.findById(offerDto.getCar().getId()).get());
                            offer.setSeller(sellerRepository.findById(offerDto.getSeller().getId()).get());
                            return offer;
                })
                .forEach(offerRepository::saveAndFlush);

        return sb.toString();
    }
}
