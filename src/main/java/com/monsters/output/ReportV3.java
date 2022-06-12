package com.monsters.output;

import com.monsters.util.Entry;
import com.monsters.util.OurDateTimeFormatter;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.style.Styler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportV3 implements Report {

    private static final Logger log = Logger.getLogger(ReportV3.class.getName());
    List<Entry> entryList;

    public ReportV3(List<Entry> entryList) {
        this.entryList = entryList;
    }

    HashMap<String, HashMap<String, Double>> sumEntryList(List<Entry> entryList) {
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
        String keyHeader = "Nazwisko Imie";
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
        HashMap<String, HashMap<String, Double>> mapToPrint = sumEntryList(entryList);


        Workbook wb = new HSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        //Sheet sheet = wb.createSheet("report_3");
        Sheet sheet = addChart(wb);

        Row row0 = sheet.createRow(0);
        Cell cell00 = row0.createCell(0);
        cell00.setCellValue("Nazwisko Imie");
        Cell cell01 = row0.createCell(1);
        cell01.setCellValue("Projekt");
        Cell cell02 = row0.createCell(2);
        cell02.setCellValue("Liczba godzin");

        int i_row = 1;

        for (Map.Entry<String, HashMap<String, Double>> entry : mapToPrint.entrySet()) {
            HashMap<String, Double> internalEntries = mapToPrint.get(entry.getKey());
            for (Map.Entry<String, Double> entry2 : internalEntries.entrySet()) {
                String user = entry.getKey();
                String project = entry2.getKey();
                Double hours = entry2.getValue();

                Row row = sheet.createRow(i_row);

                Cell cellx0 = row.createCell(0);
                Cell cellx1 = row.createCell(1);
                Cell cellx2 = row.createCell(2);

                cellx0.setCellValue(user);
                cellx1.setCellValue(project);
                cellx2.setCellValue(hours);

                i_row++;
            }

        }
        OurDateTimeFormatter ourDateTimeFormatter = new OurDateTimeFormatter();
        Path path = Paths.get(outputPath, "report_3_"+ourDateTimeFormatter.getFormattedDateTime()+".xls");
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
    public byte[] createChart() {
        CategoryChart chart = getChart();
        try {
            return BitmapEncoder.getBitmapBytes(chart, BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            log.warn("Cannot create a chart");
            throw new RuntimeException(e);
        }
//        new SwingWrapper<CategoryChart>(chart).displayChart();
    }

    CategoryChart getChart() {

        // Create Chart
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600).title("Employees Report").xAxisTitle("Employee").yAxisTitle("Hours").theme(Styler.ChartTheme.GGPlot2).build();

        // Customize Chart
        //       chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        //     chart.getStyler().setHasAnnotations(true);
        HashMap<String, HashMap<String, Double>> stringDoubleHashMap = sumEntryList(entryList);
        // Series
        int[] number = new int[stringDoubleHashMap.size()];

        ArrayList<String> project = new ArrayList<>();
        ArrayList<Double> hours = new ArrayList<>();

        List<String> names = stringDoubleHashMap.entrySet().stream().map(l -> l.getKey()).distinct().collect(Collectors.toList());
        List<String> distinct = new ArrayList<>();
        names.forEach( l -> {
            distinct.addAll(stringDoubleHashMap.get(l).entrySet().stream().map(x -> x.getKey()).distinct().collect(Collectors.toList()));
        });
        List<String> distinct2 = distinct.stream().distinct().collect(Collectors.toList());

        names.forEach( l -> {
            if (stringDoubleHashMap.containsKey(l)) {
                HashMap<String, Double> stringDoubleHashMap1 = stringDoubleHashMap.get(l);
                distinct2.forEach( i -> {
                    if (stringDoubleHashMap1.containsKey(i)) {
                        project.add(i);
                        hours.add(stringDoubleHashMap1.get(i));
                    } else {
                        project.add(i);
                        hours.add(0.0);
                    }
                });
            }

            chart.addSeries(l,project,hours);
            project.clear();
            hours.clear();
        });

        try {
            BitmapEncoder.saveBitmap(chart, "./Sample_Chart", BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return chart;

    }

    private Sheet addChart(Workbook wb) {
        createChart();
        int inputImagePicture = wb.addPicture(createChart(), Workbook.PICTURE_TYPE_PNG);
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("report_1");
        Drawing drawing = (Drawing) sheet.createDrawingPatriarch();
        HSSFClientAnchor clientAnchor = new HSSFClientAnchor();
        clientAnchor.setCol1(5);
        clientAnchor.setCol2(15);
        clientAnchor.setRow1(0);
        clientAnchor.setRow2(30);
        drawing.createPicture(clientAnchor, inputImagePicture);
        return sheet;
    }
}
