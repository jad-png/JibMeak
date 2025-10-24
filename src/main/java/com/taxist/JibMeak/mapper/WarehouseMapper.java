package com.taxist.JibMeak.mapper;

import com.taxist.JibMeak.dto.WarehouseDTO;
import com.taxist.JibMeak.model.Warehouse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {

    WarehouseMapper INSTANCE = Mappers.getMapper(WarehouseMapper.class);

    WarehouseDTO toDTO(Warehouse warehouse);

    Warehouse toEntity(WarehouseDTO warehouseDTO);
}