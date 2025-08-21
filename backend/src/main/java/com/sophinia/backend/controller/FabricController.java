package com.sophinia.backend.controller;


import com.sophinia.backend.dto.validation.FabricFormDTO;
import com.sophinia.backend.model.Fabric;
import com.sophinia.backend.service.FabricService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> create (@Valid FabricFormDTO fabricFormDTO) {


        return fabricService.createNewFabric( fabricFormDTO );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update (@Valid FabricFormDTO fabricFormDTO, @PathVariable Long id ) {

        return fabricService.updateFabric( fabricFormDTO, id );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        return fabricService.deleteFabric( id );
    }

}









