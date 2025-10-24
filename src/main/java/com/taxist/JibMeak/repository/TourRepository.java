package com.taxist.JibMeak.repository.intefaces;

import com.taxist.JibMeak.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TourRepository extends JpaRepository<Tour, Long> {
    List<Tour> findByDate(LocalDate date);
    List<Tour> findByVehiculeId(Long vehiculeId);
}
