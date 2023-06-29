package com.example.jasperreporttest.service;

import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;

public interface IReportService {
    String exportReport(String reportFormat) throws FileNotFoundException, JRException;
}
