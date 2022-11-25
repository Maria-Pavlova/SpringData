package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.OffersRootDto;
import softuni.exam.models.entity.ApartmentType;
import softuni.exam.models.entity.Offer;
import softuni.exam.repository.AgentRepository;
import softuni.exam.repository.ApartmentRepository;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.OfferService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import static softuni.exam.constants.FilePath.PATH_OFFERS;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final AgentRepository agentRepository;
    private final ApartmentRepository apartmentRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper, AgentRepository agentRepository, ApartmentRepository apartmentRepository) {
        this.offerRepository = offerRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.agentRepository = agentRepository;
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    public boolean areImported() {
        return offerRepository.count()>0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        return Files.readString(Path.of(PATH_OFFERS));
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        xmlParser.fromFile(PATH_OFFERS, OffersRootDto.class)
                .getOffers()
                .stream()
                .filter(offerDto -> {
                    boolean isValid = validationUtil.isValid(offerDto);
                    if (agentRepository.findByFirstName(offerDto.getAgent().getName()).isEmpty()) {
                        isValid = false;
                    }

                    sb.append( isValid ? String.format("Successfully imported offer %.2f",
                            offerDto.getPrice())
                            : "Invalid offer").append(System.lineSeparator());
                    return isValid;
                })
                .map(offerDto -> {
                    Offer offer = modelMapper.map(offerDto, Offer.class);
                    offer.setAgent(agentRepository.findByFirstName(offerDto.getAgent().getName()).get());
                    offer.setApartment(apartmentRepository.findById(offerDto.getApartment().getId()).get());
                    return offer;
                })
                .forEach(offerRepository::saveAndFlush);
        return sb.toString();
    }

    @Override
    public String exportOffers() {
        return offerRepository.findByApartment_ApartmentTypeOrderByApartment_AreaDescPrice(ApartmentType.three_rooms)
                .stream()
                .map(Offer::toString)
                .collect(Collectors.joining(System.lineSeparator()));

    }
}
