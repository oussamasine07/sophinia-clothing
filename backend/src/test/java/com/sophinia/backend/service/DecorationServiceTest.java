package com.sophinia.backend.service;


import com.sophinia.backend.model.Decoration;
import com.sophinia.backend.repository.DecorationRepository;
import com.sophinia.backend.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DecorationServiceTest {

    @Mock
    DecorationRepository decorationRepository;
    @Mock
    OrderRepository orderRepository;

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
    void deleteDecoration () {
        Decoration d1 = new Decoration();
        d1.setId(1L);
        d1.setName("deco");

        when(orderRepository.existsByDesignId(1L)).thenReturn(false);
        when(decorationRepository.findById(1L)).thenReturn(Optional.of(d1));
        doNothing().when(decorationRepository).deleteById(1L);

        ResponseEntity<Map<String, Object>> result = decorationService.deleteDecoration(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("success", result.getBody().get("status"));
        assertEquals("decoration deco removed", result.getBody().get("message"));
        assertEquals(1L, result.getBody().get("id"));

    }
}























