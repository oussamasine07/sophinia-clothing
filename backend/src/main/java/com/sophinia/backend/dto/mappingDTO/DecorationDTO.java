package com.sophinia.backend.dto.mappingDTO;

public class DecorationDTO {
    private String name;
    private String image;

    public DecorationDTO(String name, String image) {
        this.name = name;
        this.image = image;
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
