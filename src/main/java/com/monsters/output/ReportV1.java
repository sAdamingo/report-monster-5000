package com.monsters.output;

import com.monsters.util.Entry;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.markers.SeriesMarkers;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

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
    public void exportToExcel(String outputPath) {
        HashMap<String, Double> mapToPrint = sumEntryList(entryList);

        createChart();
        Workbook wb = new HSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("report_1");

        Row row0 = sheet.createRow(0);
        Cell cell00 = row0.createCell(0);
        cell00.setCellValue("Imię Nazwisko");
        Cell cell01 = row0.createCell(1);
        cell01.setCellValue("Liczba godzin");

        int i_row = 1;
        for (Map.Entry<String, Double> entry : mapToPrint.entrySet()) {
            String user = entry.getKey();
            Double hours = entry.getValue();

            Row row = sheet.createRow(i_row);

            Cell cellx0 = row.createCell(0);
            Cell cellx1 = row.createCell(1);

            cellx0.setCellValue(user);
            cellx1.setCellValue(hours);

            i_row++;
        }
        Path path = Paths.get(outputPath, "report_1.xls");
        try  (OutputStream fileOut = new FileOutputStream(String.valueOf(path))) {
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exportToPDF() {

    }

    @Override
    public void createChart() {
        CategoryChart chart = getChart();
        new SwingWrapper<CategoryChart>(chart).displayChart();
    }

     CategoryChart getChart() {

        // Create Chart
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Employees Report").xAxisTitle("Employee").yAxisTitle("Hours").build();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);
         HashMap<String, Double> stringDoubleHashMap = sumEntryList(entryList);
         // Series
         int[] number = new int[stringDoubleHashMap.size()];
         ArrayList<String> names = new ArrayList<>();
         ArrayList<Double> hours = new ArrayList<>();

         stringDoubleHashMap.forEach(
                 (l,m) -> {
                     names.add(l);
                     hours.add(m);
                 }
         );


        chart.addSeries("Report",names,hours);

         try {
             BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapEncoder.BitmapFormat.PNG);
         } catch (IOException e) {
             throw new RuntimeException(e);
         }

         return chart;

    }

    public List<Entry> getEntryList() {
        return entryList;
    }

    public void setEntryList(List<Entry> entryList) {
        this.entryList = entryList;
    }
}
