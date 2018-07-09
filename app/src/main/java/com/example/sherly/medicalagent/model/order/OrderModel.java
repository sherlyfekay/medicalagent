package com.example.sherly.medicalagent.model.order;

import java.util.ArrayList;

public class OrderModel {
    Integer count;
    String status;
    ArrayList<DataOrderModel> orders;

    public Integer getCount() {
        return count;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<DataOrderModel> getOrders() {
        return orders;
    }
}
