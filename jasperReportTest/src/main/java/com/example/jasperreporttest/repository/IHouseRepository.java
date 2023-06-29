package com.example.jasperreporttest.repository;

import com.example.jasperreporttest.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHouseRepository extends JpaRepository<House, Long> {
}
