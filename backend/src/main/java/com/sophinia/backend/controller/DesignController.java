package com.sophinia.backend.controller;

import com.sophinia.backend.service.DesignService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
