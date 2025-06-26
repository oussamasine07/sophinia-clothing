package com.sophinia.backend.controller;


import com.sophinia.backend.model.Fabric;
import com.sophinia.backend.service.FabricService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fabric")
public class FabricController {

    private final FabricService fabricService;

    public FabricController (
            final FabricService fabricService
    ) {
        this.fabricService = fabricService;
    }

    @GetMapping
    public ResponseEntity<?> index () {
        return fabricService.getFabrics();
    }

}
