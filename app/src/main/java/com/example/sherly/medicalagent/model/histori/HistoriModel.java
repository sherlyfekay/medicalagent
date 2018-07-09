package com.example.sherly.medicalagent.model.histori;

import java.util.ArrayList;

public class HistoriModel {
    Integer count;
    String status;
    ArrayList<DataHistoriModel> histories;

    public Integer getCount() {
        return count;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<DataHistoriModel> getHistories() {
        return histories;
    }
}
