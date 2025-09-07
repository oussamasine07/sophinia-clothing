package com.sophinia.backend.dto.mappingDTO;

public class ClothingModelDTO {

    private String name;
    private String image;

    public ClothingModelDTO (String cmName, String cmImage) {
        this.name = cmName;
        this.image = cmImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
