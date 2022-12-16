package com.example.workshopnextleveltechnologiesmvc.config;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateXml extends XmlAdapter<String, LocalDate> {
    @Override
    public LocalDate unmarshal(String s) throws Exception {
        return LocalDate.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String marshal(LocalDate localDate) throws Exception {
        return localDate.toString();
    }
}
