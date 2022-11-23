package com.example.cardealerxml.services;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface SaleService {
    void seedSales();

    void getSalesSummary() throws IOException, JAXBException;


}
