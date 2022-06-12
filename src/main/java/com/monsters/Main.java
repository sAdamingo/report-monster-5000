package com.monsters;

import com.monsters.output.ReportV1;
import com.monsters.util.*;


import org.apache.log4j.Logger;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        log.info("App Startup");

        System.out.println("Welcome to REPORT MONSTER 5000!!!!1!");

        ArgumentParser parser = new ArgumentParser();
        if (parser.parseArgs(args)) {
            InputVerificator inputVerificator = new InputVerificator(parser.getInputPath(), parser.getOutputPath(), parser.getReportNumber());

            try {
                inputVerificator.verifyParameters();
                Operator operator = new Operator(inputVerificator.getInputPath(), inputVerificator.getOutputPath(), inputVerificator.getReportNumber());
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
