package com.taxist.JibMeak.repository.intefaces;

import com.taxist.JibMeak.model.Vehicle;
import com.taxist.JibMeak.model.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByType(VehicleType type);
}
