package com.example.sherly.medicalagent.model.histori;

public class DetailHistoryModel {
    Integer jml_shift, status;
    String _id, created_at, jenis, nama_pasien, diagnosa, nama_agent, role;

    public Integer getJml_shift() {
        return jml_shift;
    }

    public String get_id() {
        return _id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getJenis() {
        return jenis;
    }

    public String getNama_pasien() {
        return nama_pasien;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public String getNama_agent() {
        return nama_agent;
    }

    public String getRole() {
        return role;
    }

    public Integer getStatus() {
        return status;
    }
}
