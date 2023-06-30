package com.example.jasperreporttest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequest {
    private String reportFormat;
    private String myText;
    private String image;
    private House house;
    private String color;
}
