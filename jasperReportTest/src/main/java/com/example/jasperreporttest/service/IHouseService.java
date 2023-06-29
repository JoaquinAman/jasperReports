package com.example.jasperreporttest.service;

import com.example.jasperreporttest.model.House;
import com.example.jasperreporttest.model.HouseRequest;
import com.example.jasperreporttest.model.HouseResponse;

import java.util.List;

public interface IHouseService {
    void saveHouse(HouseRequest houseRequest);
    List<HouseResponse> getAllHouses();
    HouseResponse getHouseById(Long id);
    void deleteHouseById(Long id);
    void updateHouseById(Long id, HouseRequest houseRequest);
}
