package com.taxist.JibMeak.service.Impl;

import com.taxist.JibMeak.algo.NearestNeighborOptimizer;
import com.taxist.JibMeak.dto.*;
import com.taxist.JibMeak.mapper.TourMapper;
import com.taxist.JibMeak.model.Delivery;
import com.taxist.JibMeak.model.Tour;
import com.taxist.JibMeak.model.Vehicle;
import com.taxist.JibMeak.model.Warehouse;
import com.taxist.JibMeak.repository.DeliveryRepository;
import com.taxist.JibMeak.repository.TourRepository;
import com.taxist.JibMeak.repository.VehicleRepository;
import com.taxist.JibMeak.repository.WarehouseRepository;
import com.taxist.JibMeak.service.interfaces.TourService;

import java.util.List;
import java.util.stream.Collectors;

public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;
    private final TourMapper tourMapper;
    private final WarehouseRepository whRepo;
    private final VehicleRepository vehRepo;
    private final DeliveryRepository dvRepo;
    private final NearestNeighborOptimizer optimizer;

    public TourServiceImpl(TourRepository trRepository, TourMapper trMapper, WarehouseRepository whRepo, DeliveryRepository dvRepo, VehicleRepository vhRepo, NearestNeighborOptimizer optimizer) {
        this.tourRepository = trRepository;
        this.tourMapper = trMapper;
        this.whRepo = whRepo;
        this.dvRepo = dvRepo;
        this.vehRepo = vhRepo;
        this.optimizer = optimizer;
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

    public TourDTO createOptimizedTour(TourOptimizationDTO request) {

        Warehouse wh = whRepo.findById(request.getWarehouseId())
                .orElseThrow(() -> new  RuntimeException("warehouse not found"));

        Vehicle vh = vehRepo.findById(request.getVehicleId())
                .orElseThrow(() -> new  RuntimeException("vehicle not found"));

        List<Delivery> dvs = dvRepo.findAllById(request.getDeliveryIds());

        Tour optimizedTour = optimizer.optimizeTour(wh, dvs, vh);

        Tour savedTour = tourRepository.save(optimizedTour);

        return tourMapper.toDTO(savedTour);
    }
}
