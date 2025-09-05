package com.sophinia.backend.controller;

import com.sophinia.backend.dto.validation.UpdateClientValidationDTO;
import com.sophinia.backend.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController (
            final ClientService clientService
    ) {
        this.clientService = clientService;
    }

    @PutMapping("/update-client-after-order")
    public ResponseEntity<?> upadateClientAfterOrder (@Valid @RequestBody UpdateClientValidationDTO updateClientValidationDTO) {
        return clientService.updateClientAfterOrder( updateClientValidationDTO );
    }

}
