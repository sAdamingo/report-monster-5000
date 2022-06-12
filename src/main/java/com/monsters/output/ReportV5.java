package com.monsters.output;

import com.monsters.util.Entry;
import com.monsters.util.OurDateTimeFormatter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.colors.ChartColor;

import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class ReportV5 implements Report {
    List<Entry> entryList;

    public ReportV5(List<Entry> entryList) {
        this.entryList = entryList;
        if (this.entryList.size() > 0) {
            Collections.sort(this.entryList, new Comparator<Entry>() {
                @Override
                public int compare(final Entry object1, final Entry object2) {
                    return object1.getDate().compareTo(object2.getDate());
                }
            });
        }
    }


    HashMap<String, TreeMap<LocalDate, Double>> sumEntryList(List<Entry> entryList) {
        HashMap<String, TreeMap<LocalDate, Double>> summedEntries = new HashMap<>();

        for (Entry entry : entryList) {
            if (summedEntries.containsKey(entry.getProject())) {
                TreeMap<LocalDate, Double> internalEntries = summedEntries.get(entry.getProject());
                if (internalEntries.containsKey(entry.getDate())) {
                    Double sum = internalEntries.get(entry.getDate());
                    internalEntries.put(entry.getDate(), sum + entry.getDuration());
                }else{
                    internalEntries.put(entry.getDate(), entry.getDuration());
                }
                summedEntries.put(entry.getProject(), internalEntries);
            } else {
                TreeMap<LocalDate, Double> internalEntries = new TreeMap<>();
                internalEntries.put(entry.getDate(), entry.getDuration());
                summedEntries.put(entry.getProject(), internalEntries);
            }
        }
        return summedEntries;
    }

    String printHashMap(HashMap<String, TreeMap<LocalDate, Double>>  summedEntries) {
        String keyHeader = "Projekt";
        int maxKey = keyHeader.length();
        String keyHeader2 = "Date";
        int maxKey2 = keyHeader2.length();
        String valueHeader = "Liczba godzin";
        int maxValue = valueHeader.length();

        for (String entry : summedEntries.keySet()) {
            if (maxKey < entry.length()) {
                maxKey = entry.length();
            }
            TreeMap<LocalDate, Double> internalEntries = summedEntries.get(entry);
            for (LocalDate entry2 : internalEntries.keySet()) {
                if (maxKey2 < entry2.toString().length()) {
                    maxKey2 = entry2.toString().length();
                }
                if (maxValue < internalEntries.get(entry2).toString().length()) {
                    maxValue = internalEntries.get(entry2).toString().length();
                }

            }

        }
        String result = (keyHeader + " ".repeat(maxKey)).substring(0,maxKey) + " | " +(keyHeader2 + " ".repeat(maxKey2)).substring(0,maxKey2) + " | " + (valueHeader + " ".repeat(maxValue)).substring(0,maxValue) + "\n";

        for (String entry : summedEntries.keySet()) {
            TreeMap<LocalDate, Double> internalEntries = summedEntries.get(entry);
            for (LocalDate entry2 : internalEntries.keySet()) {
                result = result + (entry + " ".repeat(maxKey)).substring(0, maxKey) + " | " + (entry2 + " ".repeat(maxKey2)).substring(0, maxKey2) + " | " + (internalEntries.get(entry2).toString() + " ".repeat(maxValue)).substring(0, maxValue) + "\n";
            }
        }
        return result;
    }

    @Override
    public void exportToConsole() {
        HashMap<String, TreeMap<LocalDate, Double>> mapToPrint = sumEntryList(entryList);
        String stringToPrint = printHashMap(mapToPrint);
        System.out.println(stringToPrint);

    }

    @Override
    public void exportToExcel(String outputPath) {
        HashMap<String, TreeMap<LocalDate, Double>> mapToPrint = sumEntryList(entryList);


        Workbook wb = new HSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();
        Sheet sheet = wb.createSheet("report_3");

        Row row0 = sheet.createRow(0);
        Cell cell00 = row0.createCell(0);
        cell00.setCellValue("Projekt");
        Cell cell01 = row0.createCell(1);
        cell01.setCellValue("Date");
        Cell cell02 = row0.createCell(2);
        cell02.setCellValue("Liczba godzin");

//        CellStyle cellStyle = wb.createCellStyle();
//        cellStyle.setDataFormat();
        int i_row = 1;

        for (Map.Entry<String, TreeMap<LocalDate, Double>> entry : mapToPrint.entrySet()) {
            TreeMap<LocalDate, Double> internalEntries = mapToPrint.get(entry.getKey());
            for (Map.Entry<LocalDate, Double> entry2 : internalEntries.entrySet()) {
                String project = entry.getKey();
                LocalDate date = entry2.getKey();
                Double hours = entry2.getValue();

                Row row = sheet.createRow(i_row);

                Cell cellx0 = row.createCell(0);
                Cell cellx1 = row.createCell(1);
                Cell cellx2 = row.createCell(2);

                cellx0.setCellValue(project);
                cellx1.setCellValue(date);

                cellx2.setCellValue(hours);

                i_row++;
            }

        }
        OurDateTimeFormatter ourDateTimeFormatter = new OurDateTimeFormatter();
        Path path = Paths.get(outputPath, "report_5_"+ourDateTimeFormatter.getFormattedDateTime()+".xls");
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
        return new byte[0];
    }

    public void generateChart() {

        HashMap<String, TreeMap<LocalDate, Double>> mapToPrint = sumEntryList(entryList);

        XYChart chart = new XYChartBuilder().width(800).height(600).title("Projekty").xAxisTitle("X").yAxisTitle("Y").build();

        for (Map.Entry<String, TreeMap<LocalDate, Double>> entry : mapToPrint.entrySet()) {
            List<Date> xSeries = new LinkedList<>();
            List<Double> ySeries = new LinkedList<>();
            String project="";
            TreeMap<LocalDate, Double> internalEntries = mapToPrint.get(entry.getKey());
            for (Map.Entry<LocalDate, Double> entry2 : internalEntries.entrySet()) {
                project=  entry.getKey();
                Date date = java.sql.Date.valueOf(entry2.getKey());
                Double hours = entry2.getValue();
                xSeries.add(date);
                ySeries.add(hours);
            }
            XYSeries series = chart.addSeries(project, xSeries, ySeries);
        }

        chart.getStyler().setPlotBackgroundColor(ChartColor.getAWTColor(ChartColor.GREY));
        chart.getStyler().setPlotGridLinesColor(new Color(255, 255, 255));
        chart.getStyler().setChartBackgroundColor(Color.WHITE);
        chart.getStyler().setLegendBackgroundColor(Color.PINK);
        chart.getStyler().setChartFontColor(Color.MAGENTA);
        chart.getStyler().setChartTitleBoxBackgroundColor(new Color(0, 222, 0));
        chart.getStyler().setChartTitleBoxVisible(true);
        chart.getStyler().setChartTitleBoxBorderColor(Color.BLACK);
        chart.getStyler().setPlotGridLinesVisible(false);

        chart.getStyler().setAxisTickPadding(20);

        chart.getStyler().setAxisTickMarkLength(15);

        chart.getStyler().setPlotMargin(20);

        chart.getStyler().setChartTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        chart.getStyler().setLegendFont(new Font(Font.SERIF, Font.PLAIN, 18));
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);
        chart.getStyler().setLegendSeriesLineLength(12);
        chart.getStyler().setAxisTitleFont(new Font(Font.SANS_SERIF, Font.ITALIC, 18));
        chart.getStyler().setAxisTickLabelsFont(new Font(Font.SERIF, Font.PLAIN, 11));
        chart.getStyler().setDatePattern("dd-MMM-yyyy");
        chart.getStyler().setDecimalPattern("#0.000");
        chart.getStyler().setLocale(Locale.ENGLISH);

        // Show it
        new SwingWrapper<XYChart>(chart).displayChart();
    }
}
