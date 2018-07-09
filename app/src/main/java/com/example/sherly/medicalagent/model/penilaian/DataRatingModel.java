package com.example.sherly.medicalagent.model.penilaian;

public class DataRatingModel {
    String _id, tgl, komentar, jenis;
    Float rating;

    public String get_id() {
        return _id;
    }

    public Float getRating() {
        return rating;
    }

    public String getTgl() {
        return tgl;
    }

    public String getKomentar() {
        return komentar;
    }

    public String getJenis() {
        return jenis;
    }
}
