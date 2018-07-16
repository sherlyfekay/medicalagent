package com.example.sherly.medicalagent.model.histori;

public class DataHistoriModel {
    String _id, created_at, rating, jenis, nama_pasien, alamat_lengkap;
    Integer status;

    public String get_id() {
        return _id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getRating() {
        return rating;
    }

    public String getJenis() {
        return jenis;
    }

    public String getNama_pasien() {
        return nama_pasien;
    }

    public String getAlamat_lengkap() {
        return alamat_lengkap;
    }

    public Integer getStatus() {
        return status;
    }
}
