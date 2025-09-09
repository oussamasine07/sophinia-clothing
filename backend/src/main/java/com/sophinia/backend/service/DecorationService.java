package com.sophinia.backend.service;

import com.sophinia.backend.bean.FileUpload;
import com.sophinia.backend.dto.validation.ValidateDecorationDTO;
import com.sophinia.backend.exception.NotFoundException;
import com.sophinia.backend.model.Decoration;
import com.sophinia.backend.repository.DecorationRepository;
import com.sophinia.backend.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DecorationService {

    private final DecorationRepository decorationRepository;
    private final FileUpload fileUpload;
    private final OrderRepository orderRepository;

    public DecorationService (
            final DecorationRepository decorationRepository,
            final FileUpload fileUpload,
            final OrderRepository orderRepository
    ) {
        this.decorationRepository = decorationRepository;
        this.fileUpload = fileUpload;
        this.orderRepository = orderRepository;
    }

    public ResponseEntity<List<Decoration>> getAllDecorations () {
        List<Decoration> decorations = decorationRepository.findAll();

        return new ResponseEntity<>(decorations, HttpStatus.OK);
    }

    public ResponseEntity<Decoration> getDecorationById (Long decorationId) {
        Decoration decoration = decorationRepository.findById( decorationId )
                .orElseThrow(() -> new NotFoundException("unfound decoration"));

        return new ResponseEntity<>(decoration, HttpStatus.OK);
    }

    public ResponseEntity<Decoration> createNewDecoration (ValidateDecorationDTO validateDecorationDTO) {
        Decoration decoration = new Decoration();
        decoration.setName(validateDecorationDTO.name());

        if (validateDecorationDTO.image() != null) {
            String image = fileUpload.upload( validateDecorationDTO.image(), "design");
            decoration.setImage(image);
        }

        return new ResponseEntity<>(decorationRepository.save(decoration), HttpStatus.OK);
    }

    public ResponseEntity<Decoration> updateDecoration (ValidateDecorationDTO validateDecorationDTO, Long decorationId) {
        Decoration decoration = decorationRepository.findById( decorationId )
                .orElseThrow(() -> new NotFoundException("you cant update an unfound decoration"));

        decoration.setName( validateDecorationDTO.name());

        if (validateDecorationDTO.image() != null) {
            String image = fileUpload.upload( validateDecorationDTO.image(), "design");
            decoration.setImage(image);
        }

        return new ResponseEntity<>(decorationRepository.save(decoration), HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> deleteDecoration ( Long decorationId ) {
        if (orderRepository.existsByDesignId(decorationId)) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "you can't remove a design related to orders");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        Decoration decoration = decorationRepository.findById( decorationId )
                .orElseThrow(() -> new NotFoundException("you cant delete an unfound decoration"));

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "decoration " + decoration.getName() + " removed");
        response.put("id", decoration.getId());

        decorationRepository.deleteById( decorationId );

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

}
