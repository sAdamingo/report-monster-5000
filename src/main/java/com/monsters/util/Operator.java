package com.monsters.util;

import com.monsters.input.FileReader;
import com.monsters.output.ReportV1;
import com.monsters.output.ReportV2;
import com.monsters.output.ReportV3;
import com.monsters.output.ReportV4;

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

    public Operator(String inputPath, String outputPath, int reportNumber, LocalDate from, LocalDate to) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.reportNumber = reportNumber;
        this.to = to;
        this.from = from;
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
                reportV1.exportToExcel(outputPath);
                break;
            case 2:
                listEntries = prepareData();
                ReportV2 reportV2 = new ReportV2(listEntries);
                reportV2.exportToConsole();
                reportV2.exportToExcel(outputPath);
                break;
            case 3:
                listEntries = prepareData();
                ReportV3 reportV3 = new ReportV3(listEntries);
                reportV3.exportToConsole();
                reportV3.exportToExcel(outputPath);
                break;
            case 4:
                listEntries = prepareData();
                ReportV4 reportV4 = new ReportV4(listEntries);
                reportV4.exportToConsole();
                reportV4.exportToExcel(outputPath);
                break;
            default:
                System.out.println("report not supported");
        }

    }

}
