package com.example.sherly.medicalagent.model.shift;

public class DetailShiftModel {
    String _id, tgl_shift, tindakan, kondisi, id_orderoffer;

    public DetailShiftModel(String tgl_shift, String tindakan, String kondisi, String id_orderoffer) {
        this.tgl_shift = tgl_shift;
        this.tindakan = tindakan;
        this.kondisi = kondisi;
        this.id_orderoffer = id_orderoffer;
    }

    public String get_id() {
        return _id;
    }

    public String getTgl_shift() {
        return tgl_shift;
    }

    public String getTindakan() {
        return tindakan;
    }

    public String getKondisi() {
        return kondisi;
    }

    public String getId_orderoffer() {
        return id_orderoffer;
    }
}
