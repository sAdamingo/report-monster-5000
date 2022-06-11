package com.monsters;

import org.apache.commons.cli.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to REPORT MONSTER 5000!!!!1!");

        Options options = new Options();

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println("Parsing failed.  Reason: " + e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("report FILE_PATH", options);
        }

        String xlsPath = null;
        try {
            xlsPath = cmd.getArgList().get(0);
        } catch (Exception e) {
            System.err.println("Parsing failed.  Reason: " + e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("report FILE_PATH", options);
        }

        System.out.println("Searching for xls in path: " + xlsPath);

    }
    
}
