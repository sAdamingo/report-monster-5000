package com.monsters;

import org.apache.commons.cli.*;

import com.monsters.util.ArgumentParser;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to REPORT MONSTER 5000!!!!1!");

        String xlsPath = ArgumentParser.parseArgs(args);
        System.out.println("Searching for xls in path: " + xlsPath);

    }
    
}
