package com.example.sherly.medicalagent.model;

public class UpdateModel {
    String field, value;
    Double numValue;
    Integer intValue;

    public UpdateModel(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public UpdateModel(String field, Double numValue) {
        this.field = field;
        this.numValue = numValue;
    }

    public UpdateModel(String field, Integer intValue) {
        this.field = field;
        this.intValue = intValue;
    }
}
