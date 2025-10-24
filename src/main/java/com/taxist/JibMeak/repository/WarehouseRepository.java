package com.taxist.JibMeak.repository.intefaces;

import com.taxist.JibMeak.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    // no complex queries just basic crud queries
}
