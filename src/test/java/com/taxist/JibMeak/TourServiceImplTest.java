package com.taxist.JibMeak;

import com.taxist.JibMeak.algo.NearestNeighborOptimizer;
import com.taxist.JibMeak.dto.*;
import com.taxist.JibMeak.mapper.TourMapper;
import com.taxist.JibMeak.model.*;
import com.taxist.JibMeak.repository.*;
import com.taxist.JibMeak.service.Impl.TourServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TourServiceImplTest {

    @Mock private TourRepository tourRepository;
    @Mock private TourMapper tourMapper;
    @Mock private WarehouseRepository warehouseRepository;
    @Mock private VehicleRepository vehicleRepository;
    @Mock private DeliveryRepository deliveryRepository;
    @Mock private NearestNeighborOptimizer optimizer;

    @InjectMocks
    private TourServiceImpl tourService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTour() {
        TourDTO dto = new TourDTO();
        Tour entity = new Tour();
        entity.setDate(java.time.LocalDate.now());

        when(tourMapper.toEntity(dto)).thenReturn(entity);
        when(tourRepository.save(entity)).thenReturn(entity);
        when(tourMapper.toDTO(entity)).thenReturn(dto);

        TourDTO result = tourService.createTour(dto);

        assertNotNull(result);
        verify(tourRepository, times(1)).save(entity);
    }

    @Test
    void testGetTourById() {
        Tour tour = new Tour();
        when(tourRepository.findById(1L)).thenReturn(Optional.of(tour));
        when(tourMapper.toDTO(tour)).thenReturn(new TourDTO());

        TourDTO result = tourService.getTourById(1L);

        assertNotNull(result);
        verify(tourRepository).findById(1L);
    }

    @Test
    void testGetAllTours() {
        when(tourRepository.findAll()).thenReturn(List.of(new Tour()));
        when(tourMapper.toDTO(any(Tour.class))).thenReturn(new TourDTO());

        List<TourDTO> result = tourService.getAllTours();

        assertEquals(1, result.size());
        verify(tourRepository, times(1)).findAll();
    }

    @Test
    void testDeleteTour() {
        tourService.deleteTour(1L);
        verify(tourRepository, times(1)).deleteById(1L);
    }

    @Test
    void testCreateOptimizedTour() {
        TourOptimizationDTO request = new TourOptimizationDTO();
        request.setWarehouseId(1L);
        request.setVehicleId(2L);
        request.setDeliveryIds(List.of(10L, 11L));

        Warehouse wh = new Warehouse();
        Vehicle vh = new Vehicle();
        List<Delivery> deliveries = List.of(new Delivery(), new Delivery());
        Tour optimized = new Tour();
        TourDTO dto = new TourDTO();

        when(warehouseRepository.findById(1L)).thenReturn(Optional.of(wh));
        when(vehicleRepository.findById(2L)).thenReturn(Optional.of(vh));
        when(deliveryRepository.findAllById(request.getDeliveryIds())).thenReturn(deliveries);
        when(optimizer.optimizeTour(wh, deliveries, vh)).thenReturn(optimized);
        when(tourRepository.save(optimized)).thenReturn(optimized);
        when(tourMapper.toDTO(optimized)).thenReturn(dto);

        TourDTO result = tourService.createOptimizedTour(request);

        assertNotNull(result);
        verify(tourRepository, times(1)).save(optimized);
    }
}
