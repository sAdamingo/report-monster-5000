package com.monsters.output;

import com.monsters.util.Entry;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ReportV1 implements Report{
    List<Entry> entryList;

    HashMap<String, Double> sumEntryList(List<Entry> entryList){
        HashMap<String, Double>summedEntries = new HashMap<>();
        for (Entry entry :entryList ) {
            if(summedEntries.containsKey(entry.getUser())) {
                Double sum = summedEntries.get(entry.getUser());
                summedEntries.put(entry.getUser(), sum+entry.getDuration());
            }else {
                summedEntries.put(entry.getUser(), entry.getDuration());
            }
        }
        return summedEntries;
    }

    StringWriter printHashMap(){
        String keyHeader = "Imie Nazwisko";
        int maxKey = keyHeader.length();
        String valueHeader = "Liczba godzin";
        int maxValue = valueHeader.length();

        for (:
             ) {
            
        }
    }

    @Override
    public void exportToConsole() {

    }

    @Override
    public void exportToExcel() {

    }

    @Override
    public void exportToPDF() {

    }
}
