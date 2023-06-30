package com.example.jasperreporttest.service;

import com.example.jasperreporttest.model.ReportRequest;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;

public interface IReportService {
    String exportReport(String reportFormat) throws FileNotFoundException, JRException;
    String exportReport(ReportRequest reportRequest) throws FileNotFoundException, JRException;
}
