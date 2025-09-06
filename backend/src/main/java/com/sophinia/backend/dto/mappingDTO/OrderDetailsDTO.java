package com.sophinia.backend.dto.mappingDTO;

public class OrderDetailsDTO {
    private Long orderId;
    private ProductDTO product;
    private ClothingModelDTO clothingModel;
    private DecorationDTO decoration;
    private DesignDTO design;
    private ClientDTO client;

    public OrderDetailsDTO (
            Long orderId,
            ProductDTO productDTO,
            ClothingModelDTO clothingModelDTO,
            DecorationDTO decorationDTO,
            DesignDTO designDTO,
            ClientDTO clientDTO
    ) {
        this.orderId = orderId;
        this.product = productDTO;
        this.clothingModel = clothingModelDTO;
        this.decoration = decorationDTO;
        this.design = designDTO;
        this.client = clientDTO;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public ClothingModelDTO getClothingModel() {
        return clothingModel;
    }

    public void setClothingModel(ClothingModelDTO clothingModel) {
        this.clothingModel = clothingModel;
    }

    public DecorationDTO getDecoration() {
        return decoration;
    }

    public void setDecoration(DecorationDTO decoration) {
        this.decoration = decoration;
    }

    public DesignDTO getDesign() {
        return design;
    }

    public void setDesign(DesignDTO design) {
        this.design = design;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }
}
