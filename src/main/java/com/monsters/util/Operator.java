package com.monsters.util;

import com.monsters.input.FileReader;
import com.monsters.output.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Operator {
    String inputPath;
    String outputPath;
    int reportNumber;
    LocalDate from;
    LocalDate to;
    Boolean exportToExcel;

    public Operator(String inputPath, String outputPath, int reportNumber, LocalDate from, LocalDate to, Boolean exportToExcel) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.reportNumber = reportNumber;
        this.to = to;
        this.from = from;
        this.exportToExcel= exportToExcel;
    }

    public List<Entry> prepareData(){
        FileSearcher fileSearcher = new FileSearcher();
        FileReader fileReader = new FileReader();
        List<Entry> entries;
        List<Entry> listEntries = new ArrayList<>();
        Collection<File> files = fileSearcher.findFilesWithExtenstion("(.xls)", inputPath);
        for (File file: files ) {
            entries = fileReader.parseXLS(file.getPath(),from,to);
            for (Entry entry: entries               ) {
                listEntries.add(entry);
            }
        }
        return listEntries;
    }
    public void runReport(){
        List<Entry> listEntries;
        switch (reportNumber){
            case 1:
                listEntries = prepareData();
                ReportV1 reportV1 = new ReportV1(listEntries);
                reportV1.exportToConsole();
                if (exportToExcel){
                    reportV1.exportToExcel(outputPath);
                }
                break;
            case 2:
                listEntries = prepareData();
                ReportV2 reportV2 = new ReportV2(listEntries);
                reportV2.exportToConsole();
                if (exportToExcel){
                    reportV2.exportToExcel(outputPath);
                }
                break;
            case 3:
                listEntries = prepareData();
                ReportV3 reportV3 = new ReportV3(listEntries);
                reportV3.exportToConsole();
                if (exportToExcel){
                    reportV3.exportToExcel(outputPath);
                }
                break;
            case 4:
                listEntries = prepareData();
                ReportV4 reportV4 = new ReportV4(listEntries);
                reportV4.exportToConsole();
                if (exportToExcel){
                    reportV4.exportToExcel(outputPath);
                }
                break;
            case 5:
                listEntries = prepareData();
                ReportV5 reportV5 = new ReportV5(listEntries);
                reportV5.exportToConsole();
                if (exportToExcel){
                    reportV5.exportToExcel(outputPath);
                }
                reportV5.generateChart();
                break;
            default:
                System.out.println("report not supported");
        }

    }

}
