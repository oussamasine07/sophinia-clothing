package com.sophinia.backend.service;


import com.sophinia.backend.bean.FileUpload;
import com.sophinia.backend.dto.request.ValidateDecorationDTO;
import com.sophinia.backend.exception.NotFoundException;
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
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DecorationServiceTest {

    @Mock
    DecorationRepository decorationRepository;

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    DecorationService decorationService;

    @Mock
    private FileUpload fileUpload;

    @Test
    void getAllDecorations () {
        Decoration d1 = new Decoration();
        d1.setName("decoration 1");

        Decoration d2 = new Decoration();
        d2.setName("decoration 2");

        List<Decoration> decorations = List.of( d1, d2 );

        when(decorationRepository.findAll()).thenReturn( decorations );

        ResponseEntity<List<Decoration>> result = decorationService.getAllDecorations();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertThat(result.getBody()).isNotNull();
        assertThat(result.getBody()).hasSize(2);
        assertThat(result.getBody()).extracting(Decoration::getName)
                .containsExactly("decoration 1", "decoration 2");

    }

    @Test
    void getDecorationById () {
        Decoration d = new Decoration();
        d.setId(1L);
        d.setName("decoration 1");
        d.setImage("desing image url");

        when(decorationRepository.findById(1L)).thenReturn(Optional.of( d ));

        ResponseEntity<Decoration> result = decorationService.getDecorationById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertThat(result.getBody()).isNotNull();
        assertEquals("decoration 1", result.getBody().getName());

    }

    @Test
    void createNewDecoration () {
        MockMultipartFile mockFile = new MockMultipartFile(
                "image",
                "test-image.jpg",
                "image/jpeg",
                "fake-image-content".getBytes()
        );

        ValidateDecorationDTO dto = new ValidateDecorationDTO("New Decoration", mockFile);

        // Step 2: Mock fileUpload.upload
        when(fileUpload.upload(mockFile, "decoration")).thenReturn("fake-image-url");

        // Step 3: Mock decorationRepository.save
        Decoration savedDecoration = new Decoration();
        savedDecoration.setName("New Decoration");
        savedDecoration.setImage("fake-image-url");
        when(decorationRepository.save(any(Decoration.class))).thenReturn(savedDecoration);

        // Step 4: Call the service
        ResponseEntity<Decoration> response = decorationService.createNewDecoration(dto);

        // Step 5: Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("New Decoration", response.getBody().getName());
        assertEquals("fake-image-url", response.getBody().getImage());

        // Step 6: Verify mocks were called
        verify(fileUpload).upload(mockFile, "decoration");
        verify(decorationRepository).save(any(Decoration.class));
    }

    @Test
    void updateDecoration () {

        MockMultipartFile mockFile = new MockMultipartFile(
                "image",
                "test-image.jpg",
                "image/jpeg",
                "fake-image-content".getBytes()
        );

        ValidateDecorationDTO dto = new ValidateDecorationDTO("New Decoration", mockFile);

        when(fileUpload.upload(mockFile, "decoration")).thenReturn("fake-image-url");

        Decoration foundDecoration = new Decoration();
        foundDecoration.setId(1L);
        foundDecoration.setName("old Decoration");
        foundDecoration.setImage("old fake-image-url");

        when(decorationRepository.findById(1L)).thenReturn(Optional.of( foundDecoration ));

        Decoration savedDecoration = new Decoration();
        savedDecoration.setName("New Decoration");
        savedDecoration.setImage("fake-image-url");

        foundDecoration.setName(savedDecoration.getName());
        foundDecoration.setImage(savedDecoration.getImage());

        when(decorationRepository.save(any(Decoration.class))).thenReturn(savedDecoration);

        ResponseEntity<Decoration> response = decorationService.updateDecoration(dto, 1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("New Decoration", response.getBody().getName());
        assertEquals("fake-image-url", response.getBody().getImage());

        verify(fileUpload).upload(mockFile, "decoration");
        verify(decorationRepository).save(any(Decoration.class));

    }

    @Test
    void deleteDecorationIfOrderExists () {

        Long decorationId = 1L;
        when(orderRepository.existsByDecorationId( decorationId )).thenReturn( true );

        ResponseEntity<Map<String, Object>> response = decorationService.deleteDecoration( decorationId );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("you can't remove a design related to orders", response.getBody().get("message"));

    }

    @Test
    void deleteDecorationIfOrderNotExist () {
        Long decorationId = 1L;
        when(orderRepository.existsByDecorationId( decorationId )).thenReturn( false );

        Decoration foundDecoration = new Decoration();
        foundDecoration.setId(1L);
        foundDecoration.setName("old Decoration");
        foundDecoration.setImage("old fake-image-url");

        when(decorationRepository.findById(1L)).thenReturn(Optional.of( foundDecoration ));

        ResponseEntity<Map<String, Object>> response = decorationService.deleteDecoration( decorationId );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("decoration " + foundDecoration.getName() + " removed" , response.getBody().get("message"));

        verify(orderRepository).existsByDecorationId(decorationId);
        verify(decorationRepository).deleteById(decorationId);
    }

    @Test
    void deleteDecorationIfNotExists () {
        Long decorationId = 1L;

        when(orderRepository.existsByDecorationId(decorationId)).thenReturn(false);
        when(decorationRepository.findById(decorationId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            decorationService.deleteDecoration(decorationId);
        });

        verify(orderRepository).existsByDecorationId(decorationId);
        verify(decorationRepository, never()).deleteById(anyLong());

    }

}























