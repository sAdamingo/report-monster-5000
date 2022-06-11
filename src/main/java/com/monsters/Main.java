package com.monsters;

import com.monsters.util.FileSearcher;
import org.apache.commons.cli.*;

import com.monsters.util.ArgumentParser;

import java.io.File;
import java.util.Collection;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to REPORT MONSTER 5000!!!!1!");

        String xlsPath = ArgumentParser.parseArgs(args);
        System.out.println("Searching for xls in path: " + xlsPath);

        Collection<File> xlsFiles = FileSearcher.getXlsFiles(xlsPath);

    }
    
}
