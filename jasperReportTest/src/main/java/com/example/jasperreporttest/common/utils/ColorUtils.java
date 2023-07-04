package com.example.jasperreporttest.common.utils;

import com.example.jasperreporttest.model.ReportRequest;
import org.exolab.castor.dsml.XML;

import java.util.HashMap;
import java.util.Map;

import static org.eclipse.jdt.internal.compiler.parser.Parser.name;

public class ColorUtils {
    private static final Map<String, String> map = new HashMap<>();
    static {
        map.put("red", "#8A0402");
        map.put("green", "#00FF00");
        map.put("blue", "#0000FF");
    }
    public static String getColor(String color) {
        return map.get(color);
    }
    public static void setColor(ReportRequest reportRequest) {
        reportRequest.setColor(map.get(reportRequest.getColor()));
    }
}
