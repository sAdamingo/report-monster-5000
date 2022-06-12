package com.monsters.output;

import com.monsters.util.Entry;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.colors.ChartColor;

import java.awt.*;
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

    @Override
    public void exportToConsole() {

    }

    @Override
    public void exportToExcel(String outputPath) {

    }

    @Override
    public void exportToPDF() {

    }

    @Override
    public byte[] createChart() {
        return new byte[0];
    }

    public void generateChart() {
        HashMap<String, List<LocalDate>> xDataHashMapListLocalDate = new HashMap<>();
        HashMap<String, List<Double>> yDataHashMapListDouble = new HashMap<>();

        for (Entry entry : entryList) {
            List<LocalDate> xList = xDataHashMapListLocalDate.getOrDefault(entry.getProject(), new LinkedList<>());
            xList.add(entry.getDate());
            xDataHashMapListLocalDate.put(entry.getProject(), xList);

            xDataHashMapListLocalDate.put(entry.getProject(), xDataHashMapListLocalDate.getOrDefault(entry.getProject(), new LinkedList<>()));
            List<Double> yList = yDataHashMapListDouble.getOrDefault(entry.getProject(), new LinkedList<>());
            yList.add(entry.getDuration());
            yDataHashMapListDouble.put(entry.getProject(), yList);
        }

        XYChart chart = new XYChartBuilder().width(800).height(600).title("Projekty").xAxisTitle("X").yAxisTitle("Y").build();

        for (Map.Entry<String, List<LocalDate>> entry : xDataHashMapListLocalDate.entrySet()) {
            List<Date> xDataList = new LinkedList<>();
            for (LocalDate item : xDataHashMapListLocalDate.get(entry.getKey())) {
                Date dateItem = java.sql.Date.valueOf(item);
                xDataList.add(dateItem);
            }
            List<Double> yDataList = new ArrayList<Double>();
            for (Double item : yDataHashMapListDouble.get(entry.getKey())) {
                yDataList.add(item);
            }
            XYSeries series = chart.addSeries(entry.getKey(), xDataList, yDataList);
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
