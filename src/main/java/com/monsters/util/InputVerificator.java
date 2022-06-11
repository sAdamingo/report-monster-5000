package com.monsters.util;

import com.monsters.Main;
import org.apache.log4j.Logger;

import java.io.File;

public class InputVerificator {

    private static final Logger log = Logger.getLogger(InputVerificator.class.getName());
    private String inputPath;
    private String outputPath;
    private int reportNumber;

    public InputVerificator(String inputPath, String outputPath, int reportNumber) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.reportNumber = reportNumber;
    }

    private boolean verifyInputPath() {
        if (inputPath.equals("")) {
            this.inputPath = "./";
            return true;
        }
        return true;
    }

    private boolean verifyOutputPath() {
        File f = new File(outputPath);
        if (f.exists()) {
            return false;
        }
        this.outputPath = "./";
        return true;
    }

    private boolean verifyReportNumber() {
        if (    reportNumber == 1 ||
                reportNumber == 2 ||
                reportNumber == 3 ||
                reportNumber == 4 ||
                reportNumber == 5) {
            // valid reportNumber
            this.reportNumber = reportNumber;
            return true;
        }
        return false;
    }

    /*
        Returns correct inputPath or throws exception
     */
    public void verifyParameters() {
        verifyOutputPath();
        verifyReportNumber();
        verifyInputPath();


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
