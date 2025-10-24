package com.taxist.JibMeak.service.Impl;

import com.taxist.JibMeak.dto.TourDTO;
import com.taxist.JibMeak.mapper.TourMapper;
import com.taxist.JibMeak.model.Tour;
import com.taxist.JibMeak.repository.TourRepository;
import com.taxist.JibMeak.service.interfaces.TourService;

import java.util.List;
import java.util.stream.Collectors;

public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;
    private final TourMapper tourMapper;

    public TourServiceImpl(TourRepository trRepository, TourMapper trMapper) {
        this.tourRepository = trRepository;
        this.tourMapper = trMapper;
    }

    @Override
    public TourDTO createTour(TourDTO dto) {
        Tour tour = tourMapper.toEntity(dto);

        validateTour(tour);
        Tour savedTour = tourRepository.save(tour);
        return tourMapper.toDTO(savedTour);
    }

    @Override
    public TourDTO getTourById(Long id) {
        Tour tour = tourRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("tour not dound"));
        return tourMapper.toDTO(tour);
    }

    @Override
    public List<TourDTO> getAllTours() {
        return tourRepository.findAll().stream().map(tourMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteTour(Long id) {
        tourRepository.deleteById(id);
    }

    private void validateTour(Tour tour) {
        if (tour.getDate() == null) {
            throw new RuntimeException("tour date is required");
        }
    }
}
