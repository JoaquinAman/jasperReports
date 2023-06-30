package com.example.jasperreporttest.controller;

import com.example.jasperreporttest.model.HouseRequest;
import com.example.jasperreporttest.model.HouseResponse;
import com.example.jasperreporttest.model.ReportRequest;
import com.example.jasperreporttest.service.IHouseService;
import com.example.jasperreporttest.service.IReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/house")
public class HouseController {
    @Autowired
    IHouseService houseService;
    @Autowired
    IReportService reportService;
    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws JRException, FileNotFoundException {
        return reportService.exportReport(format);
    }
    @GetMapping("/report")
    public String generateReport(@RequestBody ReportRequest reportRequest) throws JRException, FileNotFoundException {
        return reportService.exportReport(reportRequest);
    }
    @GetMapping
    public List<HouseResponse> getAll(){
        return houseService.getAllHouses();
    }
    @GetMapping("/{id}")
    public HouseResponse getById(@PathVariable("id") Long id){
        return houseService.getHouseById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        houseService.deleteHouseById(id);
    }
    @PostMapping
    public void save(@RequestBody HouseRequest houseRequest){
        houseService.saveHouse(houseRequest);
    }
    @PutMapping("/{id}")
    public void updateById(@PathVariable("id") Long id, @RequestBody HouseRequest houseRequest){
        houseService.updateHouseById(id, houseRequest);
    }

}
