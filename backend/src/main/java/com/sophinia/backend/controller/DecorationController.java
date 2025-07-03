package com.sophinia.backend.controller;

import com.sophinia.backend.service.DecorationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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




}
