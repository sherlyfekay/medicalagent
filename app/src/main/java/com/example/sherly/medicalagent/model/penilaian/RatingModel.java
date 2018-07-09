package com.example.sherly.medicalagent.model.penilaian;

import java.util.ArrayList;

public class RatingModel {
    Integer count;
    String status;
    ArrayList<DataRatingModel> ratings;

    public Integer getCount() {
        return count;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<DataRatingModel> getRatings() {
        return ratings;
    }
}
