package com.example.sherly.medicalagent.model;

public class UpdateModel {
    String field, value;
    Double numValue;

    public UpdateModel(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public UpdateModel(String field, Double numValue) {
        this.field = field;
        this.numValue = numValue;
    }
}
