package com.monsters;

import com.monsters.util.FileSearcher;


import com.monsters.util.ArgumentParser;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.Collection;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        log.info("App Startup");

        System.out.println("Welcome to REPORT MONSTER 5000!!!!1!");
        ArgumentParser parser = new ArgumentParser();
        parser.parseArgs(args);


        System.out.println(parser.getInputPath());
        System.out.println(parser.getOutputPath());
        System.out.println(parser.getReportNumber());

//        String xlsPath = parser.parseArgs(args);
//
//        boolean xlsDirectoryExists = FileSearcher.checkIfDirectoryExist(xlsPath);
//        if (!xlsDirectoryExists) {
//            exitOnError("Directory" + xlsPath + " doesn't exist!");
//        }
//
//        System.out.println("Searching for xls in path: " + xlsPath);
//        Collection<File> xlsFiles = FileSearcher.getXlsFiles(xlsPath);
//
//        if (xlsFiles.size() == 0) {
//            exitOnError("No files found.");
//        }

    }

    public static void exitOnError(String errorMessage) {
        System.err.println(errorMessage);
        System.exit(1);
    }
    
}
