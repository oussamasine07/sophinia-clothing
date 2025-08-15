package com.sophinia.backend.service;

import com.sophinia.backend.bean.FileUpload;
import com.sophinia.backend.dto.validation.DesignValidateDTO;
import com.sophinia.backend.exception.NotFoundException;
import com.sophinia.backend.model.Design;
import com.sophinia.backend.repository.DesignRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DesignService {

    private final DesignRepository designRepository;
    private final FileUpload fileUpload;

    public DesignService (
            final DesignRepository designRepository,
            final FileUpload fileUpload
    ) {
        this.designRepository = designRepository;
        this.fileUpload = fileUpload;
    }

    public ResponseEntity<?> getAllDesigns () {
        List<Design> designs = designRepository.findAll();

        return new ResponseEntity<>( designs, HttpStatus.OK );
    }

    public ResponseEntity<?> getDesignById (Long id) {
        Design design = designRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Design not found"));

        return new ResponseEntity<>( design, HttpStatus.OK );
    }

    public ResponseEntity<?> createDesign ( Design design ) {
        Design newDesign = designRepository.save(design);

        return new ResponseEntity<>(newDesign, HttpStatus.OK);
    }

    public ResponseEntity<?> updateDesignById (
            Long id,
            DesignValidateDTO designValidateDTO
    ) {
        Design updatedDesign = designRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("you can't update a not found design"));

        Design design = new Design();
        design.setName( designValidateDTO.name());

        if (designValidateDTO.image() != null) {
            // delete previouse image
            fileUpload.deleteFile(updatedDesign.getImage());

            // upload the new image
            String image = fileUpload.upload( designValidateDTO.image(), "decorations");
            design.setImage(image);
        }

        updatedDesign.setName(design.getName());
        updatedDesign.setImage(design.getImage());

        return new ResponseEntity<>( designRepository.save(updatedDesign), HttpStatus.OK);

    }

    public ResponseEntity<?> deleteDesignById (Long id) {
        Design design = designRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("you can't delete a not found design"));

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", design.getName() + " hqs been Deleted");

        designRepository.deleteById(id);

        fileUpload.deleteFile(design.getImage());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
