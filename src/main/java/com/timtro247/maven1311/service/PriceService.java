package com.timtro247.maven1311.service;

import com.timtro247.maven1311.model.Prices;

import java.util.List;

public interface PriceService {
    List<Prices> getAllPrices ();
    List<Prices> findAll();
}
