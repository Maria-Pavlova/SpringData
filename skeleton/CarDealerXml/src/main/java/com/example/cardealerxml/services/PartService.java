package com.example.cardealerxml.services;

import com.example.cardealerxml.models.entities.Part;


import java.util.List;

public interface PartService {

    void seedParts(List<Part> parts);

    List<Part> getRandomParts();


}
