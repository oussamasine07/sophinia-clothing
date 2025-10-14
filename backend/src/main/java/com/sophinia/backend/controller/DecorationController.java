package com.sophinia.backend.controller;

import com.sophinia.backend.dto.request.ValidateDecorationDTO;
import com.sophinia.backend.model.Decoration;
import com.sophinia.backend.service.DecorationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/decoration")
public class DecorationController {

    private final DecorationService decorationService;

    public DecorationController (
        final DecorationService decorationService
    ) {
        this.decorationService = decorationService;
    }

    @GetMapping
    public ResponseEntity<List<Decoration>> index () {
        return decorationService.getAllDecorations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Decoration> show (@PathVariable Long id) {
        return decorationService.getDecorationById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Decoration> create (@Valid ValidateDecorationDTO validateDecorationDTO) {

        return decorationService.createNewDecoration( validateDecorationDTO );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Decoration> update (@Valid ValidateDecorationDTO validateDecorationDTO, @PathVariable Long id) {


        return decorationService.updateDecoration( validateDecorationDTO, id );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete (@PathVariable Long id) {
        return decorationService.deleteDecoration(id);
    }


}


















