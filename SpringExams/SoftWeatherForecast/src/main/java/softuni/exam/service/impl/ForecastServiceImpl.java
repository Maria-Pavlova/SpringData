package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ForecastRootDto;
import softuni.exam.models.entity.DayOfWeek;
import softuni.exam.models.entity.Forecast;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.ForecastService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import static softuni.exam.constants.FilePath.*;

@Service
public class ForecastServiceImpl implements ForecastService {
    private final ForecastRepository forecastRepository;
    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final CityRepository cityRepository;

    @Autowired
    public ForecastServiceImpl(ForecastRepository forecastRepository, XmlParser xmlParser,
                               ValidationUtil validationUtil, ModelMapper modelMapper, CityRepository cityRepository) {
        this.forecastRepository = forecastRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.cityRepository = cityRepository;
    }

    @Override
    public boolean areImported() {
        return forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(Path.of(PATH_FORECASTS));
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        xmlParser.parseXml(PATH_FORECASTS, ForecastRootDto.class)
                .getForecasts()
                .stream()
                .filter(forecastDto -> {
                    boolean isValid = validationUtil.isValid(forecastDto);
                    if (forecastRepository.findByDayOfWeekAndCity_Id(forecastDto.getDayOfWeek(), forecastDto.getCity()).isPresent()) {
                        isValid = false;
                    }
                    sb.append(isValid ? String.format("Successfully imported forecast %s - %.2f",
                            forecastDto.getDayOfWeek(), forecastDto.getMaxTemperature())
                            : "Invalid forecast").append(System.lineSeparator());
                    return isValid;

                })
                .map(forecastDto -> {
                    Forecast forecast = modelMapper.map(forecastDto, Forecast.class);
                    forecast.setCity(cityRepository.findById(forecastDto.getCity()).get());
                    return forecast;
                })
                .forEach(forecastRepository::saveAndFlush);

        return sb.toString();
    }

    @Override
    public String exportForecasts() {
        return forecastRepository.findByDayOfWeekAndAndCity_PopulationLessThanOrderByMaxTemperatureDescId(DayOfWeek.SUNDAY, 150000)
                .stream()
                .map(Forecast::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
