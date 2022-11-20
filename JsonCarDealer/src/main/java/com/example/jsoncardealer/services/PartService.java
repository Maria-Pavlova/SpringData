package com.example.jsoncardealer.services;

import com.example.jsoncardealer.models.entities.Part;
import com.example.jsoncardealer.models.entities.Supplier;

import java.util.List;

public interface PartService {

    void seedParts(List<Part> parts);

    List<Part> getRandomParts();


}
