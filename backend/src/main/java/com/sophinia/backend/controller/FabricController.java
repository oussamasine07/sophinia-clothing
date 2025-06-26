package com.sophinia.backend.controller;


import com.sophinia.backend.dto.FabricFormDTO;
import com.sophinia.backend.model.Fabric;
import com.sophinia.backend.service.FabricService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody FabricFormDTO fabricFormDTO) {
        Fabric fabric = new Fabric();

        fabric.setName(fabricFormDTO.name());
        fabric.setDescription( fabricFormDTO.description() );

        return fabricService.createNewFabric( fabric );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update (@Valid @RequestBody FabricFormDTO fabricFormDTO, @PathVariable Long id ) {
        Fabric fabric = new Fabric();

        fabric.setName(fabricFormDTO.name());
        fabric.setDescription( fabricFormDTO.description() );

        return fabricService.updateFabric( fabric, id );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        return fabricService.deleteFabric( id );
    }

}









