package com.sophinia.backend.repository;

import com.sophinia.backend.model.ClothingModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClothingModelRepository extends JpaRepository<ClothingModel, Long> {
}
