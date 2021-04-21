package com.timtro247.maven1311.service.impl;

import com.timtro247.maven1311.model.Areas;
import com.timtro247.maven1311.repository.AreaRepository;
import com.timtro247.maven1311.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areasRepository;

    @Autowired
    public AreaServiceImpl(AreaRepository areasRepository) {
        this.areasRepository = areasRepository;
    }

    @Override
    public List<Areas> getAllAreas() {
        return areasRepository.findAllByDeletedAtNull();
    }
}
