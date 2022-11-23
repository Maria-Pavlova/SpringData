package com.example.cardealerxml.services.impl;

import com.example.cardealerxml.models.entities.Part;
import com.example.cardealerxml.repositories.PartRepository;
import com.example.cardealerxml.services.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;

    @Autowired
    public PartServiceImpl(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public void seedParts(List<Part> parts) {
        if (partRepository.count() == 0) {
            partRepository.saveAll(parts);
        }
    }

    @Override
    public List<Part> getRandomParts() {

        List<Part> parts = new ArrayList<>();
        int returnedCount = new Random().nextInt(10,20);

        for (int i = 0; i < returnedCount; i++) {
            long randId = new Random().nextLong(1, partRepository.count() + 1);
            Part part = partRepository.findById(randId).orElse(null);
            parts.add(part);
        }
       return parts;
        
    }
}
