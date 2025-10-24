package com.taxist.JibMeak.service.interfaces;

import com.taxist.JibMeak.dto.TourDTO;

import java.util.List;

public interface TourService {
    TourDTO createTour(TourDTO dto);

    TourDTO getTourById(Long id);

    List<TourDTO> getAllTours();

    void deleteTour(Long id);
}
