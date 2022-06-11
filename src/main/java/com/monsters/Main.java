package com.monsters;

import com.monsters.output.ReportV1;
import com.monsters.util.*;


import org.apache.log4j.Logger;

import java.io.File;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        log.info("App Startup");

        System.out.println("Welcome to REPORT MONSTER 5000!!!!1!");

        ArgumentParser parser = new ArgumentParser();
        parser.parseArgs(args);
        InputVerificator inputVerificator = new InputVerificator(parser.getInputPath(), parser.getOutputPath(), parser.getReportNumber());

        try {
            inputVerificator.verifyParameters();
            Operator operator = new Operator(inputVerificator.getInputPath(), inputVerificator.getOutputPath(), inputVerificator.getReportNumber());
            operator.runReport();
        } catch(IllegalArgumentException e) {
            log.error(e);
        }
    }

    public static void exitOnError(String errorMessage) {
        System.err.println(errorMessage);
        System.exit(1);
    }
    
}
