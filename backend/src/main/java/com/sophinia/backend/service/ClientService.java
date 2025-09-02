package com.sophinia.backend.service;

import com.sophinia.backend.dto.validation.ClientValidationDTO;
import com.sophinia.backend.model.Client;
import com.sophinia.backend.repository.ClientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService (
            final ClientRepository clientRepository
    ) {
        this.clientRepository = clientRepository;
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

}
