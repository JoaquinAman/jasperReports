package com.example.jasperreporttest.model;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;

@Data
@Entity
@Table(name = "house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String address;

    public House() {
    }
    public House(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
