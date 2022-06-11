package com.monsters.output;

public interface Report {

    void exportToConsole();
    void exportToExcel(String outputPath);
    void exportToPDF();
}
