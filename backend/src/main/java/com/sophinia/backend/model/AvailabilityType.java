package com.sophinia.backend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AvailabilityType {

    AT_HOME,
    AT_SHOP;

    @JsonCreator
    public static AvailabilityType fromString (String value) {
        try {
            return AvailabilityType.valueOf(value.toUpperCase());
        }
        catch (Exception e) {
            return null;
        }
    }

    @JsonValue
    public String getValue() {
        return name();
    }

}
