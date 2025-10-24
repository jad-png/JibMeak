package com.taxist.JibMeak.mapper;

import com.taxist.JibMeak.dto.TourDTO;
import com.taxist.JibMeak.model.Tour;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TourMapper {
    @Mapping(source = "vehicle.id", target = "vehicleId")
    @Mapping(source = "warehouse.id", target = "warehouseId")
    TourDTO toDTO(Tour tour);

    @Mapping(target = "vehicle", ignore = true)
    @Mapping(target = "warehouse", ignore = true)
    @Mapping(target = "deliveries", ignore = true)
    Tour toEntity(TourDTO tourDTO);
}