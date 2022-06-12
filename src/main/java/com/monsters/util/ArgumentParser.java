package com.monsters.util;

import com.monsters.Main;
import org.apache.commons.cli.*;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ArgumentParser {
    private static final Logger log = Logger.getLogger(ArgumentParser.class.getName());
    private String inputPath;
     private String outputPath;
    private int reportNumber;

    private LocalDate fromDate;

    private LocalDate tillDate;
    private boolean exportExcel;
    public boolean isExportExcel() {
        return exportExcel;
    }

    public void setExportExcel(boolean exportExcel) {
        this.exportExcel = exportExcel;
    }



    public boolean parseArgs(String[] args) {
        Options options = new Options();
        options.addOption("i", true, "input directory");
        options.addOption("o", true, "output directory");
        options.addOption("h",false,"help");
        options.addOption("r", true, "report type [1-5]");
        options.addOption("f",true,"Date from which we will filter your data");
        options.addOption("t",true,"Date till which we will filter your data");
        options.addOption("e",false,"Create excel report");



        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            boolean help = false;
            cmd = parser.parse(options, args);
            if (cmd.getOptions().length == 0){
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("No imput data. Please check possible options: ", options);
                return false;
            }
            if (cmd.hasOption("h")) {
                if (cmd.getOptions().length > 1) {
                    throw new ParseException("Parameter h can only go alone");
                } else {
                    HelpFormatter formatter = new HelpFormatter();
                    formatter.printHelp("report FILE_PATH", options);
                    help = true;
                }
            }
            if (!help) {
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
                    String date = cmd.getOptionValue(("f"));
                    try {
                        LocalDate from =  LocalDate.parse(date);
                        this.fromDate = from;
                    } catch (DateTimeParseException e) {
                        log.warn("From date badly formatted. – the text to parse should be formatted as follows: \"2007-12-03\"");
                        this.fromDate = LocalDate.MIN;
                    }
                } else {
                    this.fromDate = LocalDate.MIN;
                }
                if (cmd.hasOption("t")) {
                    String date = cmd.getOptionValue(("t"));
                    try {
                        LocalDate till =  LocalDate.parse(date);
                        this.tillDate = till;
                    } catch (DateTimeParseException e) {
                        log.warn("From date badly formatted. – the text to parse should be formatted as follows: \"2007-12-03\"");
                        this.tillDate = LocalDate.MAX;
                    }
                } else {
                    this.tillDate = LocalDate.MAX;
                }
                if (cmd.hasOption("e")) {
                    this.exportExcel = true;
                } else {
                    this.exportExcel = false;
                }
            } else {
                return false;
            }

        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("report FILE_PATH", options);
            exitOnError("Argument parsing failed.  Reason: " + e.getMessage());
            return false;
        }
        return true;
    }

    private void exitOnError(String errorMessage) {
        System.err.println(errorMessage);
        System.exit(1);
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

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getTillDate() {
        return tillDate;
    }

    public void setTillDate(LocalDate tillDate) {
        this.tillDate = tillDate;
    }
}
