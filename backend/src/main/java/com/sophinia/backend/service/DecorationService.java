package com.sophinia.backend.service;

import com.sophinia.backend.model.Decoration;
import com.sophinia.backend.repository.DecorationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DecorationService {

    private final DecorationRepository decorationRepository;

    public DecorationService (
            final DecorationRepository decorationRepository
    ) {
        this.decorationRepository = decorationRepository;
    }

    public ResponseEntity<?> getAllDecorations () {
        List<Decoration> decorations = decorationRepository.findAll();

        return new ResponseEntity<>(decorations, HttpStatus.OK);
    }

    public void show () {

    }

    public ResponseEntity<?> createNewDecoration (Decoration decoration) {
        return new ResponseEntity<>(decorationRepository.save(decoration), HttpStatus.OK);
    }

    public void update () {

    }

    public void delete () {

    }

}
