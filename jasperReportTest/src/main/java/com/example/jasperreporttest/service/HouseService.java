package com.example.jasperreporttest.service;

import com.example.jasperreporttest.model.House;
import com.example.jasperreporttest.model.HouseRequest;
import com.example.jasperreporttest.model.HouseResponse;
import com.example.jasperreporttest.repository.IHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HouseService implements IHouseService{
    @Autowired
    IHouseRepository houseRepository;
    @Override
    public void saveHouse(HouseRequest houseRequest) {
        House house = new House(houseRequest.getName(), houseRequest.getAddress());
        houseRepository.save(house);
    }

    @Override
    public List<HouseResponse> getAllHouses() {
        List<House> houses = houseRepository.findAll();
        List<HouseResponse> housesResponse = new ArrayList<>();
        for (House house : houses) {
            HouseResponse houseResponse = new HouseResponse(house.getName(), house.getAddress());
            housesResponse.add(houseResponse);
        }
        return housesResponse;
    }

    @Override
    public HouseResponse getHouseById(Long id) {
        return houseRepository.findById(id).map(house -> new HouseResponse(house.getName(), house.getAddress())).orElse(null);
    }

    @Override
    public void deleteHouseById(Long id) {
        houseRepository.deleteById(id);
    }

    @Override
    public void updateHouseById(Long id, HouseRequest houseRequest) {
        houseRepository.findById(id).map(house -> {
            house.setName(houseRequest.getName());
            house.setAddress(houseRequest.getAddress());
            return houseRepository.save(house);
        }).orElse(null);
    }
}
