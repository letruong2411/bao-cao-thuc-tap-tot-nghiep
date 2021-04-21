package com.timtro247.maven1311.repository;

import com.timtro247.maven1311.model.TypeRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRoomRepository extends JpaRepository<TypeRooms,Long> {

    TypeRooms findByTypeNameAndDeletedAtNull(String typeName);
    TypeRooms findByIdAndDeletedAtNull(Long id);
    List<TypeRooms> findAllByDeletedAtNull();
}

