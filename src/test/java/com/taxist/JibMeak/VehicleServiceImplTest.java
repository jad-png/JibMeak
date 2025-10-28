package com.taxist.JibMeak;

import com.taxist.JibMeak.dto.VehicleDTO;
import com.taxist.JibMeak.mapper.VehicleMapper;
import com.taxist.JibMeak.model.Vehicle;
import com.taxist.JibMeak.repository.VehicleRepository;
import com.taxist.JibMeak.service.Impl.VehicleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VehicleServiceImplTest {

    @Mock private VehicleRepository repo;
    @Mock private VehicleMapper mapper;

    @InjectMocks
    private VehicleServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateVehicle() {
        VehicleDTO dto = new VehicleDTO();
        Vehicle entity = new Vehicle();

        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repo.save(entity)).thenReturn(entity);
        when(mapper.toDTO(entity)).thenReturn(dto);

        VehicleDTO result = service.createVehicle(dto);

        assertNotNull(result);
        verify(repo).save(entity);
    }

    @Test
    void testGetAllVehicles() {
        when(repo.findAll()).thenReturn(List.of(new Vehicle()));
        when(mapper.toDTO(any(Vehicle.class))).thenReturn(new VehicleDTO());

        List<VehicleDTO> result = service.getAllVehicles();

        assertEquals(1, result.size());
        verify(repo).findAll();
    }

    @Test
    void testGetVehicleById() {
        Vehicle vehicle = new Vehicle();
        when(repo.findById(1L)).thenReturn(Optional.of(vehicle));
        when(mapper.toDTO(vehicle)).thenReturn(new VehicleDTO());

        Optional<VehicleDTO> result = service.getVehicleById(1L);

        assertTrue(result.isPresent());
        verify(repo).findById(1L);
    }

    @Test
    void testUpdateVehicle() {
        VehicleDTO dto = new VehicleDTO();
        dto.setId(1L);
        Vehicle existing = new Vehicle();
        when(repo.findById(1L)).thenReturn(Optional.of(existing));
        when(repo.save(existing)).thenReturn(existing);
        when(mapper.toDTO(existing)).thenReturn(dto);

        VehicleDTO result = service.updateVehicle(dto);

        assertNotNull(result);
        verify(repo).save(existing);
    }

    @Test
    void testDeleteVehicle() {
        service.deleteVehicle(1L);
        verify(repo).deleteById(1L);
    }
}
