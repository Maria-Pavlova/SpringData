package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ticket.TicketRootDto;
import softuni.exam.models.entities.Ticket;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.repository.TicketRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TicketService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static softuni.exam.constants.FilePath.PATH_TICKETS;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final TownRepository townRepository;
    private final PassengerRepository passengerRepository;
    private final PlaneRepository planeRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper, TownRepository townRepository, PassengerRepository passengerRepository, PlaneRepository planeRepository) {
        this.ticketRepository = ticketRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.townRepository = townRepository;
        this.passengerRepository = passengerRepository;
        this.planeRepository = planeRepository;
    }

    @Override
    public boolean areImported() {
        return ticketRepository.count() > 0;
    }

    @Override
    public String readTicketsFileContent() throws IOException {
        return Files.readString(Path.of(PATH_TICKETS));
    }

    @Override
    public String importTickets() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        xmlParser.fromFile(PATH_TICKETS, TicketRootDto.class)
                .getTickets()
                .stream()
                .filter(ticketDto -> {
                    boolean isValid = validationUtil.isValid(ticketDto);
                    if (ticketRepository.findBySerialNumber(ticketDto.getSerialNumber()).isPresent()) {
                        isValid = false;
                    }
                    sb.append(isValid ? String.format("Successfully imported Ticket %s - %s",
                            ticketDto.getFromTown().getName(), ticketDto.getToTown().getName())
                            : "Invalid Ticket").append(System.lineSeparator());
                    return isValid;
                })
                .map(ticketDto -> {
                            Ticket ticket = modelMapper.map(ticketDto, Ticket.class);
                            ticket.setFromTown(townRepository.findByName(ticketDto.getFromTown().getName()).get());
                            ticket.setToTown(townRepository.findByName(ticketDto.getToTown().getName()).get());
                            ticket.setPassenger(passengerRepository.findByEmail(ticketDto.getPassenger().getEmail()).get());
                            ticket.setPlane(planeRepository.findByRegisterNumber(ticketDto.getPlane().getRegisterNumber()).get());
                            return ticket;
                        })
                .forEach(ticketRepository::saveAndFlush);

        return sb.toString();
    }
}
