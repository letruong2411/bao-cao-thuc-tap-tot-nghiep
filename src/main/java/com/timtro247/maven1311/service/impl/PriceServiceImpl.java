package com.timtro247.maven1311.service.impl;

import com.timtro247.maven1311.model.Prices;
import com.timtro247.maven1311.repository.PriceRepository;
import com.timtro247.maven1311.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    @Autowired
    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public List<Prices> getAllPrices() {
        return priceRepository.findAll();
    }

    @Override
    public List<Prices> findAll() {
        return priceRepository.findAllByDeletedAtNull();
    }
}
