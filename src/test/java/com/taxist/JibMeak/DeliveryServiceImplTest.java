package com.taxist.JibMeak;

import com.taxist.JibMeak.dto.DeliveryDTO;
import com.taxist.JibMeak.mapper.DeliveryMapper;
import com.taxist.JibMeak.model.Delivery;
import com.taxist.JibMeak.repository.DeliveryRepository;
import com.taxist.JibMeak.service.Impl.DeliveryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeliveryServiceImplTest {
    @Mock
    private DeliveryRepository repo;

    @Mock
    private DeliveryMapper mapper;

    @InjectMocks
    private DeliveryServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateDelivery() {
        DeliveryDTO dto = new DeliveryDTO();
        Delivery dv = new Delivery();

        dto.setLatitude(23.555);
        dto.setLongitude(23.2424);
        dto.setVolumeM3(23);
        dto.setWeightKg(1000);

        when(mapper.toEntity(dto)).thenReturn(dv);
        when(repo.save(dv)).thenReturn(dv);
        when(mapper.toDTO(dv)).thenReturn(dto);

        DeliveryDTO result = service.createDelivery(dto);

        assertNotNull(result);
        verify(repo, times(1)).save(dv);
    }

    @Test
    void testGetAllDeliveries() {
        Delivery dv = new Delivery();
        when(repo.findAll()).thenReturn(List.of(dv));
        when(mapper.toDTO(any(Delivery.class))).thenReturn(new DeliveryDTO());

        List<DeliveryDTO> result = service.getAllDeliveries();

        assertEquals(1, result.size());
        verify(repo, times(1)).findAll();
    }

    @Test
    void testGetDeliveryById() {
        Delivery delivery = new Delivery();
        when(repo.findById(1L)).thenReturn(Optional.of(delivery));
        when(mapper.toDTO(delivery)).thenReturn(new DeliveryDTO());

        Optional<DeliveryDTO> result = service.getDelivery(1L);

        assertTrue(result.isPresent());
    }

    @Test
    void testDeleteDelivery() {
        service.deleteDelivery(1L);
        verify(repo, times(1)).deleteById(1L);
    }
}
