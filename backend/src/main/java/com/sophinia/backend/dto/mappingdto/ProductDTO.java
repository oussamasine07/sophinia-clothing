package com.sophinia.backend.dto.mappingdto;

import java.util.List;

public class ProductDTO {

    private String name;
    private String description;
    private String image;
    private List<OrderMeasurementFieldDTO> fields;

    public ProductDTO (String pName, String pImage, String pDescription) {
        this.name = pName;
        this.description = pDescription;
        this.image = pImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<OrderMeasurementFieldDTO> getFields() {
        return fields;
    }

    public void setFields(List<OrderMeasurementFieldDTO> fields) {
        this.fields = fields;
    }
}
