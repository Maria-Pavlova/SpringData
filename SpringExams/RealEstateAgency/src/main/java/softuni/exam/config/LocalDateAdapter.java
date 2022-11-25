package softuni.exam.config;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    @Override
    public LocalDate unmarshal(String input) throws Exception {
        return LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}
