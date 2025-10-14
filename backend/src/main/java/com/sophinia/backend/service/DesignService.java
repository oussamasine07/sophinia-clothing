package com.sophinia.backend.service;

import com.sophinia.backend.bean.FileUpload;
import com.sophinia.backend.dto.request.DesignValidateDTO;
import com.sophinia.backend.exception.NotFoundException;
import com.sophinia.backend.model.Decoration;
import com.sophinia.backend.model.Design;
import com.sophinia.backend.repository.DecorationRepository;
import com.sophinia.backend.repository.DesignRepository;
import com.sophinia.backend.repository.OrderRepository;
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
    private final OrderRepository orderRepository;
    private final DecorationRepository decorationRepository;

    public DesignService (
            final DesignRepository designRepository,
            final FileUpload fileUpload,
            final OrderRepository orderRepository,
            final DecorationRepository decorationRepository
    ) {
        this.designRepository = designRepository;
        this.fileUpload = fileUpload;
        this.orderRepository = orderRepository;
        this.decorationRepository = decorationRepository;
    }

    public ResponseEntity<List<Design>> getAllDesigns () {
        List<Design> designs = designRepository.findAll();

        return new ResponseEntity<>( designs, HttpStatus.OK );
    }

    public ResponseEntity<Design> getDesignById (Long id) {
        Design design = designRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Design not found"));

        return new ResponseEntity<>( design, HttpStatus.OK );
    }

    public ResponseEntity<Design> createDesign ( DesignValidateDTO designValidateDTO ) {
        Design design = new Design();
        design.setName(designValidateDTO.name());

        if (designValidateDTO.image() != null) {
            String image = fileUpload.upload( designValidateDTO.image(), "design");
            design.setImage(image);
        }
        design.setDescription( designValidateDTO.description());
        design.setCost( designValidateDTO.cost() );

        Decoration decoration = decorationRepository.findById(designValidateDTO.decoration_id())
                .orElseThrow(() -> new NotFoundException("Design not found"));

        design.setDecoration( decoration );

        Design newDesign = designRepository.save(design);

        return new ResponseEntity<>(newDesign, HttpStatus.OK);
    }

    public ResponseEntity<Design> updateDesignById (
            Long id,
            DesignValidateDTO designValidateDTO
    ) {
        Design updatedDesign = designRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("you can't update an unfound design"));

        updatedDesign.setName( designValidateDTO.name());

        if (designValidateDTO.image() != null) {
            // delete previouse image
            fileUpload.deleteFile(updatedDesign.getImage());

            // upload the new image
            String image = fileUpload.upload( designValidateDTO.image(), "decorations");
            updatedDesign.setImage(image);
        }

        updatedDesign.setDescription( designValidateDTO.description());
        updatedDesign.setCost( designValidateDTO.cost() );

        Decoration decoration = decorationRepository.findById(designValidateDTO.decoration_id())
                .orElseThrow(() -> new NotFoundException("Design not found"));

        updatedDesign.setDecoration( decoration );

        return new ResponseEntity<>( designRepository.save(updatedDesign), HttpStatus.OK);

    }

    public ResponseEntity<Map<String, Object>> deleteDesignById (Long id) {
        if (orderRepository.existsByDesignId(id)) {
            Map<String, Object> error = new HashMap<>();
            error.put("message", "you can't remove a design related to orders");
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        Design design = designRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("you can't delete a not found design"));

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", design.getName() + " hqs been Deleted");
        response.put("id", design.getId());

        designRepository.deleteById(id);

        fileUpload.deleteFile(design.getImage());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
