package com.sophinia.backend.repository;

import com.sophinia.backend.model.MeasurementValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementValueRepository extends JpaRepository<MeasurementValue, Long> {
}
