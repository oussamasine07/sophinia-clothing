package com.sophinia.backend.dto.response;

public class OrderMeasurementFieldDTO {
    private Long measurementId;
    private String name;

    public OrderMeasurementFieldDTO(Long measurementId, String name) {
        this.measurementId = measurementId;
        this.name = name;
    }

    public Long getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(Long measurementId) {
        this.measurementId = measurementId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
