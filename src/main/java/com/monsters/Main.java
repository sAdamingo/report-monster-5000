package com.monsters;

import com.monsters.output.ReportV1;
import com.monsters.util.Entry;
import com.monsters.util.FileSearcher;


import com.monsters.util.ArgumentParser;
import org.apache.log4j.Logger;

import java.io.File;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        log.info("App Startup");

        System.out.println("Welcome to REPORT MONSTER 5000!!!!1!");

        String xlsPath = ArgumentParser.parseArgs(args);

        boolean xlsDirectoryExists = FileSearcher.checkIfDirectoryExist(xlsPath);
        if (!xlsDirectoryExists) {
            exitOnError("Directory" + xlsPath + " doesn't exist!");
        }

        System.out.println("Searching for xls in path: " + xlsPath);
        Collection<File> xlsFiles = FileSearcher.getXlsFiles(xlsPath);

        if (xlsFiles.size() == 0) {
            exitOnError("No files found.");
        }

        Entry entry = new Entry(LocalDate.MAX, "Projekt", "taskname", 3.14, "user");
        List<Entry> entryList = new LinkedList<>();
        entryList.add(entry);
        ReportV1 reportV1 = new ReportV1(entryList);
        reportV1.exportToExcel();

    }

    public static void exitOnError(String errorMessage) {
        System.err.println(errorMessage);
        System.exit(1);
    }
    
}
