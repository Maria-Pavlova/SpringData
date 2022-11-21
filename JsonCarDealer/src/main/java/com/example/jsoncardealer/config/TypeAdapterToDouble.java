package com.example.jsoncardealer.config;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class TypeAdapterToDouble extends TypeAdapter<Double> {
    @Override
    public void write(JsonWriter jsonWriter, Double value) throws IOException {
        jsonWriter.value(String.format("%.2f", value ));
    }

    @Override
    public Double read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
