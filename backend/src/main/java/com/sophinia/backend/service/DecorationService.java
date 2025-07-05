package com.sophinia.backend.service;

import com.sophinia.backend.exception.NotFoundException;
import com.sophinia.backend.model.Decoration;
import com.sophinia.backend.repository.DecorationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public ResponseEntity<?> getDecorationById (Long decorationId) {
        Decoration decoration = decorationRepository.findById( decorationId )
                .orElseThrow(() -> new NotFoundException("unfound decoration"));

        return new ResponseEntity<>(decoration, HttpStatus.OK);
    }

    public ResponseEntity<?> createNewDecoration (Decoration decoration) {
        return new ResponseEntity<>(decorationRepository.save(decoration), HttpStatus.OK);
    }

    public ResponseEntity<?> updateDecoration (Decoration updatedDecoration, Long decorationId) {
        Decoration decoration = decorationRepository.findById( decorationId )
                .orElseThrow(() -> new NotFoundException("you cant update an unfound decoration"));

        decoration.setName( updatedDecoration.getName());

        return new ResponseEntity<>(decorationRepository.save(decoration), HttpStatus.OK);
    }

    public ResponseEntity<?> deleteDecoration ( Long decorationId ) {
        Decoration decoration = decorationRepository.findById( decorationId )
                .orElseThrow(() -> new NotFoundException("you cant delete an unfound decoration"));

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "decoration " + decoration.getName() + " removed");
        decorationRepository.deleteById( decorationId );

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
