package com.monsters.util;

import com.monsters.Main;
import org.apache.commons.cli.*;
import org.apache.log4j.Logger;

import java.time.LocalDate;

public class ArgumentParser {
    private static final Logger log = Logger.getLogger(ArgumentParser.class.getName());
    private String inputPath;
     private String outputPath;
    private int reportNumber;

    private LocalDate fromDate;

    private LocalDate tillDate;

    public boolean parseArgs(String[] args) {
        Options options = new Options();
        options.addOption("i", true, "input directory");
        options.addOption("o", true, "output directory");
        options.addOption("h",false,"help");
        options.addOption("r", true, "report type [1-5]");
        options.addOption("f",true,"Date from which we will filter your data");
        options.addOption("t",true,"Date till which we will filter your data");


        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
            if (cmd.getOptions().length == 0){
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("No imput data. Please check possible options: ", options);
                return false;
            }
            if (cmd.hasOption("h") && cmd.getOptions().length > 1) {
                throw new ParseException("Parameter h can only go alone");
            } else {
                System.out.println("POMOC");
            }
            if (cmd.hasOption("r")) {
                String reportType = cmd.getOptionValue("r");
                try {
                    this.reportNumber = Integer.parseInt(reportType);
                } catch (NumberFormatException e) {
                    this.reportNumber = 0;
                    log.error("Report type should be a number between 1 and 5");
                }
            }
            if (cmd.hasOption("i")) {
                this.inputPath = cmd.getOptionValue("i");
            } else {
                this.inputPath = "";
            }
            if (cmd.hasOption("o")) {
                this.outputPath = cmd.getOptionValue("o");
            } else {
                this.outputPath = "";
            }
            if (cmd.hasOption("f")) {
              this.fromDate = cmd.getOptionValue(("f"));

            } else {

            }

        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("report FILE_PATH", options);
            Main.exitOnError("Argument parsing failed.  Reason: " + e.getMessage());
            return false;
        }
        return true;
    }

    public String getInputPath() {
        return inputPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public int getReportNumber() {
        return reportNumber;
    }

    public void setReportNumber(int reportNumber) {
        this.reportNumber = reportNumber;
    }
}
