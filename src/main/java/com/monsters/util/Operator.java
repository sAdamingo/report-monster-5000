package com.monsters.util;

import com.monsters.input.FileReader;
import com.monsters.output.ReportV1;

import java.io.File;
import java.util.Collection;
import java.util.List;

public class Operator {
    String inputPath;
    String outputPath;
    int reportNumber;

    public Operator(String inputPath, String outputPath, int reportNumber) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.reportNumber = reportNumber;
    }

    public void runReport(){
        FileSearcher fileSearcher = new FileSearcher();
        FileReader fileReader = new FileReader();
        List<Entry> entries;
        List<Entry> listEntries = null;
        Collection<File> files = fileSearcher.findFilesWithExtenstion("(.xls)", inputPath);
        for (File file: files ) {
            entries = fileReader.parseXLS(file.getPath());
            for (Entry entry: entries               ) {
                listEntries.add(entry);
            }
        }
        switch (reportNumber){
            case 1:
                ReportV1 reportV1 = new ReportV1(listEntries);
                reportV1.exportToConsole();
                reportV1.exportToExcel(outputPath);
                break;
            default:
                System.out.println("report not supported");
        }

    }

}
