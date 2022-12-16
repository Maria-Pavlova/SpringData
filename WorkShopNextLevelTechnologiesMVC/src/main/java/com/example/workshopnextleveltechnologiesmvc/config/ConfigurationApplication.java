package com.example.workshopnextleveltechnologiesmvc.config;

import com.example.workshopnextleveltechnologiesmvc.util.XmlParser;
import com.example.workshopnextleveltechnologiesmvc.util.XmlParserImpl;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class ConfigurationApplication {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public XmlParser xmlParser(){
        return new XmlParserImpl();
    }

}
