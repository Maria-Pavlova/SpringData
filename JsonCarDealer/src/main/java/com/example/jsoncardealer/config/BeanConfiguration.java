package com.example.jsoncardealer.config;

import com.google.gson.*;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;


@Configuration
public class BeanConfiguration {

    @Bean
    public BufferedReader bufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelmapper = new ModelMapper();

        modelmapper.addConverter(new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(MappingContext<String, LocalDateTime> mappingContext) {
                return LocalDateTime.parse(mappingContext.getSource(),DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
            }
        });

        modelmapper.addConverter(new Converter<LocalDateTime, String>() {
            @Override
            public String convert(MappingContext<LocalDateTime, String> mappingContext) {
                return mappingContext.getSource().toString();
            }
        });
        return modelmapper;

//        Provider<LocalDateTime> localDateProvider = new AbstractProvider<LocalDateTime>() {
//            @Override
//            public LocalDateTime get() {
//                return LocalDateTime.now();
//            }
//        };

//        Converter<String, LocalDateTime> converter = new AbstractConverter<String, LocalDateTime>() {
//            @Override
//            protected LocalDateTime convert(String source) {
//                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
//                LocalDateTime localDateTime = LocalDateTime.parse(source, format);
//                return localDateTime;
//            }
//        };
    }

    @Bean
    public Gson gson() {

        return new GsonBuilder()
//                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
//                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
                .setPrettyPrinting()
                .create();

    }


}
