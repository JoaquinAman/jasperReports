package com.example.jasperreporttest.service;

import com.example.jasperreporttest.model.House;
import com.example.jasperreporttest.model.HouseResponse;
import com.example.jasperreporttest.repository.IHouseRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import java.io.*;
import java.util.*;

@Service
public class ReportService implements IReportService{
    @Autowired
    IHouseRepository houseRepository;
    @Override
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
//        String destFileName = "C:\\Users\\User\\Downloads\\houses." + reportFormat;
        String destFileName =
                // find path to downloads folder
                System.getProperty("user.home") +
                File.separator +
                "Downloads" +
                File.separator +
                "houses." +
                reportFormat;

        List<House> houses = houseRepository.findAll();
        File file = ResourceUtils.getFile("classpath:houses.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(houses);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Daitek");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, destFileName);
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, destFileName);
        }
        return "Report generated in path : " + destFileName + " successfully";
    }
}
