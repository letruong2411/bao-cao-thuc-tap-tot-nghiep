package com.timtro247.maven1311.repository;

import com.timtro247.maven1311.model.Prices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Prices,Long> {

    List<Prices> findAllByDeletedAtNull();
    Prices findByIdAndDeletedAtNull(Long id);
}
