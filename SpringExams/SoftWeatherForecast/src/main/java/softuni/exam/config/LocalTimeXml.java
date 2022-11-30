package softuni.exam.config;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeXml extends XmlAdapter<String, LocalTime> {
    @Override
    public LocalTime unmarshal(String s) throws Exception {
        return LocalTime.parse(s, DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    @Override
    public String marshal(LocalTime localTime) throws Exception {
        return localTime.toString();
    }
}
