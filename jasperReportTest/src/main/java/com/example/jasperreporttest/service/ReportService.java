package com.example.jasperreporttest.service;

import com.example.jasperreporttest.common.utils.ColorUtils;
import com.example.jasperreporttest.model.House;
import com.example.jasperreporttest.model.ReportRequest;
import com.example.jasperreporttest.repository.IHouseRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.base.JRBaseBand;
import net.sf.jasperreports.engine.base.JRBaseElement;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

@Service
public class ReportService implements IReportService{
    @Autowired
    IHouseRepository houseRepository;
    private JRBaseBand band;

    @Override
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
//
//        String destFileName =
//                System.getProperty("user.home") +
//                File.separator +
//                "Descargas" +
//                File.separator +
//                "houses." +
//                reportFormat;
//
//        List<House> houses = houseRepository.findAll();
//        File file = ResourceUtils.getFile("classpath:houses.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(houses);
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("createdBy", "Daitek");
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
//        if (reportFormat.equalsIgnoreCase("html")) {
//            JasperExportManager.exportReportToHtmlFile(jasperPrint, destFileName);
//        }
//        if (reportFormat.equalsIgnoreCase("pdf")) {
//            JasperExportManager.exportReportToPdfFile(jasperPrint, destFileName);
//        }
//        return "Report generated in path : " + destFileName + " successfully";
        return null;
    }

    @Override
    public String exportReport(ReportRequest reportRequest) throws FileNotFoundException, JRException {

        ColorUtils.setColor(reportRequest);
        String reportFormat = reportRequest.getReportFormat();
        String destFileName =
                System.getProperty("user.home") +
                        File.separator +
                        "Descargas" +
                        File.separator +
                        "report." +
                        reportFormat;
        
        String reportPath = ResourceUtils.getFile("classpath:report.jrxml").getAbsolutePath();
        File file = ResourceUtils.getFile("classpath:report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JasperReport jasperReport2 = JasperCompileManager.compileReport(file.getAbsolutePath());
        
        // set X, Y, Width, Height
        band = (JRBaseBand) jasperReport2.getDetailSection().getBands()[1];
        String elementUUID = "625a8c94-2516-4ffb-be3d-dd34da2dc467";

        JRBaseElement element = null;
        for(JRChild child : band.getChildren()){
            if(child instanceof JRBaseElement && ((JRBaseElement) child).getUUID().toString().equals(elementUUID)){
                element = (JRBaseElement) child;
                break;
            }
        }
        String chart2UUID = "c2c9ecec-eac0-47bb-b253-5ed1e04dc747";
        JRBaseElement chart2 = null;
        for(JRChild child : band.getChildren()){
            if(child instanceof JRBaseElement && ((JRBaseElement) child).getUUID().toString().equals(chart2UUID)){
                chart2 = (JRBaseElement) child;
                break;
            }
        }
        int XtoFirstChart = chart2.getX();
        int XtoSecondChart = element.getX();

//        if(element != null){
        int newPosition = 10;
//        int newPositionY = 10;
        int size = 0;

        int newPositionY = 0; // Reemplaza con el nuevo valor de "y" deseado
        int newHeight = 200;

        if (element != null) {

            element.setX(XtoFirstChart);
            chart2.setX(XtoSecondChart);
            try {
                Field yField = JRBaseElement.class.getDeclaredField("y");
                yField.setAccessible(true);
                yField.set(element, newPositionY);

                Field heightField = JRBaseElement.class.getDeclaredField("height");
                heightField.setAccessible(true);
                heightField.set(element, newHeight);
//                element.setHeight(100);
                element.setWidth(200);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
                // Manejo de excepciones
            }
        }
// ---------------------------------------------------------------

        // set X, Y, Width, Height
        band = (JRBaseBand) jasperReport2.getDetailSection().getBands()[0];
        String secondElementUUID = "bb98f62e-6e70-45eb-b789-4cde24530dbf";

        // ---------------------------------------------------------------
        List<String> allUUIDs = new ArrayList<>();
        for(JRChild child : band.getChildren()){
            if(child instanceof JRBaseElement){
                allUUIDs.add(((JRBaseElement) child).getUUID().toString());
                System.out.println(((JRBaseElement) child).getUUID().toString());
            }
        }
        // ---------------------------------------------------------------
        JRBaseElement secondElement = null;
        for(JRChild child : band.getChildren()){
            if(child instanceof JRBaseElement && ((JRBaseElement) child).getUUID().toString().equals(secondElementUUID)){
                secondElement = (JRBaseElement) child;
                break;
            }
        }
        String thirdElementUUID = "c6997d2b-2837-43e7-8d5b-08dd92658942";
        JRBaseElement thirdElement = null;
        for(JRChild child : band.getChildren()){
            if(child instanceof JRBaseElement && ((JRBaseElement) child).getUUID().toString().equals(thirdElementUUID)){
                thirdElement = (JRBaseElement) child;
                break;
            }
        }

//        if(element != null){
        int secondPosition = 10;
//        int newPositionY = 10;
        int secondSize = 0;
        newPositionY = 0; // Reemplaza con el nuevo valor de "y" deseado
        newHeight = 200;
        int targetYforThirdElement = secondElement.getY();
        int targetYforSecondElement = thirdElement.getY();


        if (secondElement != null) {
            secondElement.setX(secondPosition);

            try {
                Field yField = JRBaseElement.class.getDeclaredField("y");
                yField.setAccessible(true);
//                yField.set(secondElement, newPositionY);
                yField.set(secondElement, targetYforSecondElement);

                Field heightField = JRBaseElement.class.getDeclaredField("height");
                heightField.setAccessible(true);
//                heightField.set(secondElement, newHeight);
//                element.setHeight(100);
//                secondElement.setWidth(200);
            } catch (NoSuchFieldException
                     | IllegalAccessException
                    e) {
                e.printStackTrace();
                // Manejo de excepciones
            }
        }

// ---------------------------------------------------------------

        // ------------------------
        // ------------------------

        //        if(element != null){
        int thirdPosition = 10;
//        int newPositionY = 10;
        int thirdSize = 0;
        newPositionY = 0; // Reemplaza con el nuevo valor de "y" deseado
        newHeight = 200;

        if (thirdElement != null) {
            thirdElement.setX(thirdPosition);


            try {
                Field yField = JRBaseElement.class.getDeclaredField("y");
                yField.setAccessible(true);
//                yField.set(thirdElement, newPositionY);
                yField.set(thirdElement, targetYforThirdElement);

                Field heightField = JRBaseElement.class.getDeclaredField("height");
                heightField.setAccessible(true);
//                heightField.set(secondElement, newHeight);
//                element.setHeight(100);
//                secondElement.setWidth(200);
            } catch (NoSuchFieldException
                     | IllegalAccessException
                    e) {
                e.printStackTrace();
                // Manejo de excepciones
            }
        }
        // ------------------------
        // ------------------------

//        band.setPosition(band.getPosition().getX(), newPositionY);
//        bands[bandIndex].setY(newPositionY);
//        band.setTopMargin(newPositionY);

        List<ReportRequest> reportRequestList = new ArrayList<>();
        reportRequestList.add(reportRequest);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportRequestList);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Daitek");
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport2, null, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, destFileName);
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, destFileName);
        }
        return "Report generated in path : " + destFileName + " successfullyy";    }
}
