package com.timtro247.maven1311.repository;

import com.timtro247.maven1311.model.Areas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AreaRepository extends JpaRepository<Areas,Long> {
    Areas findByAreaName(String areaName);
    Areas findByIdAndDeletedAtNull(Long id);
    List<Areas> findAllByDeletedAtNull();
}
