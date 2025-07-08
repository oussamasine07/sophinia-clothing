package com.sophinia.backend.service;

import com.sophinia.backend.exception.NotFoundException;
import com.sophinia.backend.model.Design;
import com.sophinia.backend.repository.DesignRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesignService {

    private final DesignRepository designRepository;

    public DesignService (
            final DesignRepository designRepository
    ) {
        this.designRepository = designRepository;
    }

    public ResponseEntity<?> getAllDesigns () {
        List<Design> designs = designRepository.findAll();

        return new ResponseEntity<>( designs, HttpStatus.OK );
    }

    public ResponseEntity<?> getDesignById (Long id) {
        Design design = designRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Design not found"));

        return new ResponseEntity<>( design, HttpStatus.OK );
    }

    public ResponseEntity<?> createDesign ( Design design ) {
        Design newDesign = designRepository.save(design);

        return new ResponseEntity<>(newDesign, HttpStatus.OK);
    }

}
