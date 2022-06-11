package com.monsters.input;

import com.monsters.util.Entry;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class FileReader {

    public List<Entry> parseXLS(String filePath) {

        String user = getUserName(filePath);
        InputStream inputStream = getFileInputStream(filePath);
        List<Entry> entryList = new ArrayList<>();
        HSSFWorkbook workbook = null;

        try {
            workbook = new HSSFWorkbook(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {

            HSSFSheet currentSheet = workbook.getSheetAt(i);
            Iterator<Row> rowIterator = currentSheet.rowIterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if (validateEntryData(row)) {
                    Entry entry = getEntry(user, currentSheet, row);
                    entryList.add(entry);
                }
            }
        }
        return entryList;
    }

    private boolean validateEntryData(Row row) {
        return row.getCell(0) != null && row.getCell(0).getCellType() != CellType.BLANK && !row.getCell(0).toString().equals("Data");
    }

    private Entry getEntry(String user, HSSFSheet currentSheet, Row row) {
        Date tempDate = row.getCell(0).getDateCellValue();
        LocalDate date = tempDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        String project = currentSheet.getSheetName();
        String taskName = row.getCell(1).getStringCellValue();
        Double duration = row.getCell(2).getNumericCellValue();

        Entry entry = new Entry(date, project, taskName, duration, user);
        return entry;
    }

    private String getUserName(String filePath) {
        String user = filePath.substring(filePath.lastIndexOf("/") + 1)
                .replace(".xls", "")
                .replace("_", " ");
        return user;
    }

    private InputStream getFileInputStream(String filePath) {
        try {
            InputStream is = new FileInputStream(filePath);
            return is;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    //    public void parseXLSX() {
    //        TODO
//    }
}
