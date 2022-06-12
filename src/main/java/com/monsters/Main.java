package com.monsters;

import com.monsters.output.ReportV1;
import com.monsters.util.*;


import org.apache.log4j.Logger;

import java.time.LocalDate;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        log.info("App Startup");

        log.info("Welcome to REPORT MONSTER 5000!!!!1!");

        ArgumentParser parser = new ArgumentParser();
        if (parser.parseArgs(args)) {
            InputVerificator inputVerificator = new InputVerificator(parser.getInputPath(),
                    parser.getOutputPath(),
                    parser.getReportNumber(),
                    parser.getFromDate(),
                    parser.getTillDate(),
                    parser.isExportExcel(),
                    parser.isExportPDF());
            try {
                inputVerificator.verifyParameters();
                Operator operator = new Operator(inputVerificator.getInputPath(), inputVerificator.getOutputPath(), inputVerificator.getReportNumber(),inputVerificator.getFromDate(),inputVerificator.getToDate(),inputVerificator.isExportExcel(), inputVerificator.isExportPDF());
                operator.runReport();
            } catch(IllegalArgumentException e) {
                log.error(e);
            }
        } else{
            log.error("Input data not acceptable");
        }
        log.info("Quiting program");
    }
}
