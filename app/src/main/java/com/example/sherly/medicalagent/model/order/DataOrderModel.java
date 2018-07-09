package com.example.sherly.medicalagent.model.order;

public class DataOrderModel {
    String _id, nama_pasien, jk, diagnosa, alamat;
    Integer jenis, status;
    Double lat, lng;

    public String get_id() {
        return _id;
    }

    public String getNama_pasien() {
        return nama_pasien;
    }

    public String getJk() {
        return jk;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public String getAlamat() {
        return alamat;
    }

    public Integer getJenis() {
        return jenis;
    }

    public Integer getStatus() {
        return status;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }
}
