package com.timtro247.maven1311.service.impl;

import com.timtro247.maven1311.model.TypeRooms;
import com.timtro247.maven1311.repository.TypeRoomRepository;
import com.timtro247.maven1311.service.TypeRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeRoomServiceImpl implements TypeRoomService {

    private final TypeRoomRepository typeRoomRepository;

    @Autowired
    public TypeRoomServiceImpl(TypeRoomRepository typeRoomRepository) {
        this.typeRoomRepository = typeRoomRepository;
    }

    @Override
    public List<TypeRooms> findAllTypeRooms() {
        return typeRoomRepository.findAllByDeletedAtNull();
    }
}