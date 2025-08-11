package com.sophinia.backend.controller;

import com.sophinia.backend.bean.FileUpload;
import com.sophinia.backend.dto.validation.DesignValidateDTO;
import com.sophinia.backend.model.Design;
import com.sophinia.backend.service.DesignService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/design")
public class DesignController {

    private final DesignService designService;
    private final FileUpload fileUpload;

    public DesignController (
            final DesignService designService,
            final FileUpload fileUpload
    ) {
        this.designService = designService;
        this.fileUpload = fileUpload;
    }

    @GetMapping
    public ResponseEntity<?> index () {
        return designService.getAllDesigns();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDesign (@PathVariable Long id) {
        return designService.getDesignById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create (
            @Valid DesignValidateDTO designValidateDTO
    ) {
        Design design = new Design();
        design.setName(designValidateDTO.name());

        if (designValidateDTO.image() != null) {
            String image = fileUpload.upload( designValidateDTO.image(), "decorations");
            design.setImage(image);
        }

        return designService.createDesign( design );

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update (
            @PathVariable Long id,
            @Valid DesignValidateDTO designValidateDTO
    ) {
        return designService.updateDesignById( id, designValidateDTO );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete (@PathVariable Long id) {
        return designService.deleteDesignById( id );
    }
}


















