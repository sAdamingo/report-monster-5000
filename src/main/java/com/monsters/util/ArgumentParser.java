package com.monsters.util;

import com.monsters.Main;
import org.apache.commons.cli.*;

public class ArgumentParser {

    String inputPath;
    String outputPath;
    int reportNumber;

    public static String parseArgs(String[] args) {
        Options options = new Options();
        options.addOption("i", false, "input directory");
        options.addOption("o", false, "output directory");
        options.addOption("h",false,"help");
        options.addOption("r", false, "report type [1-5]");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("h") || cmd.getOptions().length > 1) {
                throw new ParseException("Parameter h can only go alone");
            } else {
                System.out.println("POMOC");
            }
            if (cmd.hasOption("r")) {
                String reportType = cmd.getOptionValue("r");
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
