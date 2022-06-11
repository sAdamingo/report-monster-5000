package com.monsters.util;

import org.apache.commons.cli.*;

public class ArgumentParser {
    public static String parseArgs(String[] args) {
        Options options = new Options();

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println("Parsing failed.  Reason: " + e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("report FILE_PATH", options);
            System.exit(1);
        }

        String xlsPath = "";

        try {
            xlsPath = cmd.getArgList().get(0);
        } catch (Exception e) {
            System.err.println("Parsing failed.  Reason: " + e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("report FILE_PATH", options);
            System.exit(1);
        }

        return xlsPath;
    }
}
