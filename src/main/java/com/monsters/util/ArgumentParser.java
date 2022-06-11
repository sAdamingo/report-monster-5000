package com.monsters.util;

import com.monsters.Main;
import org.apache.commons.cli.*;

public class ArgumentParser {
    public static String parseArgs(String[] args) {
        Options options = new Options();
        options.addOption("i", false, "input directory");
        options.addOption("o", false, "output directory");
        options.addOption("h",false,"help");
        options.addOption("r", true, "report type [1-5]");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("h")) {
                System.out.println("POMOC");
            } else if (cmd.hasOption("r")) {

            }
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("report FILE_PATH", options);
            Main.exitOnError("Argument parsing failed.  Reason: " + e.getMessage());
        }

        String xlsPath = "";

        try {
            xlsPath = cmd.getArgList().get(0);
        } catch (Exception e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("report FILE_PATH", options);
            Main.exitOnError("Argument parsing failed.  Reason: " + e.getMessage());
        }

        return xlsPath;
    }
}
