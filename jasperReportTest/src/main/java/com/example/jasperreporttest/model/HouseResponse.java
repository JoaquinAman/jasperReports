package com.example.jasperreporttest.model;

import lombok.Data;

@Data
public class HouseResponse {
    String name;
    String address;

    public HouseResponse() {
    }
    public HouseResponse(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
