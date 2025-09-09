package com.sophinia.backend.service;

import com.sophinia.backend.dto.validation.ClientValidationDTO;
import com.sophinia.backend.dto.validation.UpdateClientValidationDTO;
import com.sophinia.backend.exception.NotFoundException;
import com.sophinia.backend.model.Client;
import com.sophinia.backend.repository.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder encoder;

    public ClientService (
            final ClientRepository clientRepository,
            final PasswordEncoder encoder
    ) {
        this.clientRepository = clientRepository;
        this.encoder = encoder;
    }

    public ResponseEntity<?> createClientOnOrder (ClientValidationDTO clientValidationDTO) {

        Client client = new Client();

        client.setFirstName( clientValidationDTO.firstName() );
        client.setLastName( clientValidationDTO.lastName() );
        client.setEmail( clientValidationDTO.email() );
        client.setPhone( clientValidationDTO.phone() );
        client.setAddress( clientValidationDTO.address() );

        Client savedClient = clientRepository.save( client );

        return new ResponseEntity<>( savedClient, HttpStatus.OK );
    }

    public ResponseEntity<Map<String, Object>> updateClientAfterOrder ( UpdateClientValidationDTO updateClientValidationDTO) {
        Client client = clientRepository.findClientByEmail( updateClientValidationDTO.email() )
                .orElseThrow(() -> new NotFoundException("this client not found"));

        client.setPassword(encoder.encode(updateClientValidationDTO.password()));
        Client savedClient = clientRepository.save( client );

        Map<String, Object> res = new HashMap<>();
        res.put("status", "success");
        res.put( "user", savedClient.getFirstName() + " " + savedClient.getLastName() );

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}



















