package com.sophinia.backend.service;


import com.sophinia.backend.model.Decoration;
import com.sophinia.backend.repository.DecorationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.xml.transform.OutputKeys;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DecorationServiceTest {

    @Mock
    DecorationRepository decorationRepository;

    @InjectMocks
    DecorationService decorationService;


    @Test
    void getAllDecorations () {
        Decoration d1 = new Decoration();
        d1.setName("decoration 1");

        Decoration d2 = new Decoration();
        d2.setName("decoration 2");

        List<Decoration> decorations = List.of( d1, d2 );

        when(decorationRepository.findAll()).thenReturn( decorations );

        ResponseEntity result = decorationService.getAllDecorations();

        assertEquals(HttpStatus.OK, result.getStatusCode());

    }

    @Test
    void getDecorationById () {

        Decoration d1 = new Decoration();
        d1.setId(1L);
        d1.setName("decoration 1");

        when(decorationRepository.findById( 1L )).thenReturn( Optional.of( d1 ) );

        ResponseEntity result = decorationService.getDecorationById( 1L );

        assertEquals(HttpStatus.OK, result.getStatusCode());

    }

    @Test
    void createNewDecoration () {
        Decoration d1 = new Decoration();
        d1.setId(1L);
        d1.setName("decoration 1");

        when(decorationRepository.save(any(Decoration.class))).thenReturn( d1 );

        ResponseEntity result = decorationService.createNewDecoration( d1 );

        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void updateDecoration () {

        Decoration d1 = new Decoration();
        d1.setId(1L);
        d1.setName("decoration 1");

        Decoration updatedDeco = new Decoration();
        updatedDeco.setId(1L);
        updatedDeco.setName("deco update");

        when(decorationRepository.findById( 1L )).thenReturn( Optional.of( d1 ) );
        when(decorationRepository.save(any(Decoration.class))).thenReturn( d1 );

        ResponseEntity<Decoration> result = (ResponseEntity<Decoration>) decorationService.updateDecoration( updatedDeco, 1L );

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("deco update", result.getBody().getName());
    }

    @Test
    void deleteDecoration () {
        Decoration d1 = new Decoration();
        d1.setId(1L);
        d1.setName("decoration 1");

        when(decorationRepository.findById( 1L )).thenReturn( Optional.of( d1 ) );
        doNothing().when(decorationRepository).deleteById(1L);

        ResponseEntity<Map<String, String>> result = (ResponseEntity<Map<String, String>>) decorationService.deleteDecoration(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("success", result.getBody().get("status"));

    }
}























