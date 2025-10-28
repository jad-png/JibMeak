package com.taxist.JibMeak;

import com.taxist.JibMeak.dto.WarehouseDTO;
import com.taxist.JibMeak.mapper.WarehouseMapper;
import com.taxist.JibMeak.model.Warehouse;
import com.taxist.JibMeak.repository.WarehouseRepository;
import com.taxist.JibMeak.service.Impl.WarehouseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WarehouseImplTest {

    @Mock private WarehouseRepository repo;
    @Mock private WarehouseMapper mapper;

    @InjectMocks
    private WarehouseImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateWarehouse() {
        WarehouseDTO dto = new WarehouseDTO();
        Warehouse entity = new Warehouse();

        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repo.save(entity)).thenReturn(entity);
        when(mapper.toDTO(entity)).thenReturn(dto);

        WarehouseDTO result = service.create(dto);

        assertNotNull(result);
        verify(repo).save(entity);
    }

    @Test
    void testFindAll() {
        when(repo.findAll()).thenReturn(List.of(new Warehouse()));
        when(mapper.toDTO(any(Warehouse.class))).thenReturn(new WarehouseDTO());

        List<WarehouseDTO> result = service.findAll();

        assertEquals(1, result.size());
        verify(repo).findAll();
    }

    @Test
    void testFindById() {
        Warehouse wh = new Warehouse();
        when(repo.findById(1L)).thenReturn(Optional.of(wh));
        when(mapper.toDTO(wh)).thenReturn(new WarehouseDTO());

        Optional<WarehouseDTO> result = service.findById(1L);

        assertTrue(result.isPresent());
    }

    @Test
    void testUpdateWarehouse() {
        WarehouseDTO dto = new WarehouseDTO();
        dto.setId(1L);
        Warehouse wh = new Warehouse();

        when(repo.findById(1L)).thenReturn(Optional.of(wh));
        when(repo.save(wh)).thenReturn(wh);
        when(mapper.toDTO(wh)).thenReturn(dto);

        WarehouseDTO result = service.update(dto);

        assertNotNull(result);
        verify(repo).save(wh);
    }

    @Test
    void testDeleteById() {
        service.deleteById(1L);
        verify(repo).deleteById(1L);
    }
}
