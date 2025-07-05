package com.sophinia.backend.controller;

import com.sophinia.backend.dto.validation.ValidateDecorationDTO;
import com.sophinia.backend.model.Decoration;
import com.sophinia.backend.service.DecorationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> index () {
        return decorationService.getAllDecorations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show (@PathVariable Long id) {
        return decorationService.getDecorationById(id);
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody ValidateDecorationDTO validateDecorationDTO) {
        Decoration decoration = new Decoration();
        decoration.setName(validateDecorationDTO.name());

        return decorationService.createNewDecoration( decoration );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@Valid @RequestBody ValidateDecorationDTO validateDecorationDTO, @PathVariable Long id) {
        Decoration decoration = new Decoration();
        decoration.setName(validateDecorationDTO.name());

        return decorationService.updateDecoration( decoration, id );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        return decorationService.deleteDecoration(id);
    }


}


















