package com.example.jsoncardealer.services;

import java.io.IOException;

public interface SaleService {
    void seedSales();

    void getSalesSummary() throws IOException;


}
