package com.sophinia.backend.controller;

import com.sophinia.backend.dto.validation.DesignValidateDTO;
import com.sophinia.backend.model.Design;
import com.sophinia.backend.service.DesignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/design")
public class DesignController {

    private final DesignService designService;
    public DesignController (
            final DesignService designService
    ) {
        this.designService = designService;
    }

    @GetMapping
    public ResponseEntity<?> index () {
        return designService.getAllDesigns();
    }

    @PostMapping
    public ResponseEntity<?> create (@RequestBody DesignValidateDTO designValidateDTO) {
        Design design = new Design();

        design.setName(designValidateDTO.name());
        design.setImage(designValidateDTO.image());

        return designService.createDesign( design );

    }

}
