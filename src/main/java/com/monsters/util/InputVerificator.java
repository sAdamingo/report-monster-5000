package com.monsters.util;

import com.monsters.Main;
import org.apache.log4j.Logger;

import java.io.File;
import java.time.LocalDate;

public class InputVerificator {

    private static final Logger log = Logger.getLogger(InputVerificator.class.getName());
    private String inputPath;
    private String outputPath;
    private int reportNumber;
    private LocalDate fromDate;
    private LocalDate toDate;

    public InputVerificator(String inputPath, String outputPath, int reportNumber, LocalDate fromDate, LocalDate toDate) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.reportNumber = reportNumber;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    private void verifyInputPath() {
        // jeśli input istnieje i jest folderem to true

        File f = new File(inputPath);

        if (!f.exists()) {
            inputPath = "./";
        }
    }

    private void verifyOutputPath() {
        // jeśli output nie istnieje (directory) to tworzysz folder
        // chyba że jest źle sformatowana to podajesz domyślną
        File f = new File(outputPath);
        boolean folderCreated = false;
        if (!f.exists()) {
            // output folder nie istnieje stwórz folder
            folderCreated = f.mkdirs();
        }
        if (!folderCreated) {
            this.outputPath = "./";
        }
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
        Returns correct inputPath or throws exception
     */
    public boolean verifyParameters() {
        // jeśli input istnieje i jest folderem to true
        // jeśli output nie istnieje (directory) to tworzysz folder
        // chyba że jest źle sformatowana to podajesz domyślną
        boolean report = verifyReportNumber();
        if (report) {
            verifyInputPath();
            verifyOutputPath();
            return true;
        } else {
            log.error("Wrong report type.");
            return false;
        }


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

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }
}
