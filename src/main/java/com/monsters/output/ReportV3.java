package com.monsters.output;

import com.monsters.util.Entry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ReportV3 implements Report {
    List<Entry> entryList;

        HashMap<String, HashMap<String, Double>> sumEntryList(List<Entry> entryList) {


        List<String> users = entryList.stream().map(e -> e.getUser()).distinct().collect(Collectors.toList());
        List<String> projects = entryList.stream().map(e -> e.getProject()).distinct().collect(Collectors.toList());

        HashMap<String, HashMap<String, Double>> summedEntries = new HashMap<>();

        for (Entry entry : entryList) {
            if (summedEntries.containsKey(entry.getUser())) {
                HashMap<String, Double> internalEntries = summedEntries.get(entry.getUser());
                if (internalEntries.containsKey(entry.getProject())) {
                    Double sum = internalEntries.get(entry.getProject());
                    internalEntries.put(entry.getProject(), sum + entry.getDuration());
                }else{
                    internalEntries.put(entry.getProject(), entry.getDuration());
                }
                summedEntries.put(entry.getUser(), internalEntries);
            } else {
                HashMap<String, Double> internalEntries = new HashMap<>();
                internalEntries.put(entry.getProject(), entry.getDuration());
                summedEntries.put(entry.getUser(), internalEntries);
            }
        }
        return summedEntries;
    }

    String printHashMap(HashMap<String, HashMap<String, Double>> summedEntries) {
        String keyHeader = "Imie Nazwisko";
        int maxKey = keyHeader.length();
        String keyHeader2 = "Projekt";
        int maxKey2 = keyHeader2.length();
        String valueHeader = "Liczba godzin";
        int maxValue = valueHeader.length();

        for (String entry : summedEntries.keySet()) {
            if (maxKey < entry.length()) {
                maxKey = entry.length();
            }
            HashMap<String, Double> internalEntries = summedEntries.get(entry);
            for (String entry2 : internalEntries.keySet()) {
                if (maxKey2 < entry2.length()) {
                    maxKey2 = entry2.length();
                }
                if (maxValue < internalEntries.get(entry2).toString().length()) {
                    maxValue = internalEntries.get(entry2).toString().length();
                }

            }

        }
        String result = (keyHeader + " ".repeat(maxKey)).substring(0,maxKey) + " | " +(keyHeader2 + " ".repeat(maxKey2)).substring(0,maxKey2) + " | " + (valueHeader + " ".repeat(maxValue)).substring(0,maxValue) + "\n";

        for (String entry : summedEntries.keySet()) {
            HashMap<String, Double> internalEntries = summedEntries.get(entry);
            for (String entry2 : internalEntries.keySet()) {
                result = result + (entry + " ".repeat(maxKey)).substring(0, maxKey) + " | " + (entry2 + " ".repeat(maxKey2)).substring(0, maxKey2) + " | " + (internalEntries.get(entry2).toString() + " ".repeat(maxValue)).substring(0, maxValue) + "\n";
            }
        }
        return result;
    }

    @Override
    public void exportToConsole() {
        HashMap<String, HashMap<String, Double>> mapToPrint = sumEntryList(entryList);
        String stringToPrint = printHashMap(mapToPrint);
        System.out.println(stringToPrint);
    }

    @Override
    public void exportToExcel(String outputPath) {

    }

    @Override
    public void exportToPDF() {

    }
}
