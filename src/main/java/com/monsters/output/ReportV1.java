package com.monsters.output;

import com.monsters.util.Entry;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ReportV1 implements Report {
    List<Entry> entryList;

    public ReportV1(List<Entry> entryList) {
        this.entryList = entryList;
    }

    HashMap<String, Double> sumEntryList(List<Entry> entryList) {
        HashMap<String, Double> summedEntries = new HashMap<>();
        for (Entry entry : entryList) {
            if (summedEntries.containsKey(entry.getUser())) {
                Double sum = summedEntries.get(entry.getUser());
                summedEntries.put(entry.getUser(), sum + entry.getDuration());
            } else {
                summedEntries.put(entry.getUser(), entry.getDuration());
            }
        }
        return summedEntries;
    }

    String printHashMap(HashMap<String, Double> summedEntries) {
        String keyHeader = "Imie Nazwisko";
        int maxKey = keyHeader.length();
        String valueHeader = "Liczba godzin";
        int maxValue = valueHeader.length();

        for (String element : summedEntries.keySet()) {
            if (maxKey < element.length()) {
                maxKey = element.length();
            }
            if (maxValue < summedEntries.get(element).toString().length()) {
                maxValue = summedEntries.get(element).toString().length();
            }
        }
        String result = (keyHeader + " ".repeat(maxKey)).substring(0,maxKey) + " | " + (valueHeader + " ".repeat(maxValue)).substring(0,maxValue) + "\n";

        for (String element : summedEntries.keySet()) {
            result = result + (element + " ".repeat(maxKey)).substring(0,maxKey) + " | " + (summedEntries.get(element).toString() + " ".repeat(maxValue)).substring(0,maxValue) + "\n";
        }
        return result;
    }

    @Override
    public void exportToConsole() {
        HashMap<String, Double> mapToPrint = sumEntryList(entryList);
        String stringToPrint = printHashMap(mapToPrint);
        System.out.println(stringToPrint);
    }

    @Override
    public void exportToExcel() {

    }

    @Override
    public void exportToPDF() {

    }
}
