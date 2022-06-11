package com.monsters.util;

import java.io.File;

public class InputVerificator {

    private String inputPath;
    private String outputPath;
    private int reportNumber;

    public InputVerificator(String inputPath, String outputPath, int reportNumber) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.reportNumber = reportNumber;
    }

    private String verifyInputPath() {
        if (inputPath.equals("")) {
            return ".";
        }
        return inputPath;
    }

    private boolean verifyOutputPath() {
        File f = new File(outputPath);
        if (f.exists()) return false;
        return true;
    }

    private boolean verifyReportNumber() {
        if (    reportNumber == 1 ||
                reportNumber == 2 ||
                reportNumber == 3 ||
                reportNumber == 4 ||
                reportNumber == 5) {
            // valid reportNumber
            return true;
        }
        return false;
    }

    /*
        Returns correct outputPath or throws exception
     */
    public String veriftyParameters() {
        if (verifyOutputPath() && verifyReportNumber()) {
            return verifyInputPath();
        }
        throw new IllegalArgumentException("Niepoprawne argumenty");
    }
}
