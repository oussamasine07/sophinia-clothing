package com.sophinia.backend.service;


import com.sophinia.backend.bean.FileUpload;
import com.sophinia.backend.dto.validation.ValidateDecorationDTO;
import com.sophinia.backend.model.Decoration;
import com.sophinia.backend.repository.DecorationRepository;
import com.sophinia.backend.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

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

    }

    @Test
    void getDecorationById () {

        Decoration d1 = new Decoration();
        d1.setId(1L);
        d1.setName("decoration 1");

        when(decorationRepository.findById( 1L )).thenReturn( Optional.of( d1 ) );

        ResponseEntity<Decoration> result = decorationService.getDecorationById( 1L );

        assertEquals(HttpStatus.OK, result.getStatusCode());

    }

    @Test
    void createNewDecoration () {

        MultipartFile multipartFile = new MockMultipartFile(
                "image", "test.png", "image/png", "fake-image-content".getBytes()
        );

        ValidateDecorationDTO dto = new ValidateDecorationDTO("Test Decoration", multipartFile);

        Decoration savedDecoration = new Decoration();
        savedDecoration.setName("Test Decoration");
        savedDecoration.setImage("uploaded-image-path");

        when(fileUpload.upload(multipartFile, "design"))
                .thenReturn("uploaded-image-path");

        when(decorationRepository.save(any(Decoration.class)))
                .thenReturn(savedDecoration);

        // Act
        ResponseEntity<Decoration> response = decorationService.createNewDecoration(dto);

        // Assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getName()).isEqualTo("Test Decoration");
        assertThat(response.getBody().getImage()).isEqualTo("uploaded-image-path");

        verify(fileUpload, times(1)).upload(multipartFile, "design");
        verify(decorationRepository, times(1)).save(any(Decoration.class));

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
        assertEquals("success", Objects.requireNonNull(result.getBody()).get("status"));
        assertEquals("decoration deco removed", result.getBody().get("message"));
        assertEquals(1L, result.getBody().get("id"));

    }
}























