package com.sophinia.backend.repository;

import com.sophinia.backend.model.ClothingType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothingTypeRepository extends JpaRepository<ClothingType, Long> {
}
