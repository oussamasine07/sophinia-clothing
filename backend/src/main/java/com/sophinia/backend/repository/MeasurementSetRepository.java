package com.sophinia.backend.repository;

import com.sophinia.backend.model.MeasurementSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementSetRepository extends JpaRepository<MeasurementSet, Long> {
}
